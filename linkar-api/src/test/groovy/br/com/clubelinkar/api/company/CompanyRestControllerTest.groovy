package br.com.clubelinkar.api.company

import br.com.clubelinkar.api.user.User
import br.com.clubelinkar.exception.InvalidPasswordException
import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.support.crypto.IPasswordEncrypter
import br.com.clubelinkar.support.event.IEvent
import br.com.clubelinkar.support.event.IEventBus
import br.com.clubelinkar.support.event.objects.CompanyCreatedEvent
import br.com.clubelinkar.support.mail.Mail
import br.com.clubelinkar.support.mail.MailTemplate
import br.com.clubelinkar.support.security.service.ILoggedUserService
import br.com.clubelinkar.test.BaseRestControllerMock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import java.lang.reflect.Type

import static br.com.clubelinkar.api.company.CompanyMother.*
import static org.junit.Assert.*
import static org.mockito.Matchers.any
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class CompanyRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/company"

    @Mock
    CompanyRepository companyRepositoryMock

    @Mock
    ICompanyValidator companyValidatorMock

    @Mock
    IEventBus eventBusMock

    @Mock
    ILoggedUserService loggedUserServiceMock

    @Mock
    IPasswordEncrypter passwordEncrypterMock

    @InjectMocks
    CompanyRestController companyRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(companyRestController).build();

        when(loggedUserServiceMock.currentLoggedUser).thenReturn(new User(id: "criador_id"))

        when(passwordEncrypterMock.encrypt(anyString())).thenReturn("hash")

    }

    @Test
    public void "Deve cadastrar uma empresa corretamente"() {

        when(companyRepositoryMock.save(allMotos())).thenReturn(allMotos());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotos()))
        ).andExpect(status().isOk()).andReturn()

        def company = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull company
        assertEquals allMotos().name, company.name
        assertEquals allMotos().description, company.description
        assertEquals allMotos().address, company.address
        assertEquals allMotos().phones, company.phones
        assertEquals allMotos().url, company.url
        assertEquals allMotos().email, company.email
        assertEquals allMotos().createdBy, company.createdBy

        verify(eventBusMock).publish(any(CompanyCreatedEvent))
        verify(loggedUserServiceMock).currentLoggedUser
    }

    @Test
    public void "Deve cadastrar uma empresa com categorias corretamente"() {

        when(companyRepositoryMock.save(allMotosWithCategories())).thenReturn(allMotosWithCategories());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotosWithCategories()))
        ).andExpect(status().isOk()).andReturn()

        def company = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull company
        assertEquals allMotosWithCategories().name, company.name
        assertEquals allMotosWithCategories().description, company.description
        assertEquals allMotosWithCategories().address, company.address
        assertEquals allMotosWithCategories().phones, company.phones
        assertEquals allMotosWithCategories().url, company.url
        assertEquals allMotosWithCategories().email, company.email
        assertEquals allMotosWithCategories().categories, company.categories

        verify(eventBusMock).publish(any(CompanyCreatedEvent))
        verify(loggedUserServiceMock).currentLoggedUser
    }

    @Test
    public void "Deve cadastrar uma empresa com categorias repetidas corretamente"() {

        when(companyRepositoryMock.save(allMotosWithRepeatedCategories())).thenReturn(allMotosWithRepeatedCategories());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotosWithRepeatedCategories()))
        ).andExpect(status().isOk()).andReturn()

        def company = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull company
        assertEquals allMotosWithRepeatedCategories().name, company.name
        assertEquals allMotosWithRepeatedCategories().description, company.description
        assertEquals allMotosWithRepeatedCategories().address, company.address
        assertEquals allMotosWithRepeatedCategories().phones, company.phones
        assertEquals allMotosWithRepeatedCategories().url, company.url
        assertEquals allMotosWithRepeatedCategories().email, company.email
        assertEquals 2, company.categories.size()
        assertEquals(["c1", "c2"] as SortedSet, company.categories)

        verify(eventBusMock).publish(any(CompanyCreatedEvent))
        verify(loggedUserServiceMock).currentLoggedUser
    }

//    @Test
    // FIXME
    public void "Deve enviar email de boas vindas para novas empresas cadastradas com sucesso"() {

        when(companyRepositoryMock.save(allMotos())).thenReturn(allMotos());

        mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotos()))
        ).andExpect(status().isOk()).andReturn()

        def mail = new Mail()
                .from("noreply@clubelinkar.com.br")
                .to("allmotos@allmotos.com.br")
                .subject("Sua empresa está na Linkar!")
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", allMotos().name)

//        verify(mailServiceMock).send(mail)

    }

    @Test
    public void "Deve cadastrar uma empresa com responsavel corretamente"() {

        when(companyRepositoryMock.save(allMotosWithResponsible())).thenReturn(allMotosWithResponsible());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotosWithResponsible()))
        ).andExpect(status().isOk()).andReturn()

        def company = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull company
        assertEquals allMotosWithResponsible().name, company.name
        assertEquals allMotosWithResponsible().description, company.description
        assertEquals allMotosWithResponsible().address, company.address
        assertEquals allMotosWithResponsible().phones, company.phones
        assertEquals allMotosWithResponsible().url, company.url
        assertEquals allMotosWithResponsible().email, company.email
        assertEquals allMotosWithResponsible().responsiblePerson, company.responsiblePerson
    }

    @Test
    public void "Deve criticar empresa com cnpj nulo"() {
        def invalidStore = allMotos()
        invalidStore.cnpj = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com password nulo"() {
        def invalidStore = allMotos()
        invalidStore.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com password inválido"() {
        when(companyValidatorMock.validate(allMotos(), null)).thenThrow(new InvalidPasswordException());
        postAndExpectBadRequest(BASE_ENDPOINT, allMotos())

        verify(eventBusMock, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar empresa com name nulo"() {
        def invalidStore = allMotos()
        invalidStore.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com description nulo"() {
        def invalidStore = allMotos()
        invalidStore.description = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com address nulo"() {
        def invalidStore = allMotos()
        invalidStore.address = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com phones nulo"() {
        def invalidStore = allMotos()
        invalidStore.phones = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com email nulo"() {
        def invalidStore = allMotos()
        invalidStore.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve criticar empresa com email inválido"() {
        def invalidStore = allMotos()
        invalidStore.email = "email.invalido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Não deve permitir empresa com mesmo e-mail"() {
        when(companyValidatorMock.validate(allMotos(), null)).thenThrow(new RepeatedStoreEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, allMotos())
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Não deve permitir empresa com mesmo cnpj"() {
        when(companyValidatorMock.validate(allMotos(), null)).thenThrow(new RepeatedStoreCNPJException());
        postAndExpectBadRequest(BASE_ENDPOINT, allMotos())
        verify(eventBusMock, never()).publish(any(CompanyCreatedEvent))
    }

    @Test
    public void "Deve listar corretamente todas as empresas cadastradas no sistema"() {

        def companyListMock = [allMotos(), homaMotos()]

        when(companyRepositoryMock.findAll()).thenReturn(companyListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Company>>() {}.getType();

        List<Company> companysList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse companysList.empty
        assertEquals companyListMock, companysList

    }


}
