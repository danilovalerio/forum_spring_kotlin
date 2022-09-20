package br.com.danilo.exemplo.forum.exception

/**
 * Trata quando o recurso não existe
 */
class NotFoundException(message: String?): RuntimeException(message) {

}