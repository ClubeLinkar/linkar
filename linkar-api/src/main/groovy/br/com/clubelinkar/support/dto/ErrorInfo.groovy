package br.com.clubelinkar.support.dto

/**
 * Informações dos error gerados no back end
 * @author Lennon Jesus
 */
public class ErrorInfo {

    public final String url

    public final String exception

    public ErrorInfo(String url, Throwable exception) {
        this.url = url
        this.exception = exception.getMessage()
    }
}
