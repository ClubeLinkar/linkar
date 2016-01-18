package br.com.clubelinkar.api.dummy

import br.com.clubelinkar.test.BaseRestControllerMock
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static br.com.clubelinkar.test.ProductObjectMother.getaProduct
import static org.junit.Assert.assertNotNull
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by lennonjesus on 1/17/16.
 */
class DummyDataControllerTest extends BaseRestControllerMock {

    private static final String BASE_ENDPOINT = "/exception"

    @InjectMocks
    def DummyDataController dummyDataControllerMock

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this)

        this.mockMvc = MockMvcBuilders.standaloneSetup(dummyDataControllerMock).build();
    }

    @Test
    public void "deve lancar excecao corretamente"() {


        def result = mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(aProduct))
        ).andExpect(status().isBadRequest()).andReturn()

        assertNotNull(result)

    }

}
