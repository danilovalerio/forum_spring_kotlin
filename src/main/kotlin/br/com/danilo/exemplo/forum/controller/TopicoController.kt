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
 * RestController - recebe as requisi��es, faz a manipula��o e devolve dados
 * RequestMapping - toda requisi��o /topicos vem para essa classe
 * GetMapping - requis�o do tipo get cai na fun��o listar
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
     * @PathVariable indica que id � uma variavel do caminho
     */
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscaPorId(id)
    }

    /**
     * Ao criar um recurso como boa pr�tica REST
     * Devemos devolver 201
     * Body com a representa��o do Recurso criado
     * Header Location o endere�o (uri) do recurso
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