package br.com.danilo.exemplo.forum.exception

/**
 * Trata quando o recurso n�o existe
 */
class NotFoundException(message: String?): RuntimeException(message) {

}