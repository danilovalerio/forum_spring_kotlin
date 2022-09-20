package br.com.danilo.exemplo.forum.exception

import br.com.danilo.exemplo.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Classe para fazer o Tratamento das Exceptions Lançadas
 *
 * @RestControllerAdvice - Para parametrizar para o Spring que essa classe faz um tratamento para os RestsControllers
 * da nossa aplicação (Advice)
 */
@RestControllerAdvice
class ExceptionHandler {
    /**
     * @exceptionHandler e especifica quando for NotFoundException chama essa função
     * e devolve o codigo NOT_FOUND
     */
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(): ErrorView {

    }
}