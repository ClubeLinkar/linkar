package br.com.clubelinkar.support

import br.com.clubelinkar.support.mail.Mail
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
@Ignore //FIXME Remover ignore
class MailUnitTest {

    @Test
    @Ignore //FIXME Remover ignore
    public void equalsContract() {
        EqualsVerifier
                .forClass(Mail)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}