package br.com.clubelinkar.support.filter;

import br.com.clubelinkar.exception.RepeatedUserCPFException;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by lennonjesus on 1/17/16.
 */
@ControllerAdvice
public class RestExceptionHandlerAdvice {

    @ExceptionHandler(RepeatedUserCPFException.class)
    public HttpEntity<Object> blah() {

        return new HttpEntity<Object>(new Object());

    }

}
