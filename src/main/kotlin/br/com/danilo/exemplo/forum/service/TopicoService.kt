package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.mapper.TopicoFormMapper
import br.com.danilo.exemplo.forum.mapper.TopicoViewMapper
import br.com.danilo.exemplo.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

/**
 * @Service - faz com que o Spring gerencie essa classe como um serviço
 * com isso permitindo a injeção de dependência
 */
@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
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
        return topicos.stream().map {
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
        val topicoEncontrado = topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
        return topicoViewMapper.map(topicoEncontrado)

    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        val topicoEncontrado = topicos.stream().filter { topico ->
            topico.id == form.id
        }.findFirst().get()

        /**
         * Remove o topico anterior e adiciona novo topico
         */
        topicos = topicos.minus(topicoEncontrado).plus(Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topicoEncontrado.autor,
            curso = topicoEncontrado.curso,
            respostas = topicoEncontrado.respostas,
            status = topicoEncontrado.status,
            dataCriacao = topicoEncontrado.dataCriacao
        ))
    }

    fun deletar(id: Long) {
        val topicoEncontrado = topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()

        topicos = topicos.minus(topicoEncontrado)
    }
}