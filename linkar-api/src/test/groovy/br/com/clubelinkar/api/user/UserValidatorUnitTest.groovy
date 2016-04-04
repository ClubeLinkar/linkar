package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.InvalidPasswordException
import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static br.com.clubelinkar.api.user.UserMother.felipe
import static br.com.clubelinkar.api.user.UserMother.lennonJesus
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class UserValidatorUnitTest {

    @Mock
    UserRepository userRepositoryMock

    @Mock
    IPasswordPolicy passwordPolicyMock

    @InjectMocks
    UserValidator userValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.TRUE)
    }

    @Test
    public void "Não deve criticar novo usuário se e-mail ainda não existir"() {
        when(userRepositoryMock.findByEmail(felipe().email)).thenReturn(null)
        userValidator.validate(felipe(), null)
        verify(userRepositoryMock).findByEmail(felipe().email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar novo usuário com e-mail repetido"() {
        when(userRepositoryMock.findByEmail(felipe().email)).thenReturn(lennonJesus())
        userValidator.validate(felipe(), null)
    }

    @Test
    public void "Não deve criticar usuário com e-mail repetido se a operação for de edição"() {
        when(userRepositoryMock.findByEmail(lennonJesus().email)).thenReturn(lennonJesus())
        userValidator.validate(lennonJesus(), null)
    }

    @Test
    public void "Não deve criticar novo usuário se cpf ainda não existir"() {
        when(userRepositoryMock.findByCpf(felipe().cpf)).thenReturn(null)
        userValidator.validate(felipe(), null)
        verify(userRepositoryMock).findByCpf(felipe().cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar novo usuário com cpf repetido"() {
        when(userRepositoryMock.findByCpf(felipe().cpf)).thenReturn(lennonJesus())
        userValidator.validate(felipe(), null)
    }

    @Test
    public void "Não deve criticar usuário com cpf repetido se a operação for de edição"() {
        when(userRepositoryMock.findByCpf(lennonJesus().cpf)).thenReturn(lennonJesus())
        userValidator.validate(lennonJesus(), null)
    }

    @Test(expected = InvalidPasswordException)
    public void "Deve criticar se a senha nao estiver de acordo com as regras de senhas"() {
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.FALSE)
        userValidator.validate(lennonJesus(), null)
    }

}
