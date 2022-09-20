package br.com.danilo.exemplo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm (
    @field:NotEmpty(message = "T�tulo n�o pode ser em branco")
    @field:Size(min = 5, max = 30, message = "T�tulo deve ter entre 5 e 30 caracteres")
    val titulo: String,
    @field:NotEmpty(message = "Mensagem n�o pode ser em branco")
    val mensagem: String,
    @field:NotNull(message = "Id do curso n�o pode ser nulo")
    val idCurso: Long,
    @field:NotNull(message = "Id do autor n�o pode ser nulo")
    val idAutor: Long
)
