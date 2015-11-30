package br.com.clubelinkar.api.store

import br.com.clubelinkar.test.BaseRestControllerTest
import com.google.gson.Gson
import org.junit.Test
import org.springframework.http.MediaType

import static org.junit.Assert.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Lennon Jesus
 */
public class StoreRestControllerTest extends BaseRestControllerTest {

    @Test
    public void "Deve cadastrar uma loja corretamente"() {

        def allMotos = new Store(name: 'All Motos',
                address: 'Rua Siqueira Campos, 243, loja B, Copacaban, Rio de Janeiro - RJ',
                phones: '(21) 3442-3584 - WhatsApp (21) 98081-0033',
                url: 'allmotos.com.br',
                email: 'allmotos@allmotos.com.br')

        def result = mockMvc.perform(post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(allMotos))
        ).andExpect(status().isOk()).andReturn()

        def store = new Gson().fromJson(result.response.contentAsString, Store)

        assertNotNull store
        assertNotNull store.id
        assertEquals allMotos.name, store.name
        assertEquals allMotos.address, store.address
        assertEquals allMotos.phones, store.phones
        assertEquals allMotos.url, store.url
        assertEquals allMotos.email, store.email

    }

    @Test
    public void "Deve listar corretamente todas as lojas cadastradas no sistema"() {

        def result = mockMvc.perform(get("/store")).andExpect(status().isOk()).andReturn()

        def stores = new Gson().fromJson(result.response.contentAsString, List)

        assertFalse stores.empty

    }


}
