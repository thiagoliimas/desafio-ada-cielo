package br.com.desafiocielo.desafio1.repositories;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Queue;
import java.util.UUID;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, UUID> {
}