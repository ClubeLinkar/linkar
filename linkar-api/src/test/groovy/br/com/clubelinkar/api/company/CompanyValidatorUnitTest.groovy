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

import static br.com.clubelinkar.test.CompanyObjectMother.getaCompany
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
        when(companyRepositoryMock.findByEmail(aCompany.email)).thenReturn(null)
        storeValidator.validate(aCompany, null)
        verify(companyRepositoryMock).findByEmail(aCompany.email)
    }

    @Test(expected = RepeatedStoreEmailException)
    public void "Deve criticar loja com e-mail repetido"() {
        when(companyRepositoryMock.findByEmail(aCompany.email)).thenReturn(aCompany)
        storeValidator.validate(aCompany, null)
    }

    @Test
    public void "Não deve criticar loja se cnpj ainda não existir"() {
        when(companyRepositoryMock.findByCnpj(aCompany.cnpj)).thenReturn(null)
        storeValidator.validate(aCompany, null)
        verify(companyRepositoryMock).findByCnpj(aCompany.cnpj)
    }

    @Test(expected = RepeatedStoreCNPJException)
    public void "Deve criticar loja com cnpj repetido"() {
        when(companyRepositoryMock.findByCnpj(aCompany.cnpj)).thenReturn(aCompany)
        storeValidator.validate(aCompany, null)
    }

}
