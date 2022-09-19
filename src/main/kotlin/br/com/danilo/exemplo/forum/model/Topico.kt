package br.com.danilo.exemplo.forum.model

import java.time.LocalDateTime

/**
 * Representa o recruso de t�pico dentro do projeto
 */
data class Topico(
    var id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val respostas: List<Resposta> = ArrayList()
)
