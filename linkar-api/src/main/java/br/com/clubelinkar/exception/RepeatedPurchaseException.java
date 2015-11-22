package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Purchase repetida")
public class RepeatedPurchaseException extends RuntimeException {

    public RepeatedPurchaseException() {
        super();
    }
}
