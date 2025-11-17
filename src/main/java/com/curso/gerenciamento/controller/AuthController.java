package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.RefreshToken;
import com.curso.gerenciamento.domain.Usuario;
import com.curso.gerenciamento.dto.*;
import com.curso.gerenciamento.repository.UsuarioRepository;
import com.curso.gerenciamento.service.JwtService;
import com.curso.gerenciamento.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Erro: Username já está em uso!");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Erro: Email já está em uso!");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEnabled(true);

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            usuario.setRoles(new HashSet<>());
            usuario.getRoles().add("ROLE_USER");
        } else {
            usuario.setRoles(request.getRoles());
        }

        usuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario.getId());

        AuthResponse response = AuthResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .roles(usuario.getRoles())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsuario)
                .map(usuario -> {
                    UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                            .username(usuario.getUsername())
                            .password(usuario.getPassword())
                            .authorities(usuario.getRoles().toArray(new String[0]))
                            .build();

                    String token = jwtService.generateToken(userDetails);

                    RefreshTokenResponse response = RefreshTokenResponse.builder()
                            .accessToken(token)
                            .refreshToken(requestRefreshToken)
                            .tokenType("Bearer")
                            .build();

                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token não encontrado!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request) {
        refreshTokenService.findByToken(request.getRefreshToken())
                .ifPresent(refreshToken -> refreshTokenService.deleteByUserId(refreshToken.getUsuario().getId()));

        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}
