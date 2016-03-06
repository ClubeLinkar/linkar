package br.com.clubelinkar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class InvalidStoreException extends BusinessException {

    public InvalidStoreException() {
        super("Loja inválida ou inexistente!")
    }

}
