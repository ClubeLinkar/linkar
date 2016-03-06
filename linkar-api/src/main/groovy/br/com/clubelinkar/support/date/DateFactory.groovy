package br.com.clubelinkar.support.date

import br.com.clubelinkar.support.date.IDateFactory
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
