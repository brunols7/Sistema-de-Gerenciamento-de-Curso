package com.curso.gerenciamento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gerenciamento de Cursos API")
                        .version("1.0.0")
                        .description("API para gerenciamento de cursos, alunos, professores, aulas, avaliações, certificados e matrículas")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .url("https://github.com/brunols7/Sistema-de-Gerenciamento-de-Curso")));
    }
}
