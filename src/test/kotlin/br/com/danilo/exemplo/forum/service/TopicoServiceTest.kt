package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.TopicoTest
import br.com.danilo.exemplo.forum.model.TopicoViewTest
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
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
    }
    val topicoViewMapper: TopicoViewMapper = mockk {
        // sempre que topicoViewMapper.map for chamado com qualquer parametro retorna nosso objeto mok=ckado
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()
    val em: EntityManager = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper, "", em
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`(){
        topicoService.listar("Kotlin Básico", paginacao = paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }

    }
}