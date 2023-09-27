package br.com.desafiocielo.desafio1.domain.models.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class JuridicalPersonDto {

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Max(value = 9999)
    private Long merchantCategoryCode;

    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}$", message = "Formato de email inválido")
    private String email;

    @NotBlank
    @Length(min = 14, max = 14)
    private String cnpj;

    @NotBlank
    @Length(max = 50)
    private String companyName;
}
