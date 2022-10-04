package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto
import br.com.danilo.exemplo.forum.service.TopicoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Relatório mais abstrato para N fins
 * Troca de RestController por Contoller para permitir retornos diferentes de JSON
 */
@Controller
@RequestMapping("relatorios")
class RelatorioController(
    private val topicoService: TopicoService
) {
    @GetMapping
    fun relatorio(model: Model): String {
        // adicionar atributo no modelo UI
        model.addAttribute("topicosPorCategorias", topicoService.relatorio())
        // relatorio - nome do template thymeleaf
        return "relatorio"
    }
}