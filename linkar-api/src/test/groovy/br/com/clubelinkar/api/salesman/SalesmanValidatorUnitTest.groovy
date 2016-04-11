package br.com.clubelinkar.api.salesman

import br.com.clubelinkar.exception.InvalidPasswordException
import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import br.com.clubelinkar.support.security.password.policy.IPasswordPolicy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static br.com.clubelinkar.api.salesman.SalesmanMother.newSalesman
import static br.com.clubelinkar.api.salesman.SalesmanMother.salesman
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class SalesmanValidatorUnitTest {

    @Mock
    def SalesmanRepository salesmanRepositoryMock

    @Mock
    IPasswordPolicy passwordPolicyMock

    @InjectMocks
    def SalesmanValidator salesmanValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.TRUE)
    }

    @Test
    public void "Não deve criticar novo vendedor se e-mail ainda não existir"() {
        when(salesmanRepositoryMock.findByEmail(newSalesman().email)).thenReturn(null)
        salesmanValidator.validate(newSalesman(), null)
        verify(salesmanRepositoryMock).findByEmail(newSalesman().email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar novo vendedor com e-mail repetido"() {
        when(salesmanRepositoryMock.findByEmail(newSalesman().email)).thenReturn(salesman())
        salesmanValidator.validate(newSalesman(), null)
    }

    @Test
    public void "Não deve criticar vendedor com e-mail repetido se a operação for de edição"() {
        when(salesmanRepositoryMock.findByEmail(salesman().email)).thenReturn(salesman())
        salesmanValidator.validate(salesman(), null)
    }

    @Test
    public void "Não deve criticar novo vendedor se cpf ainda não existir"() {
        when(salesmanRepositoryMock.findByCpf(newSalesman().cpf)).thenReturn(null)
        salesmanValidator.validate(newSalesman(), null)
        verify(salesmanRepositoryMock).findByCpf(newSalesman().cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar novo vendedor com cpf repetido"() {
        when(salesmanRepositoryMock.findByCpf(newSalesman().cpf)).thenReturn(salesman())
        salesmanValidator.validate(newSalesman(), null)
    }

    @Test
    public void "Não deve criticar vendedor com cpf repetido se a operação for de edição"() {
        when(salesmanRepositoryMock.findByCpf(salesman().cpf)).thenReturn(salesman())
        salesmanValidator.validate(salesman(), null)
    }

    @Test(expected = InvalidPasswordException)
    public void "Deve criticar se a senha nao estiver de acordo com as regras de senhas"() {
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.FALSE)
        salesmanValidator.validate(salesman(), null)
    }
}
