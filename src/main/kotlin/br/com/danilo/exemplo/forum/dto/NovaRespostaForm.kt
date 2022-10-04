package br.com.danilo.exemplo.forum.dto

import javax.validation.constraints.NotEmpty

data class NovaRespostaForm(
    @field:NotEmpty(message = "Mensagem não pode ser em branco")
    val mensagem: String
)
