package br.com.danilo.exemplo.forum.exception

import br.com.danilo.exemplo.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

/**
 * Classe para fazer o Tratamento das Exceptions Lançadas
 *
 * @RestControllerAdvice - Para parametrizar para o Spring que essa classe faz um tratamento para os RestsControllers
 * da nossa aplicação (Advice), o Spring vai olhar aqui quando der alguma exception e ver se tem um tratamento personalizado
 */
@RestControllerAdvice
class ExceptionHandler {
    /**
     * @exceptionHandler e especifica quando for NotFoundException chama essa função
     * e devolve o codigo NOT_FOUND
     *
     * HttpServeletRequest - Permite pegar o caminho da requisição
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

    /**
     * BAD_REQUEST - Pois foi uma requisição invalida enviada pelo cliente com campo obrigatório em branco por exemplo
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {
        /**
         * HashMap de [chave - valor] para mapear [campo - erro]
         * Simplificando a mensagem a retornada das validações de campos
         */
        val errorMessageCustom = HashMap<String, String?>()

        /**
         * Percorre a lista de erros dos campos validados
         */
        exception.bindingResult.fieldErrors.forEach{
            //para cada erro encontrado adiciona no hashMap
            errorValidation -> errorMessageCustom.put(errorValidation.field, errorValidation.defaultMessage)
        }

        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessageCustom.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
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