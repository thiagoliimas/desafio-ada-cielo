package br.com.desafiocielo.desafio1.repositories;

import br.com.desafiocielo.desafio1.domain.models.authentication.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationRepository extends JpaRepository<UserAuthentication, String> {

    UserDetails findByLogin(String login);
}
