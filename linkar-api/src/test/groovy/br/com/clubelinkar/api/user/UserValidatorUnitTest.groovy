package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static br.com.clubelinkar.api.user.UserMother.*

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class UserValidatorUnitTest {

    @Mock
    def UserRepository userRepositoryMock

    @InjectMocks
    def UserValidator userValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    public void "Não deve criticar usuário se e-mail ainda não existir"() {
        when(userRepositoryMock.findByEmail(lennonJesus().email)).thenReturn(null)
        userValidator.validate(lennonJesus(), null)
        verify(userRepositoryMock).findByEmail(lennonJesus().email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar usuário com e-mail repetido"() {
        when(userRepositoryMock.findByEmail(lennonJesus().email)).thenReturn(lennonJesus())
        userValidator.validate(lennonJesus(), null)
    }

    @Test
    public void "Não deve criticar usuário se cpf ainda não existir"() {
        when(userRepositoryMock.findByCpf(lennonJesus().cpf)).thenReturn(null)
        userValidator.validate(lennonJesus(), null)
        verify(userRepositoryMock).findByCpf(lennonJesus().cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar usuário com cpf repetido"() {
        when(userRepositoryMock.findByCpf(lennonJesus().cpf)).thenReturn(lennonJesus())
        userValidator.validate(lennonJesus(), null)
    }

}
