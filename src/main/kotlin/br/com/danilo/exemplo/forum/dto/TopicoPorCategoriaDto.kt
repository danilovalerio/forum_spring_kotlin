package br.com.danilo.exemplo.forum.dto

/**
 * Representa a consulta dizendo quantos tem por categoria
 */
data class TopicoPorCategoriaDto (
    val categoria: String,
    val quantidade: Long
)
