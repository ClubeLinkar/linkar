package br.com.clubelinkar.api.user

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
class UserUnitTest {

    @Test
    public void equalsContract() {
        EqualsVerifier
                .forClass(User)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

}
