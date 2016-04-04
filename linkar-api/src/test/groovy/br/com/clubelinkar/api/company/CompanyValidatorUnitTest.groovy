package br.com.clubelinkar.api.company

import br.com.clubelinkar.api.user.IPasswordPolicy
import br.com.clubelinkar.exception.InvalidPasswordException
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
import static br.com.clubelinkar.api.company.CompanyMother.novaLoja
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class CompanyValidatorUnitTest {

    @Mock
    def CompanyRepository companyRepositoryMock

    @Mock
    IPasswordPolicy passwordPolicyMock

    @InjectMocks
    def CompanyValidator companyValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.TRUE)
    }

    @Test
    public void "Não deve criticar nova empresa se e-mail ainda não existir"() {
        when(companyRepositoryMock.findByEmail(novaLoja().email)).thenReturn(null)
        companyValidator.validate(novaLoja(), null)
        verify(companyRepositoryMock).findByEmail(novaLoja().email)
    }

    @Test(expected = RepeatedStoreEmailException)
    public void "Deve criticar nova empresa com e-mail repetido"() {
        when(companyRepositoryMock.findByEmail(novaLoja().email)).thenReturn(allMotos())
        companyValidator.validate(novaLoja(), null)
    }

    @Test
    public void "Não deve criticar empresa com e-mail repetido se a operação for de edição"() {
        when(companyRepositoryMock.findByEmail(allMotos().email)).thenReturn(allMotos())
        companyValidator.validate(allMotos(), null)
    }

    @Test
    public void "Não deve criticar nova empresa se cnpj ainda não existir"() {
        when(companyRepositoryMock.findByCnpj(novaLoja().cnpj)).thenReturn(null)
        companyValidator.validate(novaLoja(), null)
        verify(companyRepositoryMock).findByCnpj(novaLoja().cnpj)
    }

    @Test(expected = RepeatedStoreCNPJException)
    public void "Deve criticar nova empresa com cnpj repetido"() {
        when(companyRepositoryMock.findByCnpj(novaLoja().cnpj)).thenReturn(allMotos())
        companyValidator.validate(novaLoja(), null)
    }

    @Test
    public void "Não eve criticar empresa com cnpj repetido se a operação for de edição"() {
        when(companyRepositoryMock.findByCnpj(allMotos().cnpj)).thenReturn(allMotos())
        companyValidator.validate(allMotos(), null)
    }

    @Test(expected = InvalidPasswordException)
    public void "Deve criticar se a senha nao estiver de acordo com as regras de senhas"() {
        when(passwordPolicyMock.matches(anyString())).thenReturn(Boolean.FALSE)
        companyValidator.validate(novaLoja(), null)
    }
}
