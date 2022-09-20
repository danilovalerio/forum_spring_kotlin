package br.com.danilo.exemplo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm (
    @field:NotEmpty(message = "Título não pode ser em branco")
    @field:Size(min = 5, max = 30, message = "Título deve ter entre 5 e 30 caracteres")
    val titulo: String,
    @field:NotEmpty(message = "Mensagem não pode ser em branco")
    val mensagem: String,
    @field:NotNull(message = "Id do curso não pode ser nulo")
    val idCurso: Long,
    @field:NotNull(message = "Id do autor não pode ser nulo")
    val idAutor: Long
)
