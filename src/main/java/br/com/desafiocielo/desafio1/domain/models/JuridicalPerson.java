package br.com.desafiocielo.desafio1.domain.models;

import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "Pessoa_Juridica")
@Getter
@Setter
public final class JuridicalPerson extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 50)
    private String companyName;

    public JuridicalPerson() {
    }

    public JuridicalPerson(UUID id, Long merchantCategoryCode, String name, String cpf, String email, String cnpj, String companyName) {
        super(id, merchantCategoryCode, name, cpf, email);
        this.cnpj = cnpj;
        this.companyName = companyName;
    }

    public JuridicalPerson(JuridicalPersonDto juridicalPersonDTO) {
        super.setId(UUID.randomUUID());
        super.setCpf(juridicalPersonDTO.getCpf());
        super.setName(juridicalPersonDTO.getName());
        super.setEmail(juridicalPersonDTO.getEmail());
        super.setMerchantCategoryCode(juridicalPersonDTO.getMerchantCategoryCode());
        this.setCnpj(juridicalPersonDTO.getCnpj());
        this.setCompanyName(juridicalPersonDTO.getCompanyName());
    }
}
