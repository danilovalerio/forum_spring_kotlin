package br.com.danilo.exemplo.forum.controller

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * Os testes vai simular como se um usuário estivesse fazendo a requisição
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object {
        private const val RECURSO = "/topicos"
    }

    @BeforeEach
    fun setup() {
        // instanciacao do security mock mvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder?>(
            SecurityMockMvcConfigurers.springSecurity()
        ).build()
    }

    fun `deve retornar codigo 400 quando chamar topicos sem token`(){
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }
}