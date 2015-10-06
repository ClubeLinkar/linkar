package br.com.clubelinkar.controller.rest

import br.com.clubelinkar.domain.Product
import br.com.clubelinkar.domain.Store
import com.google.gson.Gson
import org.junit.Test
import org.springframework.http.MediaType;

import static org.junit.Assert.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class ProductRestControllerTest extends BaseRestControllerTest {

    @Test
    public void "Deve cadastrar um produto corretamente"() {

        def airFilter = new Product()

        def result = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(airFilter))
        ).andExpect(status().isOk()).andReturn()

        def product = new Gson().fromJson(result.response.contentAsString, Product)

        assertNotNull product
        assertNotNull product.id
        assertEquals airFilter.name, product.name
        assertEquals airFilter.description, product.description
        assertTrue airFilter.price.equals(product.price)

    }

    @Test
    public void "Deve listar corretamente todas os produtos de todas as lojas cadastradas no sistema"() {

        def result = mockMvc.perform(get("/product")).andExpect(status().isOk()).andReturn()

        def products = new Gson().fromJson(result.response.contentAsString, List)

        assertFalse products.empty

    }

}