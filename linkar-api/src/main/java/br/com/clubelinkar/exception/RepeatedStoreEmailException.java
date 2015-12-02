package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email de loja repetido    ")
public class RepeatedStoreEmailException extends RuntimeException {

    public RepeatedStoreEmailException() {
        super();
    }

}
