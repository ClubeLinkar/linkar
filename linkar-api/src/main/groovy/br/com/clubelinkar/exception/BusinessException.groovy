package br.com.clubelinkar.exception

/**
 * @author Lennon Jesus
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String erro) {
        super(erro)
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause)
    }

    public BusinessException(Throwable cause) {
        super(cause)
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }
}

