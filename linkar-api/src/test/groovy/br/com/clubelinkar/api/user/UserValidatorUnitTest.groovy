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
import static br.com.clubelinkar.test.UserObjectMother.*

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
        when(userRepositoryMock.findByEmail(anUser.email)).thenReturn(null)
        userValidator.validate(anUser, null)
        verify(userRepositoryMock).findByEmail(anUser.email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar usuário com e-mail repetido"() {
        when(userRepositoryMock.findByEmail(anUser.email)).thenReturn(anUser)
        userValidator.validate(anUser, null)
    }

    @Test
    public void "Não deve criticar usuário se cpf ainda não existir"() {
        when(userRepositoryMock.findByCpf(anUser.cpf)).thenReturn(null)
        userValidator.validate(anUser, null)
        verify(userRepositoryMock).findByCpf(anUser.cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar usuário com cpf repetido"() {
        when(userRepositoryMock.findByCpf(anUser.cpf)).thenReturn(anUser)
        userValidator.validate(anUser, null)
    }

}
