package br.com.desafiocielo.desafio1.domain.models;

import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "Pessoa_Juridica")
@Getter
@Setter
public final class JuridicalPerson extends User {

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 50)
    private String companyName;

    public JuridicalPerson(JuridicalPersonDto juridicalPersonDTO) {
        super.setId(UUID.randomUUID());
        super.setCpf(juridicalPersonDTO.getCpf());
        super.setName(juridicalPersonDTO.getName());
        super.setEmail(juridicalPersonDTO.getEmail());
        super.setMerchantCategoryCode(juridicalPersonDTO.getMerchantCategoryCode());
        this.setCnpj(juridicalPersonDTO.getCnpj());
        this.setCompanyName(juridicalPersonDTO.getCompanyName());
    }

    public JuridicalPerson() {
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        JuridicalPerson that = (JuridicalPerson) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
