package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Store nao existe    ")
public class InvalidStoreException extends RuntimeException {

    public InvalidStoreException() {
        super();
    }

}
