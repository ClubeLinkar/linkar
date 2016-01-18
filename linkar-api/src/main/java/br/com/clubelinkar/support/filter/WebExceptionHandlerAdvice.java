package br.com.clubelinkar.support.filter;

import br.com.clubelinkar.exception.BusinessException;
import br.com.clubelinkar.support.dto.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lennonjesus
 */
@ControllerAdvice
public class WebExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandlerAdvice.class);

    /**
     * Relança a exceçao para que o Spring logue
     * Ex.: ao criar um DTO usado com @RequestBody sem o construtor padrao, ocorre um erro nao logado pelo Spring
     * Relançando a excecao atraves deste metodo o erro eh devidamente logado (mesmo sem utilizar o Logger desta classe)
     *
     * @param exception
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    void handleException(Exception exception) throws Exception {
        throw exception;
    }

    /**
     * Tratamento de BusinessException
     *
     * @return JSON com informaçoes sobre a excecao gerada
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    HttpEntity<ErrorInfo> handleBusinessException(HttpServletRequest req, BusinessException exception) {
        LOGGER.error(req.getRequestURL().toString(), exception);
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), exception);

        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);

        return new ResponseEntity<>(errorInfo, responseStatus.value());
    }

    /**
     * Tratamento de BusinessException
     *
     * @return JSON com informaçoes sobre a excecao gerada
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    HttpEntity<ErrorInfo> handleValidationException(HttpServletRequest req, MethodArgumentNotValidException exception) {
        LOGGER.error(req.getRequestURL().toString(), exception);
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), exception);

        return new HttpEntity<>(errorInfo);
    }


}
