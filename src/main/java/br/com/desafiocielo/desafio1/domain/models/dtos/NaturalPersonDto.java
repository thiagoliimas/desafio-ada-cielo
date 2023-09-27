package br.com.desafiocielo.desafio1.domain.models.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class NaturalPersonDto {

    public NaturalPersonDto(String cpf, Long merchantCategoryCode, String name, String email) {
        this.cpf = cpf;
        this.merchantCategoryCode = merchantCategoryCode;
        this.name = name;
        this.email = email;
    }

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Max(value = 9999)
    private Long merchantCategoryCode;

    @NotBlank
    private String name;

    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}$", message = "Formato de email inválido")
    private String email;
}
