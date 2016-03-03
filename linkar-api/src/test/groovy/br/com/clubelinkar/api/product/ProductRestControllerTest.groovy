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

import static br.com.clubelinkar.test.ProductObjectMother.*
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

        when(productRepositoryMock.save(aProduct)).thenReturn(aProduct);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
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
    public void "Deve cadastrar um produto corretamente com categorias"() {

        when(productRepositoryMock.save(aProductWithCategories)).thenReturn(aProductWithCategories);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aProductWithCategories))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals aProductWithCategories.name, product.name
        assertEquals aProductWithCategories.description, product.description
        assertEquals aProductWithCategories.brand, product.brand
        assertEquals aProductWithCategories.price, product.price
        assertEquals aProductWithCategories.storeId, product.storeId
        assertEquals aProductWithCategories.categories, product.categories
    }

    @Test
    public void "Deve cadastrar um produto corretamente com categorias e eliminar categorias repetidas"() {

        when(productRepositoryMock.save(aProductWithRepeatedCategories)).thenReturn(aProductWithRepeatedCategories);

        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aProductWithRepeatedCategories))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertEquals aProductWithRepeatedCategories.name, product.name
        assertEquals aProductWithRepeatedCategories.description, product.description
        assertEquals aProductWithRepeatedCategories.brand, product.brand
        assertEquals aProductWithRepeatedCategories.price, product.price
        assertEquals aProductWithRepeatedCategories.storeId, product.storeId
        assertEquals aProductWithRepeatedCategories.categories, product.categories
        assertEquals 2, product.categories.size()

        assertEquals aProductWithCategories.categories, product.categories
        // a comparacao com aProductWithCategories, diferente das anteriores eh de proposito, pois queremos garantir
        // que o produto retornado so tem 2 categorias, e nao 3, como as associadas ao aProductWithRepeatedCategories

    }

    @Test
    public void "Deve criticar loja com name nulo"() {
        def invalidProduct = aProduct
        invalidProduct.name = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com description nulo"() {
        def invalidProduct = aProduct
        invalidProduct.description = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    @Ignore
    public void "Deve criticar loja com brand nulo"() {
        def invalidProduct = aProduct
        invalidProduct.brand = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price nulo"() {
        def invalidProduct = aProduct
        invalidProduct.price = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com price zero"() {
        def invalidProduct = aProduct
        invalidProduct.price = 0
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Deve criticar loja com storeId nulo"() {
        def invalidProduct = aProduct
        invalidProduct.storeId = null
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    @Ignore
    // TODO Implementar
    public void "Deve criticar loja com storeId inválido"() {
        def invalidProduct = aProduct
        invalidProduct.storeId = "blah"
        postAndExpectBadRequest(BASE_ENDPOINT, invalidProduct)
    }

    @Test
    public void "Não deve permitir produto repetido"() {
        // TODO Implementar
    }

    @Test
    public void "Deve listar corretamente todos os produtos cadastradas no sistema"() {

        def productListMock = [aProduct, anotherProduct]

        when(productRepositoryMock.findAll()).thenReturn(productListMock)

        def result = mockMvc.perform(get(BASE_ENDPOINT)).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Product>>() {}.getType();

        List<Product> productsList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse productsList.empty
        assertEquals productListMock, productsList

    }


    @Test
    public void "Deve listar todos os produtos de uma determinada loja"() {

        def productListMock = [aProduct, anotherProduct]

        when(productRepositoryMock.findByStoreId("id_123456_store")).thenReturn(productListMock)

        def result = mockMvc.perform(get("/product/store/id_123456_store")).andExpect(status().isOk()).andReturn()

        Type dummyListType = new TypeToken<ArrayList<Product>>() {}.getType();

        List<Product> productsList = new Gson().fromJson(result.response.contentAsString, dummyListType)

        assertFalse productsList.empty
        assertEquals productListMock, productsList

    }

}
