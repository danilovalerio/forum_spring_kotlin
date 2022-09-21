package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

/**
 * RestController - recebe as requisições, faz a manipulação e devolve dados
 * RequestMapping - toda requisição /topicos vem para essa classe
 * GetMapping - requisão do tipo get cai na função listar
 *
 * Pageable recurso para controlar a paginação oferecido pelo Spring Boot
 */
@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC)
        paginacao: Pageable
    ): Page<TopicoView> {
        /**
         * service me devolve o listar
         */
        return service.listar(nomeCurso, paginacao)
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
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView>{
        val topicoViewAtualizado = service.atualizar(form)
        /**
         * vamos devolver a view do recurso que foi atualizado
         */
        return ResponseEntity.ok(topicoViewAtualizado)
    }

    /**
     * Ao deletar devolve 204 sem corpo, pois o recurso foi excluído
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }

}