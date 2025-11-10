package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.domain.Matricula;
import com.curso.gerenciamento.domain.MatriculaId;
import com.curso.gerenciamento.repository.CertificadoRepository;
import com.curso.gerenciamento.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CertificadoRepository certificadoRepository;

    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    public Matricula buscarPorId(MatriculaId id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
    }

    public Matricula salvar(Matricula matricula) {
        matricula.setDataMatricula(LocalDate.now());
        return matriculaRepository.save(matricula);
    }

    public void deletar(MatriculaId id) {
        matriculaRepository.deleteById(id);
    }

    public List<Matricula> listarPorAluno(Long alunoId) {
        return matriculaRepository.findByAlunoId(alunoId);
    }

    public Certificado concluirCurso(MatriculaId id) {
        Matricula matricula = buscarPorId(id);
        matricula.setConcluido(true);

        Certificado certificado = new Certificado();
        certificado.setMatricula(matricula);
        certificado.setDataEmissao(LocalDate.now());
        certificado.setCodigo("CERTI-" + id.getAlunoId() + "-" + id.getCursoId());

        matricula.setCertificado(certificado);

        matriculaRepository.save(matricula);
        return certificadoRepository.save(certificado);
    }

}
