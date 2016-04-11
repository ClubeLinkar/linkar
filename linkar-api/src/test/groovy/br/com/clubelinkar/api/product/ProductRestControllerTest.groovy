package br.com.clubelinkar.api.product

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

import static br.com.clubelinkar.api.product.ProductMother.*
import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class ProductRestControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/product"

    @Mock
    def ProductRepository productRepositoryMock

    @InjectMocks
    def ProductRestController productRestController

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(productRestController).build();
    }

    @Test
    public void "Deve cadastrar um produto corretamente"() {

        when(productRepositoryMock.save(riserGuidao())).thenReturn(riserGuidao());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(riserGuidao()))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals riserGuidao().name, product.name
        assertEquals riserGuidao().description, product.description
        assertEquals riserGuidao().brand, product.brand
        assertEquals riserGuidao().price, product.price
        assertEquals riserGuidao().companyId, product.companyId

    }

    @Test
    public void "Deve cadastrar um produto corretamente com categorias"() {

        when(productRepositoryMock.save(riserGuidaoWithCategories())).thenReturn(riserGuidaoWithCategories());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(riserGuidaoWithCategories()))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals riserGuidaoWithCategories().name, product.name
        assertEquals riserGuidaoWithCategories().description, product.description
        assertEquals riserGuidaoWithCategories().brand, product.brand
        assertEquals riserGuidaoWithCategories().price, product.price
        assertEquals riserGuidaoWithCategories().companyId, product.companyId
        assertEquals riserGuidaoWithCategories().categories, product.categories
    }

    @Test
    public void "Deve cadastrar um produto corretamente com categorias e eliminar categorias repetidas"() {

        when(productRepositoryMock.save(riserGuidaoWithRepeatedCategories())).thenReturn(riserGuidaoWithRepeatedCategories());

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(riserGuidaoWithRepeatedCategories()))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals riserGuidaoWithRepeatedCategories().name, product.name
        assertEquals riserGuidaoWithRepeatedCategories().description, product.description
        assertEquals riserGuidaoWithRepeatedCategories().brand, product.brand
        assertEquals riserGuidaoWithRepeatedCategories().price, product.price
        assertEquals riserGuidaoWithRepeatedCategories().companyId, product.companyId
        assertEquals riserGuidaoWithRepeatedCategories().categories, product.categories
        assertEquals 2, product.categories.size()

        assertEquals riserGuidaoWithCategories().categories, product.categories
        // a comparacao com riserGuidao()WithCategories, diferente das anteriores eh de proposito, pois queremos garantir
        // que o produto retornado so tem 2 categorias, e nao 3, como as associadas ao riserGuidaoWithRepeatedCategories()

    }

    @Test
    public void "Deve criticar loja com name nulo"() {
        def invalidProduct = riserGuidao()
        invalidProduct.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com description nulo"() {
        def invalidProduct = riserGuidao()
        invalidProduct.description = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    @Ignore
    public void "Deve criticar loja com brand nulo"() {
        def invalidProduct = riserGuidao()
        invalidProduct.brand = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price nulo"() {
        def invalidProduct = riserGuidao()
        invalidProduct.price = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price zero"() {
        def invalidProduct = riserGuidao()
        invalidProduct.price = 0
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com companyId nulo"() {
        def invalidProduct = riserGuidao()
        invalidProduct.companyId = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    @Ignore
    // TODO Implementar
    public void "Deve criticar loja com companyId inválido"() {
        def invalidProduct = riserGuidao()
        invalidProduct.companyId = "blah"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Não deve permitir produto repetido"() {
        // TODO Implementar
    }

    @Test
    public void "Deve listar corretamente todos os produtos cadastradas no sistema"() {

        def productListMock = [riserGuidao(), espadrilla()]

        when(productRepositoryMock.findAll()).thenReturn(productListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Product>>() {}.getType();

        List<Product> productsList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse productsList.empty
        assertEquals productListMock, productsList
    }

    @Test
    public void "Deve listar todos os produtos de uma determinada loja"() {

        def productListMock = [riserGuidao(), espadrilla()]

        when(productRepositoryMock.findByCompanyId("id_123456_company")).thenReturn(productListMock)

        def result = mockMvc.perform(get("/product/company/id_123456_company")).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Product>>() {}.getType();

        List<Product> productsList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse productsList.empty
        assertEquals productListMock, productsList
    }

}
