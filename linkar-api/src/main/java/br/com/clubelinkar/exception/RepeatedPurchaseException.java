package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Order repetida")
public class RepeatedPurchaseException extends BusinessException {

    public RepeatedPurchaseException() {
        super("Este produto já foi comprado por este cliente.");
    }
}
