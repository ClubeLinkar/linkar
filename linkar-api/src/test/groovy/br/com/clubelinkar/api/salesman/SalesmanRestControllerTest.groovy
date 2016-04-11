package br.com.clubelinkar.api.salesman

import br.com.clubelinkar.exception.InvalidPasswordException
import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import br.com.clubelinkar.support.mail.IMailService
import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailTemplate
import br.com.clubelinkar.support.security.password.crypto.IPasswordEncrypter
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

import static br.com.clubelinkar.api.salesman.SalesmanMother.anotherSalesman
import static br.com.clubelinkar.api.salesman.SalesmanMother.salesman
import static org.junit.Assert.*
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
class SalesmanRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/salesman"

    @Mock
    SalesmanRepository salesmanRepositoryMock

    @Mock
    ISalesmanValidator salesmanValidatorMock

    @Mock
    IMailService mailServiceMock

    @Mock
    IPasswordEncrypter passwordEncrypterMock

    @InjectMocks
    SalesmanRestController salesmanRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(salesmanRestController).build();

        when(passwordEncrypterMock.encrypt(anyString())).thenReturn("hash")

    }

    @Test
    public void "Deve adicionar um novo vendedor corretamente"() {

        when(salesmanRepositoryMock.save(salesman())).thenReturn(salesman());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(salesman()))
        ).andExpect(status().isOk()).andReturn()

        def savedSalesman = new Gson().fromJson(result.response.contentAsString, Salesman)

        assertNotNull savedSalesman
        assertEquals salesman().name, savedSalesman.name
        assertEquals salesman().email, savedSalesman.email
        assertEquals salesman().cpf, savedSalesman.cpf
        assertEquals salesman().phones, savedSalesman.phones

    }

    @Test
    public void "Deve enviar email de boas vindas para novos vendedores cadastrados com sucesso"() {

        when(salesmanRepositoryMock.save(salesman())).thenReturn(salesman());

        mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(salesman()))
        ).andExpect(status().isOk()).andReturn()


        def mail = new Mail()
                .from("noreply@clubelinkar.com.br")
                .to("lennon.jesus@gmail.com")
                .subject("Você se cadastrou na Linkar!")
                .template(MailTemplate.USER_REGISTRATION)
                .addParameter("name", salesman().name)

        verify(mailServiceMock).send(mail)

    }

    @Test
    public void "Deve criticar vendedor com nome nulo"() {
        def invalidSalesman = salesman()
        invalidSalesman.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar vendedor com email nulo"() {
        def invalidSalesman = salesman()
        invalidSalesman.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar vendedor com email inválido"() {
        def invalidSalesman = salesman()
        invalidSalesman.email = "email.inválido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar vendedor com cpf nulo"() {
        def invalidSalesman = salesman()
        invalidSalesman.cpf = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar vendedor com phone nulo"() {
        def invalidSalesman = salesman()
        invalidSalesman.phones = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    @Ignore
    // implementar validacao de cpf
    public void "Deve criticar vendedor com cpf inválido"() {
        def invalidSalesman = salesman()
        invalidSalesman.cpf = "abc123"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar vendedor com password nula"() {
        def invalidSalesman = salesman()
        invalidSalesman.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidSalesman)
    }

    @Test
    public void "Deve criticar usuário com password inválida"() {
        when(salesmanValidatorMock.validate(salesman(), null)).thenThrow(new InvalidPasswordException());
        postAndExpectBadRequest(BASE_ENDPOINT, salesman())

//        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Não deve permitir novo vendedor com e-mail já existente"() {
        when(salesmanValidatorMock.validate(salesman(), null)).thenThrow(new RepeatedUserEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, salesman())
    }

    @Test
    public void "Não deve permitir novo vendedor com cpf já existente"() {
        when(salesmanValidatorMock.validate(salesman(), null)).thenThrow(new RepeatedUserCPFException());
        postAndExpectBadRequest(BASE_ENDPOINT, salesman())
    }

    @Test
    public void "Deve listar todos os vendedores existentes"() {

        def salesmanListMock = [salesman(), anotherSalesman()]

        when(salesmanRepositoryMock.findAll()).thenReturn(salesmanListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Salesman>>() {}.getType();

        List<Salesman> salesmanList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse salesmanList.empty
        assertEquals 2, salesmanList.size()
        assertEquals salesmanListMock, salesmanList
    }

}