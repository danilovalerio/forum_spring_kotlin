package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.NovoTopicoDto
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.web.bind.annotation.*

/**
 * RestController - recebe as requisi��es, faz a manipula��o e devolve dados
 * RequestMapping - toda requisi��o /topicos vem para essa classe
 * GetMapping - requis�o do tipo get cai na fun��o listar
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
     * @PathVariable indica que id � uma variavel do caminho
     */
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico {
        return service.buscaPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody topicoDto: NovoTopicoDto) {
        service.cadastrar(topicoDto)
    }

}