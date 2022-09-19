package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.dto.NovoTopicoDto
import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Service - faz com que o Spring gerencie essa classe como um serviço
 * com isso permitindo a injeção de dependência
 */
@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService
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
    fun listar(): List<Topico> {
        return topicos
    }

    fun buscaPorId(id: Long): Topico {
        /**
         * Com api stream do java 8
         * filtra dado um topico ->
         * topico id igual ao id passado
         * pega o primeiro registro encontrado
         */
        return topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
    }

    fun cadastrar(dto: NovoTopicoDto) {
        topicos.plus(Topico(
            id = topicos.size.toLong() + 1,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscaPorId(dto.idCurso),
            autor = usuarioService.buscaPorId(dto.idAutor)
        ))
    }
}