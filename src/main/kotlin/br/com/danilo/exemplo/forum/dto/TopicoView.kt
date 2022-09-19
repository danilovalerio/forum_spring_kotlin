package br.com.danilo.exemplo.forum.dto

import br.com.danilo.exemplo.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
    val id: Long,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
