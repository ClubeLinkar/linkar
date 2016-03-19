package br.com.clubelinkar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends BusinessException {

    public InvalidPasswordException() {
        super("Senha inválida. Sua senha deve ter no mínimo 8 caracteres, pelo menos um número e um caracter maiúsculo.")
    }

}
