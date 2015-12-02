package br.com.clubelinkar.api.store

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.test.StoreObjectMother
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static br.com.clubelinkar.test.StoreObjectMother.aStore
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * @author Lennon Jesus
 */
@RunWith(JUnit4)
public class StoreValidatorUnitTest {

    @Mock
    def StoreRepository storeRepositoryMock

    @InjectMocks
    def StoreValidator storeValidator

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    public void "Não deve criticar loja se e-mail ainda não existir"() {
        when(storeRepositoryMock.findByEmail(aStore.email)).thenReturn(null)
        storeValidator.validate(aStore, null)
        verify(storeRepositoryMock).findByEmail(aStore.email)
    }

    @Test(expected = RepeatedStoreEmailException)
    public void "Deve criticar loja com e-mail repetido"() {
        when(storeRepositoryMock.findByEmail(aStore.email)).thenReturn(aStore)
        storeValidator.validate(aStore, null)
    }

    @Test
    public void "Não deve criticar loja se cnpj ainda não existir"() {
        when(storeRepositoryMock.findByCnpj(aStore.cnpj)).thenReturn(null)
        storeValidator.validate(aStore, null)
        verify(storeRepositoryMock).findByCnpj(aStore.cnpj)
    }

    @Test(expected = RepeatedStoreCNPJException)
    public void "Deve criticar loja com cnpj repetido"() {
        when(storeRepositoryMock.findByCnpj(aStore.cnpj)).thenReturn(aStore)
        storeValidator.validate(aStore, null)
    }

}