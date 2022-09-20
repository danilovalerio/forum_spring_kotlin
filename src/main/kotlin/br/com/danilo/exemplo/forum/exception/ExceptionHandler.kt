package br.com.danilo.exemplo.forum.exception

import br.com.danilo.exemplo.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

/**
 * Classe para fazer o Tratamento das Exceptions Lan�adas
 *
 * @RestControllerAdvice - Para parametrizar para o Spring que essa classe faz um tratamento para os RestsControllers
 * da nossa aplica��o (Advice), o Spring vai olhar aqui quando der alguma exception e ver se tem um tratamento personalizado
 */
@RestControllerAdvice
class ExceptionHandler {
    /**
     * @exceptionHandler e especifica quando for NotFoundException chama essa fun��o
     * e devolve o codigo NOT_FOUND
     *
     * HttpServeletRequest - Permite pegar o caminho da requisi��o
     */
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleNotFound(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}