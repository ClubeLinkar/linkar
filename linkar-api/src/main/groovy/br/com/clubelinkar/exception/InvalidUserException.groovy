package br.com.clubelinkar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class InvalidUserException extends BusinessException {

    public InvalidUserException() {
        super("Usuário inválido ou inexistente!")
    }

}
