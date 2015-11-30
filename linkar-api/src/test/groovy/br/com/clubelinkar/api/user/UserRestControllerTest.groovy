package br.com.clubelinkar.api.user

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

import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
class UserRestControllerTest extends BaseRestControllerTest {

    @Mock
    def UserRepository userRepositoryMock

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

        def result = mockMvc.perform(post("/user")
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
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com email nulo"() {
        def invalidUser = anUser
        invalidUser.email = null
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com email inválido"() {
        def invalidUser = anUser
        invalidUser.email = "email.inválido"
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com cpf nulo"() {
        def invalidUser = anUser
        invalidUser.cpf = null
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    @Ignore // implementar validacao de cpf
    public void "Deve criticar usuário com cpf inválido"() {
        def invalidUser = anUser
        invalidUser.cpf = "abc123"
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com cidade nula"() {
        def invalidUser = anUser
        invalidUser.city = null
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com uf nulo"() {
        def invalidUser = anUser
        invalidUser.state = null
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Deve criticar usuário com password nula"() {
        def invalidUser = anUser
        invalidUser.password = null
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    @Ignore // definir e implementar regras de seguranca
    public void "Deve criticar usuário com password inválida"() {
        def invalidUser = anUser
        invalidUser.password = "123"
        postAndExpectBadRequest(invalidUser)
    }

    @Test
    public void "Não deve permitir usuário com mesmo e-mail"() {
        // TODO Implementar
    }

    @Test
    public void "Não deve permitir usuário com mesmo cpf"() {
        // TODO Implementar
    }

    @Test
    public void "Deve listar todos os Usuários existentes"() {

        def userListMock = [anUser, anotherUser]

        when(userRepositoryMock.findAll()).thenReturn(userListMock)

        def result = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<User>>() {}.getType();

        List<User> userList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse userList.empty
        assertEquals 2, userList.size()
        assertEquals userListMock, userList
    }


    def postAndExpectBadRequest(User user) {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(user))
        ).andExpect(status().isBadRequest()).andReturn()
    }

    def User getAnUser() {
        new User(
                name: "Lennon Jesus",
                email: "lennon.jesus@gmail.com",
                cpf: "09974361745",
                district: "Jardim Primavera",
                city: "Duque de Caxias",
                state: "RJ",
                password: "123456"
        )
    }

    def User getAnotherUser() {
        new User(
                name: "Lorem Ipsum Dolor User",
                email: "anotheruser@domain.com",
                cpf: "22233344410",
                district: "São José do Barreiro",
                city: "Bananal",
                state: "SP",
                password: "um2três"
        )
    }

}