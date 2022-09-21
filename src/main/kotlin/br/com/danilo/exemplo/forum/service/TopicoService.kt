package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.exception.NotFoundException
import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

/**
 * @Service - faz com que o Spring gerencie essa classe como um serviço
 * com isso permitindo a injeção de dependência
 */
@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "tópico não encontrado"
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
    fun listar(): List<TopicoView> {
        /**
         * vamos mapear a lista para devolver uma lista de topicoview
         */
        return repository.findAll().stream().map {
                topico -> topicoViewMapper.map(topico)
        }.collect(Collectors.toList())
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

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topicoEncontrado = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }

        topicoEncontrado.titulo = form.titulo
        topicoEncontrado.mensagem = form.mensagem
        return topicoViewMapper.map(topicoEncontrado)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}