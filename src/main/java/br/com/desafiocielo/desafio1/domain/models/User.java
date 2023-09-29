package br.com.desafiocielo.desafio1.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public sealed abstract class User permits NaturalPerson, JuridicalPerson{

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, length = 4)
    private Long merchantCategoryCode;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", merchantCategoryCode=" + merchantCategoryCode +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
