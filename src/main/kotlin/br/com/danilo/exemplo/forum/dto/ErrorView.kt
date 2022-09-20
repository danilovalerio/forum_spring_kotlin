package br.com.danilo.exemplo.forum.dto

import java.time.LocalDateTime

/**
 * DTO Genérico para qualquer tipo de erro
 */
data class ErrorView (
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
