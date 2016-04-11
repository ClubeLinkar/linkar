package br.com.clubelinkar.api.category

import br.com.clubelinkar.test.BaseRestControllerMock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import java.lang.reflect.Type

import static br.com.clubelinkar.api.category.CategoryMother.*
import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class CategoryRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/category"

    @Mock
    def CategoryRepository categoryRepositoryMock

    @InjectMocks
    def CategoryRestController categoryRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test
    public void "Deve cadastrar uma categoria corretamente"() {

        when(categoryRepositoryMock.save(servicosMecanica())).thenReturn(servicosMecanica());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(servicosMecanica()))
        ).andExpect(status().isOk()).andReturn()

        def category = new Gson().fromJson(result.response.contentAsString, Category)

        assertNotNull category
        assertEquals servicosMecanica().name, category.name

    }

    @Test
    public void "Deve criticar categoria com name nulo"() {
        def invalidCategory = servicosMecanica()
        invalidCategory.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidCategory)
    }

    @Test
    public void "Não deve permitir categoria repetida"() {
        // TODO Implementar
    }

    @Test
    public void "Deve listar corretamente todas as categorias cadastradas no sistema"() {

        def categoryListMock = [servicosMecanica(), pecasMoto()]

        when(categoryRepositoryMock.findAll()).thenReturn(categoryListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Category>>() {}.getType();

        List<Category> categorysList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse categorysList.empty
        assertEquals categoryListMock, categorysList

    }


}
