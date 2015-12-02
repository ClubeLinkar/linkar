package br.com.clubelinkar.api.store

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.test.BaseRestControllerTest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import java.lang.reflect.Type

import static br.com.clubelinkar.test.StoreObjectMother.aStore
import static br.com.clubelinkar.test.StoreObjectMother.getAnotherStore
import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class StoreRestControllerTest extends BaseRestControllerTest {

    @Mock
    def StoreRepository storeRepositoryMock

    @Mock
    def StoreValidator storeValidatorMock

    @InjectMocks
    def StoreRestController storeRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(storeRestController).build();
    }

    @Test
    public void "Deve cadastrar uma loja corretamente"() {

        when(storeRepositoryMock.save(aStore)).thenReturn(aStore);

        def result = mockMvc.perform(post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aStore))
        ).andExpect(status().isOk()).andReturn()

        def store = new Gson().fromJson(result.response.contentAsString, Store)

        assertNotNull store
        assertEquals aStore.name, store.name
        assertEquals aStore.description, store.description
        assertEquals aStore.address, store.address
        assertEquals aStore.phones, store.phones
        assertEquals aStore.url, store.url
        assertEquals aStore.email, store.email

    }

    @Test
    public void "Deve criticar loja com cnpj nulo"() {
        def invalidStore = aStore
        invalidStore.cnpj = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com password nulo"() {
        def invalidStore = aStore
        invalidStore.password = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    @Ignore
    // TODO implementar validacao de passwrod
    public void "Deve criticar loja com password inválido"() {
        def invalidStore = aStore
        invalidStore.password = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com name nulo"() {
        def invalidStore = aStore
        invalidStore.name = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com description nulo"() {
        def invalidStore = aStore
        invalidStore.description = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com address nulo"() {
        def invalidStore = aStore
        invalidStore.address = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com phones nulo"() {
        def invalidStore = aStore
        invalidStore.phones = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com email nulo"() {
        def invalidStore = aStore
        invalidStore.email = null
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Deve criticar loja com email inválido"() {
        def invalidStore = aStore
        invalidStore.email = "email.invalido"
        postAndExpectBadRequest("/store", invalidStore)
    }

    @Test
    public void "Não deve permitir loja com mesmo e-mail"() {
        when(storeValidatorMock.validate(aStore, null)).thenThrow(new RepeatedStoreEmailException());
        postAndExpectBadRequest("/store", aStore)
    }

    @Test
    public void "Não deve permitir loja com mesmo cnpj"() {
        when(storeValidatorMock.validate(aStore, null)).thenThrow(new RepeatedStoreCNPJException());
        postAndExpectBadRequest("/store", aStore)
    }

    @Test
    public void "Deve listar corretamente todas as lojas cadastradas no sistema"() {

        def storeListMock = [aStore, anotherStore]

        when(storeRepositoryMock.findAll()).thenReturn(storeListMock)

        def result = mockMvc.perform(get("/store")).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Store>>() {}.getType();

        List<Store> storesList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse storesList.empty
        assertEquals storeListMock, storesList

    }


}
