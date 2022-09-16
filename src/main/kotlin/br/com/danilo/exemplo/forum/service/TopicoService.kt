package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

/**
 * @Service - faz com que o Spring gerencie essa classe como um servi�o
 * com isso permitindo a inje��o de depend�ncia
 */
@Service
class TopicoService(
    private var topicos: List<Topico>
) {
    /**
     * Lista inicializada para simular ao banco em mem�ria
     */
    init {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlinzinho",
            mensagem = "Vari�veis no Kotlin",
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
            mensagem = "Vari�veis no Kotlin 2",
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
            mensagem = "Vari�veis no Kotlin 3",
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
    }
    fun listar(): List<Topico> {
        return topicos
    }
}