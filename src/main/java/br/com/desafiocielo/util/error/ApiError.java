package br.com.desafiocielo.util.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiError {

    private String message;

    public ApiError(String message) {
        this.message = message;
    }
}
