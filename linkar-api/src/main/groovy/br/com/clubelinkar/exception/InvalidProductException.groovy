package br.com.clubelinkar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class InvalidProductException extends BusinessException {

    public InvalidProductException() {
        super("Produto inválido ou inexistente!")
    }

}
