package br.com.clubelinkar.api.company

import br.com.clubelinkar.exception.RepeatedStoreCNPJException
import br.com.clubelinkar.exception.RepeatedStoreEmailException
import br.com.clubelinkar.support.mail.IMailService
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

import static br.com.clubelinkar.test.CompanyObjectMother.getaCompany
import static br.com.clubelinkar.test.CompanyObjectMother.getAnotherCompany
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
    CompanyRepository companyRepositoryMock

    @Mock
    ICompanyValidator companyValidatorMock

    @Mock
    IMailService mailServiceMock

    @InjectMocks
    CompanyRestController companyRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(companyRestController).build();
    }

    @Test
    public void "Deve cadastrar uma empresa corretamente"() {

        when(companyRepositoryMock.save(aCompany)).thenReturn(aCompany);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aCompany))
        ).andExpect(status().isOk()).andReturn()

        def company = new Gson().fromJson(result.response.contentAsString, Company)

        assertNotNull company
        assertEquals aCompany.name, company.name
        assertEquals aCompany.description, company.description
        assertEquals aCompany.address, company.address
        assertEquals aCompany.phones, company.phones
        assertEquals aCompany.url, company.url
        assertEquals aCompany.email, company.email

    }

    @Test
    public void "Deve enviar email de boas vindas para novas empresas cadastradas com sucesso"() {

        when(companyRepositoryMock.save(aCompany)).thenReturn(aCompany);

        mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aCompany))
        ).andExpect(status().isOk()).andReturn()

        def mail = new Mail()
                .from("noreply@clubelinkar.com.br")
                .to("allmotos@allmotos.com.br")
                .subject("Sua empresa está na Linkar!")
                .template(MailTemplate.STORE_REGISTRATION)
                .addParameter("name", aCompany.name)

        verify(mailServiceMock).send(mail)

    }

    @Test
    public void "Deve criticar empresa com cnpj nulo"() {
        def invalidStore = aCompany
        invalidStore.cnpj = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com password nulo"() {
        def invalidStore = aCompany
        invalidStore.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    @Ignore
    // TODO implementar validacao de passwrod
    public void "Deve criticar empresa com password inválido"() {
        def invalidStore = aCompany
        invalidStore.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com name nulo"() {
        def invalidStore = aCompany
        invalidStore.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com description nulo"() {
        def invalidStore = aCompany
        invalidStore.description = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com address nulo"() {
        def invalidStore = aCompany
        invalidStore.address = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com phones nulo"() {
        def invalidStore = aCompany
        invalidStore.phones = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com email nulo"() {
        def invalidStore = aCompany
        invalidStore.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Deve criticar empresa com email inválido"() {
        def invalidStore = aCompany
        invalidStore.email = "email.invalido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidStore)
    }

    @Test
    public void "Não deve permitir empresa com mesmo e-mail"() {
        when(companyValidatorMock.validate(aCompany, null)).thenThrow(new RepeatedStoreEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, aCompany)
    }

    @Test
    public void "Não deve permitir empresa com mesmo cnpj"() {
        when(companyValidatorMock.validate(aCompany, null)).thenThrow(new RepeatedStoreCNPJException());
        postAndExpectBadRequest(BASE_ENDPOINT, aCompany)
    }

    @Test
    public void "Deve listar corretamente todas as empresas cadastradas no sistema"() {

        def companyListMock = [aCompany, anotherCompany]

        when(companyRepositoryMock.findAll()).thenReturn(companyListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Company>>() {}.getType();

        List<Company> companysList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse companysList.empty
        assertEquals companyListMock, companysList

    }


}
