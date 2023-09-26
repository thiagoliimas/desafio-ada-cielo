package br.com.desafiocielo.domain.models;

import jakarta.persistence.Entity;


@Entity(name = "Pessoa_Fisica")
public final class NaturalPerson extends User {

    public NaturalPerson(){
        super();
    }
}
