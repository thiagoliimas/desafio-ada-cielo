package br.com.desafiocielo.domain.models.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class JuridicalPersonDto {

    @NotBlank
    @Size(min = 9, max = 9)
    private String cpf;

    @NotNull
    @Max(4)
    private Long merchantCategoryCode;

    @NotBlank
    @Max(50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank
    @Max(50)
    private String companyName;
}
