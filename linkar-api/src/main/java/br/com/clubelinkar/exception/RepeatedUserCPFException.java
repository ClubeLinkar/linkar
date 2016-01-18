package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RepeatedUserCPFException extends BusinessException {

    public RepeatedUserCPFException() {
        super("CPF já cadastrado.");
    }

}
