package br.com.clubelinkar.api.company

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailService
import br.com.clubelinkar.support.mail.MailTemplate
import br.com.clubelinkar.test.BaseRestControllerMock
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

import static br.com.clubelinkar.test.CompanyObjectMother.aStore
import static br.com.clubelinkar.test.CompanyObjectMother.getAnotherStore
import static org.junit.Assert.*
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class CompanyRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/company"

    @Mock
    def CompanyRepository storeRepositoryMock

    @Mock
    def CompanyValidator storeValidatorMock

    @Mock
    def MailService mailServiceMock

    @InjectMocks
    def CompanyRestController storeRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(storeRestController).build();
    }

    @Test
    public void "Deve cadastrar uma loja corretamente"() {

        when(storeRepositoryMock.save(aStore)).thenReturn(aStore);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aStore))
        ).andExpect(status().isOk()).andReturn()

        def store = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull store
        assertEquals aStore.name, store.name
        assertEquals aStore.description, store.description
        assertEquals aStore.address, store.address
        assertEquals aStore.phones, store.phones
        assertEquals aStore.url, store.url
        assertEquals aStore.email, store.email

    }

    @Test
    public void "Deve enviar email de boas vindas para novas lojas cadastradas com sucesso"() {

        when(storeRepositoryMock.save(aStore)).thenReturn(aStore);

        mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aStore))
        ).andExpect(status().isOk()).andReturn()


        def mail = new Mail()
                .from("noreply@clubelinkar.com.br")
                .to("allmotos@allmotos.com.br")
                .subject("Sua loja está na Linkar!")
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", aStore.name)

        verify(mailServiceMock).send(mail)

    }

    @Test
    public void "Deve criticar loja com cnpj nulo"() {
        def invalidStore = aStore
        invalidStore.cnpj = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com password nulo"() {
        def invalidStore = aStore
        invalidStore.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    @Ignore
    // TODO implementar validacao de passwrod
    public void "Deve criticar loja com password inválido"() {
        def invalidStore = aStore
        invalidStore.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com name nulo"() {
        def invalidStore = aStore
        invalidStore.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com description nulo"() {
        def invalidStore = aStore
        invalidStore.description = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com address nulo"() {
        def invalidStore = aStore
        invalidStore.address = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com phones nulo"() {
        def invalidStore = aStore
        invalidStore.phones = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com email nulo"() {
        def invalidStore = aStore
        invalidStore.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar loja com email inválido"() {
        def invalidStore = aStore
        invalidStore.email = "email.invalido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Não deve permitir loja com mesmo e-mail"() {
        when(storeValidatorMock.validate(aStore, null)).thenThrow(new RepeatedStoreEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, aStore)
    }

    @Test
    public void "Não deve permitir loja com mesmo cnpj"() {
        when(storeValidatorMock.validate(aStore, null)).thenThrow(new RepeatedStoreCNPJException());
        postAndExpectBadRequest(BASE_ENDPOINT, aStore)
    }

    @Test
    public void "Deve listar corretamente todas as lojas cadastradas no sistema"() {

        def storeListMock = [aStore, anotherStore]

        when(storeRepositoryMock.findAll()).thenReturn(storeListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Company>>() {}.getType();

        List<Company> storesList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse storesList.empty
        assertEquals storeListMock, storesList

    }


}
