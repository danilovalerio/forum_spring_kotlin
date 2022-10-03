package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.TopicoTest
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.PageImpl
import javax.persistence.EntityManager

class TopicoServiceTest {
    /**
     * Vamos testar o compaortamento do método listar
     * se passar nome do curso, chama o findBCursoNome
     * se não chama o finAll
     */
    val topicos = PageImpl(listOf(TopicoTest.build()))

    val topicoRepository: TopicoRepository = mockk {
        //Sempre que topicoRepository for chamado com qualqquer parametro
        every { findByCursoNome(any(), any()) } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk()
    val topicoFormMapper: TopicoFormMapper = mockk()
    val em: EntityManager = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper, "", em
    )
}