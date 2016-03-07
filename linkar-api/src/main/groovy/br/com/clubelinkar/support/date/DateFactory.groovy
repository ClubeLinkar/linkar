package br.com.clubelinkar.support.date

import org.springframework.stereotype.Component

/**
 * @author Lennon Jesus
 */
@Component
public class DateFactory implements IDateFactory {

    public Date now() {
        return new Date()
    }

}
