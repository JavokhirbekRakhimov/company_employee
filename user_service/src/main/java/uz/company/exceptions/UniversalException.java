package uz.company.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
public  class UniversalException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public UniversalException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }

}
