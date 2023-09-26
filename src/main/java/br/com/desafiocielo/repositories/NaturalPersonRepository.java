package br.com.desafiocielo.repositories;

import br.com.desafiocielo.domain.models.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, UUID> {
}