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
import static br.com.clubelinkar.test.SalesmanObjectMother.aNewSalesman
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
    public void "Não deve criticar novo vendedor se e-mail ainda não existir"() {
        when(salesmanRepositoryMock.findByEmail(aNewSalesman.email)).thenReturn(null)
        salesmanValidator.validate(aNewSalesman, null)
        verify(salesmanRepositoryMock).findByEmail(aNewSalesman.email)
    }

    @Test(expected = RepeatedUserEmailException)
    public void "Deve criticar novo vendedor com e-mail repetido"() {
        when(salesmanRepositoryMock.findByEmail(aNewSalesman.email)).thenReturn(aSalesman)
        salesmanValidator.validate(aNewSalesman, null)
    }

    @Test
    public void "Não deve criticar vendedor com e-mail repetido se a operação for de edição"() {
        when(salesmanRepositoryMock.findByEmail(aSalesman.email)).thenReturn(aSalesman)
        salesmanValidator.validate(aSalesman, null)
    }

    @Test
    public void "Não deve criticar novo vendedor se cpf ainda não existir"() {
        when(salesmanRepositoryMock.findByCpf(aNewSalesman.cpf)).thenReturn(null)
        salesmanValidator.validate(aNewSalesman, null)
        verify(salesmanRepositoryMock).findByCpf(aNewSalesman.cpf)
    }

    @Test(expected = RepeatedUserCPFException)
    public void "Deve criticar novo vendedor com cpf repetido"() {
        when(salesmanRepositoryMock.findByCpf(aNewSalesman.cpf)).thenReturn(aSalesman)
        salesmanValidator.validate(aNewSalesman, null)
    }

    @Test
    public void "Não deve criticar vendedor com cpf repetido se a operação for de edição"() {
        when(salesmanRepositoryMock.findByCpf(aSalesman.cpf)).thenReturn(aSalesman)
        salesmanValidator.validate(aSalesman, null)
    }
}
