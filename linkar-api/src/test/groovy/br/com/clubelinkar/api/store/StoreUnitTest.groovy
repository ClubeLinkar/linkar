package br.com.clubelinkar.api.store

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
class StoreUnitTest {

    @Test
    public void equalsContract() {
        EqualsVerifier
                .forClass(Store)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

}
