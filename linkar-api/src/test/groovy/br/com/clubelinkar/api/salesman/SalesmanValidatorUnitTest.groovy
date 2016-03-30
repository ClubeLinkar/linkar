package br.com.clubelinkar.api.salesman

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static br.com.clubelinkar.test.SalesmanObjectMother.aSalesman
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class SalesmanValidatorUnitTest {

    @Mock
    def SalesmanRepository salesmanRepositoryMock

    @InjectMocks
    def SalesmanValidator salesmanValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    public void "Não deve criticar vendedor se e-mail ainda não existir"() {
        when(salesmanRepositoryMock.findByEmail(aSalesman.email)).thenReturn(null)
        salesmanValidator.validate(aSalesman, null)
        verify(salesmanRepositoryMock).findByEmail(aSalesman.email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar vendedor com e-mail repetido"() {
        when(salesmanRepositoryMock.findByEmail(aSalesman.email)).thenReturn(aSalesman)
        salesmanValidator.validate(aSalesman, null)
    }

    @Test
    public void "Não deve criticar vendedor se cpf ainda não existir"() {
        when(salesmanRepositoryMock.findByCpf(aSalesman.cpf)).thenReturn(null)
        salesmanValidator.validate(aSalesman, null)
        verify(salesmanRepositoryMock).findByCpf(aSalesman.cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar vendedor com cpf repetido"() {
        when(salesmanRepositoryMock.findByCpf(aSalesman.cpf)).thenReturn(aSalesman)
        salesmanValidator.validate(aSalesman, null)
    }

}
