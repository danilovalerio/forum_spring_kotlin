package br.com.danilo.exemplo.forum.controller

import br.com.danilo.exemplo.forum.dto.NovaRespostaForm
import br.com.danilo.exemplo.forum.model.Resposta
import br.com.danilo.exemplo.forum.service.RespostaService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
class RespostaController(private val service: RespostaService) {

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid resposta: NovaRespostaForm,
    ) {
        val respostaRecente = service.cadastrar(resposta)
    }
}