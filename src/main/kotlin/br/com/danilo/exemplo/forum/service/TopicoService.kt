package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

/**
 * @Service - faz com que o Spring gerencie essa classe como um serviço
 * com isso permitindo a injeção de dependência
 */
@Service
class TopicoService {
    fun listar(): List<Topico> {
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

        return Arrays.asList(topico, topico, topico)
    }
}