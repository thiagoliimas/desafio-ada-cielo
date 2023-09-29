package br.com.desafiocielo.desafio1.domain.models;

import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity(name = "Pessoa_Fisica")
public final class NaturalPerson extends User {

    public NaturalPerson(NaturalPersonDto naturalPersonDto) {
        super.setId(UUID.randomUUID());
        super.setCpf(naturalPersonDto.getCpf());
        super.setName(naturalPersonDto.getName());
        super.setEmail(naturalPersonDto.getEmail());
        super.setMerchantCategoryCode(naturalPersonDto.getMerchantCategoryCode());
    }

    public NaturalPerson() {
    }

    public NaturalPerson(UUID id, Long merchantCategoryCode, String name, String cpf, String email) {
        super(id, merchantCategoryCode, name, cpf, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
