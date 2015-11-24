package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User nao existe    ")
public class InvalidUserException extends RuntimeException {

    public InvalidUserException() {
        super();
    }

}
