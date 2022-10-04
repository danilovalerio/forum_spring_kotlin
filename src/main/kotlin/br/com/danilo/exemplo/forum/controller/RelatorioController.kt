package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Relatório mais abstrato para N fins
 */
@RestController
@RequestMapping("relatorios")
class RelatorioController(
    private val topicoService: TopicoService
) {
    @GetMapping
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoService.relatorio()
    }
}