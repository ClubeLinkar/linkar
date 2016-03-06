package br.com.clubelinkar.api.company

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
class CompanyUnitTest {

    @Test
    public void equalsContract() {
        EqualsVerifier
                .forClass(Company)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

}
