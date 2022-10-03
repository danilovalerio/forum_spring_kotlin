package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.exception.NotFoundException
import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.TopicoTest
import br.com.danilo.exemplo.forum.model.TopicoViewTest
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*
import javax.persistence.EntityManager

class TopicoServiceTest {
    /**
     * Vamos testar o compaortamento do método listar
     * se passar nome do curso, chama o findBCursoNome
     * se não chama o finAll
     */
    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        //Sempre que topicoRepository for chamado com qualqquer parametro
        every { findByCursoNome(any(), any()) } returns topicos
        //Sempre que chamar o findAll com paginacao retorna os topicos
        every { findAll(paginacao) } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk {
        // sempre que topicoViewMapper.map for chamado com qualquer parametro retorna nosso objeto mok=ckado
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()
    val em: EntityManager = mockk()

    val topicoService = TopicoService(
        repository = topicoRepository,
        topicoViewMapper = topicoViewMapper,
        topicoFormMapper = topicoFormMapper,
        em = em
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`(){
        topicoService.listar("Kotlin Básico", paginacao = paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }

    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`(){
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve retornar exception not found ao busar por id de topico inexistente`(){
        //Quando chamar o findById retorna um Optional vazio
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscaPorId(1)
        }

        assertThat(atual.message).isEqualTo("tópico não encontrado")

    }
}