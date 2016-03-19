package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
import br.com.clubelinkar.support.crypto.IPasswordEncrypter
import br.com.clubelinkar.support.event.IEvent
import br.com.clubelinkar.support.event.IEventBus
import br.com.clubelinkar.support.event.objects.UserCreatedEvent
import br.com.clubelinkar.test.BaseRestControllerMock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import java.lang.reflect.Type

import static br.com.clubelinkar.api.user.UserMother.carlaVidal
import static br.com.clubelinkar.api.user.UserMother.lennonJesus
import static org.junit.Assert.*
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
class UserRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/user"

    @Mock
    UserRepository userRepositoryMock

    @Mock
    IUserValidator userValidatorMock

    @Mock
    IPasswordEncrypter passwordEncrypterMock

    @Mock
    IEventBus eventBus

    @InjectMocks
    UserRestController userRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();

        when(passwordEncrypterMock.encrypt(anyString())).thenReturn("hash")
    }

    @Test
    public void "Deve adicionar um novo usuário corretamente"() {

        User lennon = lennonJesus()

        when(userRepositoryMock.save(lennonJesus())).thenReturn(lennon);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(lennon))
        ).andExpect(status().isOk()).andReturn()

        def savedUser = new Gson().fromJson(result.response.contentAsString, User)

        assertNotNull savedUser
        assertEquals lennon.name, savedUser.name
        assertEquals lennon.email, savedUser.email
        assertEquals lennon.cpf, savedUser.cpf
        assertEquals lennon.district, savedUser.district
        assertEquals lennon.city, savedUser.city
        assertEquals lennon.state, savedUser.state
        assertEquals lennon.password, savedUser.password

        verify(eventBus, times(1)).publish(Mockito.any(UserCreatedEvent.class))
    }

    @Test
    public void "Deve criticar usuário com nome nulo"() {
        def invalidUser = lennonJesus()
        invalidUser.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com email nulo"() {
        def invalidUser = lennonJesus()
        invalidUser.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com email inválido"() {
        def invalidUser = lennonJesus()
        invalidUser.email = "email.inválido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com cpf nulo"() {
        def invalidUser = lennonJesus()
        invalidUser.cpf = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    @Ignore
    // implementar validacao de cpf
    public void "Deve criticar usuário com cpf inválido"() {
        def invalidUser = lennonJesus()
        invalidUser.cpf = "abc123"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com cidade nula"() {
        def invalidUser = lennonJesus()
        invalidUser.city = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com uf nulo"() {
        def invalidUser = lennonJesus()
        invalidUser.state = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve criticar usuário com password nula"() {
        def invalidUser = lennonJesus()
        invalidUser.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    @Ignore
    // definir e implementar regras de seguranca
    public void "Deve criticar usuário com password inválida"() {
        def invalidUser = lennonJesus()
        invalidUser.password = "123"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Não deve permitir novo usuário com e-mail já existente"() {
        when(userValidatorMock.validate(lennonJesus(), null)).thenThrow(new RepeatedUserEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, lennonJesus())

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Não deve permitir novo usuário com cpf já existente"() {
        when(userValidatorMock.validate(lennonJesus(), null)).thenThrow(new RepeatedUserCPFException());
        postAndExpectBadRequest(BASE_ENDPOINT, lennonJesus())

        verify(eventBus, never()).publish(Mockito.any(IEvent.class))
    }

    @Test
    public void "Deve listar todos os Usuários existentes"() {

        def userListMock = [lennonJesus(), carlaVidal()]

        when(userRepositoryMock.findAll()).thenReturn(userListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<User>>() {}.getType();

        List<User> userList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse userList.empty
        assertEquals 2, userList.size()
        assertEquals userListMock, userList
    }

}