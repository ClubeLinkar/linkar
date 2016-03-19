package br.com.clubelinkar.api.company

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static br.com.clubelinkar.api.company.CompanyMother.allMotos
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class CompanyValidatorUnitTest {

    @Mock
    def CompanyRepository companyRepositoryMock

    @InjectMocks
    def CompanyValidator storeValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    public void "Não deve criticar loja se e-mail ainda não existir"() {
        when(companyRepositoryMock.findByEmail(allMotos().email)).thenReturn(null)
        storeValidator.validate(allMotos(), null)
        verify(companyRepositoryMock).findByEmail(allMotos().email)
    }

    @Test(expected = RepeatedStoreEmailException)
    public void "Deve criticar loja com e-mail repetido"() {
        when(companyRepositoryMock.findByEmail(allMotos().email)).thenReturn(allMotos())
        storeValidator.validate(allMotos(), null)
    }

    @Test
    public void "Não deve criticar loja se cnpj ainda não existir"() {
        when(companyRepositoryMock.findByCnpj(allMotos().cnpj)).thenReturn(null)
        storeValidator.validate(allMotos(), null)
        verify(companyRepositoryMock).findByCnpj(allMotos().cnpj)
    }

    @Test(expected = RepeatedStoreCNPJException)
    public void "Deve criticar loja com cnpj repetido"() {
        when(companyRepositoryMock.findByCnpj(allMotos().cnpj)).thenReturn(allMotos())
        storeValidator.validate(allMotos(), null)
    }

}
