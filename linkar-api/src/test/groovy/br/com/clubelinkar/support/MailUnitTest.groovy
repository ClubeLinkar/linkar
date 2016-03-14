package br.com.clubelinkar.support

import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailTemplate
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.Ignore
import org.junit.Test

import static junit.framework.Assert.assertEquals

/**
 * @author Lennon Jesus
 */
class MailUnitTest {

    @Test
    @Ignore //FIXME Remover ignore
    public void equalsContract() {
        EqualsVerifier
                .forClass(Mail)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    void "newCompany usa template STORE_REGISTRATION"() {
        Mail mail = Mail.newCompany()

        assertEquals "Sua loja está na Linkar!", mail.subject
        assertEquals MailTemplate.STORE_REGISTRATION, mail.template
        assertEquals "noreply@clubelinkar.com.br", mail.from
    }

    @Test
    void "newUser usa template USER_REGISTRATION"() {
        Mail mail = Mail.newUser()

        assertEquals "Você se cadastrou na Linkar!", mail.subject
        assertEquals MailTemplate.USER_REGISTRATION, mail.template
        assertEquals "noreply@clubelinkar.com.br", mail.from
    }
}