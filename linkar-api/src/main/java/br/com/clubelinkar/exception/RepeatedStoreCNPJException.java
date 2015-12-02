package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CNPJ de loja repetido    ")
public class RepeatedStoreCNPJException extends RuntimeException {

    public RepeatedStoreCNPJException() {
        super();
    }

}
