package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * RestController - recebe as requisições, faz a manipulação e devolve dados
 * RequestMapping - toda requisição /topicos vem para essa classe
 * GetMapping - requisão do tipo get cai na função listar
 */
@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<Topico> {
        /**
         * service me devolve o listar
         */
        return service.listar()
    }

}