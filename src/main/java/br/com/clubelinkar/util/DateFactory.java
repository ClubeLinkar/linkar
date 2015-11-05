package br.com.clubelinkar.util;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lennon Jesus
 */
@Component
public class DateFactory {

    public Date now() {
        return new Date();
    }

}
