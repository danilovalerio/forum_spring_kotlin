package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.NovoTopicoDto
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.web.bind.annotation.*

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

    /**
     * adicionado o complemento /{id} da uri
     * @PathVariable indica que id é uma variavel do caminho
     */
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico {
        return service.buscaPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody topico: NovoTopicoDto) {
        service.cadastrar(topico)
    }

}