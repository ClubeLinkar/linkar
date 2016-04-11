package br.com.clubelinkar.support.security.password.crypto

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

import static org.apache.commons.lang3.StringUtils.isBlank
import static org.junit.Assert.*

@RunWith(JUnit4)
class PasswordEncrypterTest {

    PasswordEncrypter encrypter

    PasswordEncoder encoder

    @Before
    public void setup() {
        encrypter = new PasswordEncrypter()
        encoder = new BCryptPasswordEncoder()
        encrypter.passwordEncoder = encoder
    }

    @Test
    void "encrypt generates a proper hash of the input password"() {
        String plain = "senha"
        String hash = encrypter.encrypt(plain)

        assertNotNull hash
        assertFalse isBlank(hash)
        assertTrue encoder.matches(plain, hash)
    }

    @Test
    void "encrypt returns null if input is null"() {
        String hash = encrypter.encrypt(null)

        assertNull hash
    }
}
