package br.com.desafiocielo.desafio1.repositories;

import br.com.desafiocielo.desafio1.domain.models.JuridicalPerson;
import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JuridicalPersonRepository extends JpaRepository<JuridicalPerson, UUID> {
}