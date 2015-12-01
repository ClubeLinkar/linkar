package br.com.clubelinkar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lennon Jesus
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CPF de usuario repetido    ")
public class RepeatedUserCPFException extends RuntimeException {

    public RepeatedUserCPFException() {
        super();
    }

}
