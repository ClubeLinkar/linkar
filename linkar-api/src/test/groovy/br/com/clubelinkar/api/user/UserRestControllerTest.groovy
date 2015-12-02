package br.com.clubelinkar.api.user

import br.com.clubelinkar.exception.RepeatedUserCPFException
import br.com.clubelinkar.exception.RepeatedUserEmailException
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

import static br.com.clubelinkar.test.UserObjectMother.anUser
import static br.com.clubelinkar.test.UserObjectMother.anotherUser
import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
class UserRestControllerTest extends BaseRestControllerTest {

    private static final String BASE_ENDPOINT = "/user"

    @Mock
    def UserRepository userRepositoryMock

    @Mock
    def UserValidator userValidatorMock

    @InjectMocks
    def UserRestController userRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void "Deve adicionar um novo usuário corretamente"() {

        when(userRepositoryMock.save(anUser)).thenReturn(anUser);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(anUser))
        ).andExpect(status().isOk()).andReturn()

        def savedUser = new Gson().fromJson(result.response.contentAsString, User)

        assertNotNull savedUser
        assertEquals anUser.name, savedUser.name
        assertEquals anUser.email, savedUser.email
        assertEquals anUser.cpf, savedUser.cpf
        assertEquals anUser.district, savedUser.district
        assertEquals anUser.city, savedUser.city
        assertEquals anUser.state, savedUser.state
        assertEquals anUser.password, savedUser.password

    }

    @Test
    public void "Deve criticar usuário com nome nulo"() {
        def invalidUser = anUser
        invalidUser.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com email nulo"() {
        def invalidUser = anUser
        invalidUser.email = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com email inválido"() {
        def invalidUser = anUser
        invalidUser.email = "email.inválido"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com cpf nulo"() {
        def invalidUser = anUser
        invalidUser.cpf = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    @Ignore
    // implementar validacao de cpf
    public void "Deve criticar usuário com cpf inválido"() {
        def invalidUser = anUser
        invalidUser.cpf = "abc123"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com cidade nula"() {
        def invalidUser = anUser
        invalidUser.city = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com uf nulo"() {
        def invalidUser = anUser
        invalidUser.state = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Deve criticar usuário com password nula"() {
        def invalidUser = anUser
        invalidUser.password = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    @Ignore
    // definir e implementar regras de seguranca
    public void "Deve criticar usuário com password inválida"() {
        def invalidUser = anUser
        invalidUser.password = "123"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidUser)
    }

    @Test
    public void "Não deve permitir novo usuário com e-mail já existente"() {
        when(userValidatorMock.validate(anUser, null)).thenThrow(new RepeatedUserEmailException());
        postAndExpectBadRequest(BASE_ENDPOINT, anUser)
    }

    @Test
    public void "Não deve permitir novo usuário com cpf já existente"() {
        when(userValidatorMock.validate(anUser, null)).thenThrow(new RepeatedUserCPFException());
        postAndExpectBadRequest(BASE_ENDPOINT, anUser)
    }

    @Test
    public void "Deve listar todos os Usuários existentes"() {

        def userListMock = [anUser, anotherUser]

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