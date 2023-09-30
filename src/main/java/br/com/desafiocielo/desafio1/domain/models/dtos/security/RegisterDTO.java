package br.com.desafiocielo.desafio1.domain.models.dtos.security;

import br.com.desafiocielo.desafio1.domain.models.authentication.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
