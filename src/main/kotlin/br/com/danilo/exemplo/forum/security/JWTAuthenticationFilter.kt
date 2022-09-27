package br.com.danilo.exemplo.forum.security

import br.com.danilo.exemplo.forum.config.JWUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Filtro para uso do Token gerado com o Login Filter
 *
 * OncePerRequestFilter - porque será validado a cada requisição
 */
class JWTAuthenticationFilter(private val jwtUtil: JWUtil) : OncePerRequestFilter {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwtUtil.isValid(jwt)) {
           val authentication = jwtUtil.getAuthentication(jwt)
            // adiciona a autenticao no contexto da aplicacao
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token.let { jwt ->
            jwt?.startsWith("Bearer ")
            jwt?.substring(7, jwt.length)
        }
    }

}
