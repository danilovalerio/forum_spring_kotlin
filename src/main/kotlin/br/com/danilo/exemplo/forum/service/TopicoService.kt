package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.exception.NotFoundException
import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.stream.Collectors
import javax.persistence.EntityManager
import kotlin.collections.ArrayList

/**
 * @Service - faz com que o Spring gerencie essa classe como um serviço
 * com isso permitindo a injeção de dependência
 *
 * @Transactional - é necessário para os método de escrita, que vai fazer a transação SQL
 */
@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "tópico não encontrado",
    private val em: EntityManager
) {
    /**
     * Lista inicializada para simular ao banco em memória
     */
    /**init {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlinzinho",
            mensagem = "Variáveis no Kotlin",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Danilo",
                email = "danilo_vs@hotmail.com"
            )
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Duvida Kotlinzinho 2",
            mensagem = "Variáveis no Kotlin 2",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Danilo",
                email = "danilo_vs@hotmail.com"
            )
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Duvida Kotlinzinho 3",
            mensagem = "Variáveis no Kotlin 3",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Danilo",
                email = "danilo_vs@hotmail.com"
            )
        )

        topicos = Arrays.asList(topico, topico2, topico3)
    }*/
    @Cacheable("topicos")
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        /**
         * vamos mapear a lista para devolver uma lista de topicoview
         */
        print(em)
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map {
                topico -> topicoViewMapper.map(topico)
        }
    }

    fun buscaPorId(id: Long): TopicoView {
        /**
         * Com api stream do java 8
         * filtra dado um topico ->
         * topico id igual ao id passado
         * pega o primeiro registro encontrado
         */
        val topicoEncontrado = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topicoEncontrado)
    }

    @Transactional
    //limpa o cache topicos
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    @Transactional
    //limpa o cache topicos
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topicoEncontrado = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }

        topicoEncontrado.titulo = form.titulo
        topicoEncontrado.mensagem = form.mensagem
        topicoEncontrado.dataAlteracao = LocalDate.now()
        return topicoViewMapper.map(topicoEncontrado)
    }

    @Transactional
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}