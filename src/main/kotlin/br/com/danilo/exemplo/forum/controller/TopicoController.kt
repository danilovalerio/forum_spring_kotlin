package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

/**
 * RestController - recebe as requisições, faz a manipulação e devolve dados
 * RequestMapping - toda requisição /topicos vem para essa classe
 * GetMapping - requisão do tipo get cai na função listar
 */
@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> {
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
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscaPorId(id)
    }

    /**
     * Ao criar um recurso como boa prática REST
     * Devemos devolver 201
     * Body com a representação do Recurso criado
     * Header Location o endereço (uri) do recurso
     *
     * uriBuilder do Spring sabe criar URI
     */
    @PostMapping
    fun cadastrar(
        @RequestBody @Valid topicoDto: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoViewRecente = service.cadastrar(topicoDto)

        /**
         * pega a url do servidor e converte para URI esperada
         */
        val uri = uriBuilder.path("/topicos/${topicoViewRecente.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoViewRecente)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm){
        service.atualizar(form)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }

}