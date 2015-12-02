package br.com.clubelinkar.api.product

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

import static br.com.clubelinkar.test.ProductObjectMother.aProduct
import static br.com.clubelinkar.test.ProductObjectMother.anotherProduct
import static org.junit.Assert.*
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class ProductRestControllerTest extends BaseRestControllerTest {

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

        when(productRepositoryMock.save(aProduct)).thenReturn(aProduct);

        def result = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aProduct))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals aProduct.name, product.name
        assertEquals aProduct.description, product.description
        assertEquals aProduct.brand, product.brand
        assertEquals aProduct.price, product.price
        assertEquals aProduct.storeId, product.storeId

    }

    @Test
    public void "Deve criticar loja com name nulo"() {
        def invalidProduct = aProduct
        invalidProduct.name = null
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Deve criticar loja com description nulo"() {
        def invalidProduct = aProduct
        invalidProduct.description = null
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Deve criticar loja com brand nulo"() {
        def invalidProduct = aProduct
        invalidProduct.brand = null
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price nulo"() {
        def invalidProduct = aProduct
        invalidProduct.price = null
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price zero"() {
        def invalidProduct = aProduct
        invalidProduct.price = 0
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Deve criticar loja com storeId nulo"() {
        def invalidProduct = aProduct
        invalidProduct.storeId = null
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    @Ignore
    // TODO Implementar
    public void "Deve criticar loja com storeId inválido"() {
        def invalidProduct = aProduct
        invalidProduct.storeId = "blah"
        postAndExpectBadRequest("/product", invalidProduct)
    }

    @Test
    public void "Não deve permitir produto repetido"() {
        // TODO Implementar
    }

    @Test
    public void "Deve listar corretamente todos os produtos cadastradas no sistema"() {

        def productListMock = [aProduct, anotherProduct]

        when(productRepositoryMock.findAll()).thenReturn(productListMock)

        def result = mockMvc.perform(get("/product")).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Product>>() {}.getType();

        List<Product> productsList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse productsList.empty
        assertEquals productListMock, productsList

    }

}
