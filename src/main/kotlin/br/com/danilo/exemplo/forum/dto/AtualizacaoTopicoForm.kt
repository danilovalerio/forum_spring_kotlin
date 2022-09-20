package br.com.danilo.exemplo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Para atualizar topico será permitido somente editar
 * Titulo e Mensagem o restante sera igual
 */
data class AtualizacaoTopicoForm(
    @field: NotNull
    val id: Long,
    @field: NotEmpty
    @field: Size(min = 5, max =30)
    val titulo: String,
    @field:NotEmpty
    val mensagem: String
)
