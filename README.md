# Sistema de Gestão de Cursos Online

### Integrantes
- Bruno Leonardo Silva
- Caique de Oliveira Castro
- Heitor Pereira de Lucena
- Mariana Vidal Vaz
- William Vieira de Sousa

## Descrição do Domínio e Justificativa

O sistema **Gestão de Cursos Online** tem como objetivo gerenciar um ambiente educacional digital, permitindo o cadastro de **alunos, professores, cursos, aulas e matrículas**, além do controle de **avaliações, certificados e categorias de cursos**.

O domínio foi modelado para contemplar todas as exigências do trabalho:
- Múltiplas entidades de domínio (8 no total);
- Relações **1:1**, **1:N**, **N:1** e **N:N**;
- Uso de **chave composta**, onde as FKs também são PKs;
- Operações de negócio além do CRUD simples.

### Entidades criadas e justificativas

| Entidade | Descrição | Justificativa |
|-----------|------------|---------------|
| **Aluno** | Representa o estudante cadastrado. | Base do relacionamento com cursos (via matrículas). |
| **Professor** | Instrutor responsável por ministrar cursos. | Um professor cria vários cursos. |
| **Curso** | Curso publicado no sistema. | Entidade central, ligada a professor, aulas, categorias e avaliações. |
| **Aula** | Cada conteúdo pertencente a um curso. | Representa as partes que compõem o curso. |
| **Categoria** | Classificação dos cursos (ex: Tecnologia, Design). | Permite relacionar vários cursos a várias categorias (N:N). |
| **Matricula** | Associação entre aluno e curso. | Representa a inscrição, com chave composta (aluno_id + curso_id). |
| **Certificado** | Emitido após a conclusão da matrícula. | Tem relação 1:1 com matrícula (FK é PK). |
| **Avaliacao** | Opinião do aluno sobre o curso. | Relaciona aluno e curso, permitindo cálculos de média. |

---

## Diagrama Conceitual (Entidades e Relacionamentos)

```yaml
Aluno (1)───< Matricula >───(1) Curso
 │                            │
 │                            ├──< Aula
 │                            ├──< Avaliacao
 │                            └──< Curso_Categoria >──> Categoria
 └──< Avaliacao
Matricula ───(1:1)──> Certificado
```

---

## Descrição das Relações

| Tipo | Entidades | Descrição |
|------|------------|------------|
| **1:1** | Matricula ↔ Certificado | Cada matrícula gera um único certificado. |
| **1:N** | Professor → Curso | Um professor pode criar vários cursos. |
| **N:1** | Curso → Professor | Cada curso pertence a um único professor. |
| **N:N** | Curso ↔ Categoria | Um curso pode pertencer a várias categorias e vice-versa. |
| **1:N** | Curso → Aula | Um curso possui várias aulas. |
| **1:N** | Curso → Avaliação | Um curso pode ter várias avaliações. |
| **Chave composta** | Matricula (aluno_id, curso_id) | Define uma matrícula única entre aluno e curso. |

---

## Operações Além do CRUD

| Operação | Descrição | Entidades Envolvidas |
|-----------|------------|----------------------|
| **Cálculo de média de avaliações** | Calcula a média das notas de um curso. | `Avaliacao` |
| **Filtragem de cursos por professor** | Lista todos os cursos de um professor específico. | `Curso`, `Professor` |
| **Busca de curso por título** | Pesquisa cursos contendo uma palavra-chave. | `Curso` |
| **Conclusão de curso com geração de certificado** | Marca matrícula como concluída e cria certificado. | `Matricula`, `Certificado` |
| **Listagem de matrículas de um aluno** | Mostra todas as matrículas do aluno logado. | `Matricula`, `Aluno` |

---

# Exemplo de Chamadas de API

## Alunos
### Criar um aluno

**POST** `/alunos`

```json
{
    "nome": "Maria Souza",
    "email": "maria@email.com",
    "dataNascimento": "2000-06-10"
}
```

#### Resposta (201 Created)

```json
{
    "id": 1,
    "nome": "Maria Souza",
    "email": "maria@email.com",
    "dataNascimento": "2000-06-10"
}
```

### Buscar todos os alunos

**GET** `/alunos`

#### Resposta (200 OK) (exemplo)

```json
[
    {
        "id": 1,
        "nome": "Maria Souza",
        "email": "maria@email.com",
        "dataNascimento": "2000-06-10"
    }
]
```

## Professores
### Criar um professor

**POST** `/professores`

```json
{
    "nome": "Carlos Pereira",
    "email": "carlos@email.com",
    "especialidade": "Programação"
}
```


#### Resposta (201 Created) (exemplo)

```json
{
    "id": 1,
    "nome": "Carlos Pereira",
    "email": "carlos@email.com",
    "especialidade": "Programação"
}
```

## Cursos
### Criar um curso

**POST** `/cursos`

```json
{
    "titulo": "Java Spring Boot",
    "descricao": "Curso completo de APIs REST",
    "cargaHoraria": 40,
    "professor": { "id": 1 }
}
```


#### Resposta (201 Created) (exemplo)

```json
{
    "id": 1,
    "titulo": "Java Spring Boot",
    "descricao": "Curso completo de APIs REST",
    "cargaHoraria": 40,
    "professor": { "id": 1 }
}
```

### Buscar cursos por professor

**GET** `/cursos/professor/1`
#### Resposta (200 OK) (exemplo)

```json
[
    {
        "id": 1,
        "titulo": "Java Spring Boot",
        "professor": { "id": 1 }
    }
]
```

### Buscar cursos por título (pesquisa)

**GET** `/cursos/buscar?titulo=java`
#### Resposta (200 OK) (exemplo)

```json
[
    {
        "id": 1,
        "titulo": "Java Spring Boot",
        "descricao": "Curso completo de APIs REST"
    }
]
```

## Aulas
### Criar uma aula

**POST** `/aulas`

```json
{
    "titulo": "Introdução ao Spring Boot",
    "urlVideo": "https://video.com/123",
    "duracao": 25,
    "curso": { "id": 1 }
}
```


#### Resposta (201 Created) (exemplo)

```json
{
    "id": 1,
    "titulo": "Introdução ao Spring Boot",
    "urlVideo": "https://video.com/123",
    "duracao": 25,
    "curso": { "id": 1 }
}
```

## Categorias
### Criar uma categoria

**POST** `/categorias`

```json
{
    "nome": "Tecnologia"
}
```

#### Resposta (201 Created) (exemplo)

```json
{
    "id": 1,
    "nome": "Tecnologia"
}
```

## Avaliações
### Criar avaliação

**POST** `/avaliacoes`

```json
{
    "nota": 9.5,
    "comentario": "Excelente curso!",
    "data": "2025-11-10",
    "aluno": { "id": 1 },
    "curso": { "id": 1 }
}
```

#### Resposta (201 Created) (exemplo)

```json
{
    "id": 1,
    "nota": 9.5,
    "comentario": "Excelente curso!",
    "data": "2025-11-10",
    "aluno": { "id": 1 },
    "curso": { "id": 1 }
}
```

### Calcular média de notas por curso

**GET** `/avaliacoes/curso/1/media`
#### Resposta (200 OK)

```json
{
    "cursoId": 1,
    "media": 9.5
}
```

## Matrículas
### Criar matrícula

**POST** `/matriculas`

```json
{
    "aluno": { "id": 1 },
    "curso": { "id": 1 }
}
```


#### Resposta (201 Created) (exemplo — retorna a matrícula com a PK composta representada)

```json
{
    "id": {
        "alunoId": 1,
        "cursoId": 1
    },
    "dataMatricula": "2025-11-10",
    "concluido": false
}
```

### Listar matrículas de um aluno

**GET** `/matriculas/aluno/1`
#### Resposta (200 OK) (exemplo)

```json
[
    {
        "id": { "alunoId": 1, "cursoId": 1 },
        "dataMatricula": "2025-11-10",
        "concluido": false
    }
]
```

### Concluir curso e gerar certificado (processo)

**POST** `/matriculas/concluir`

```json
{
    "alunoId": 1,
    "cursoId": 1
}
```


#### Resposta (201 Created) (exemplo de Certificado gerado)

```json
{
    "matricula": { "alunoId": 1, "cursoId": 1 },
    "dataEmissao": "2025-11-10",
    "codigo": "CERT-1-1"
}
```

## Certificados
### Listar certificados

**GET** `/certificados`

#### Resposta (200 OK) (exemplo)
```json
[
    {
        "matricula": { "alunoId": 1, "cursoId": 1 },
        "dataEmissao": "2025-11-10",
        "codigo": "CERT-1-1"
    }
]
```

### Buscar certificado

**GET** `/certificados/{alunoId}/{cursoId}`

#### Resposta (200 OK) (exemplo)

```json
{
    "matricula": { "alunoId": 1, "cursoId": 1 },
    "dataEmissao": "2025-11-10",
    "codigo": "CERT-1-1"
}
```

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Maven**

---
