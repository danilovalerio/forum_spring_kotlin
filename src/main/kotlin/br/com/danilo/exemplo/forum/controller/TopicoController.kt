package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * RestController - recebe as requisi��es, faz a manipula��o e devolve dados
 * RequestMapping - toda requisi��o /topicos vem para essa classe
 * GetMapping - requis�o do tipo get cai na fun��o listar
 */
@RestController
@RequestMapping("/topicos")
class TopicoController {

    @GetMapping
    fun listar(): List<Topico> {
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

        return Arrays.asList(topico, topico, topico)
    }

}