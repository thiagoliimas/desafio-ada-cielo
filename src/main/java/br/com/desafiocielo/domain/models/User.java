package br.com.desafiocielo.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public sealed abstract class User permits NaturalPerson, JuridicalPerson {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(length = 4)
    private Long merchantCategoryCode;

    @Column(length = 50)
    private String name;

    @Column(unique = true, length = 9)
    private String cpf;

    @Column(unique = true, length = 50)
    private String email;
}
