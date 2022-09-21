package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.AtualizacaoTopicoForm
import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto
import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

/**
 * RestController - recebe as requisi��es, faz a manipula��o e devolve dados
 * RequestMapping - toda requisi��o /topicos vem para essa classe
 * GetMapping - requis�o do tipo get cai na fun��o listar
 *
 * Pageable recurso para controlar a pagina��o oferecido pelo Spring Boot
 */
@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
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
    @Transactional
    //limpa o cache topicos
    @CacheEvict(value = ["topicos"], allEntries = true)
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
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView>{
        val topicoViewAtualizado = service.atualizar(form)
        /**
         * vamos devolver a view do recurso que foi atualizado
         */
        return ResponseEntity.ok(topicoViewAtualizado)
    }

    /**
     * Ao deletar devolve 204 sem corpo, pois o recurso foi exclu�do
     */
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return service.relatorio()
    }

}