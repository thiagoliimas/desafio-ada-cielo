package br.com.desafiocielo.domain.models.dto;

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
    @Size(min = 9, max = 9)
    private String cpf;

    @NotNull
    @Max(4)
    private Long merchantCategoryCode;

    @NotBlank
    @Max(50)
    private String name;

    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}$", message = "Formato de email inválido")
    private String email;
}
