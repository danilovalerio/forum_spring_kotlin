package br.com.danilo.exemplo.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * O Spring observa essa classe ao iniciar
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    /**
     * Configurar quais recursos serão bloqueados ou não
     * Qualquer requisisão precisa estar autenticada
     *
     */
    override fun configure(http: HttpSecurity?) {
        /**
         * SessionCreationPolicy.STATELESS - Não quero que minha aplicação não guarde nenhum estado
         * de autenticação
         *
         * Forma de Login disabilitado e alterada para httpBasic
         */
        http?.
        authorizeRequests()?.
        anyRequest()?.
        authenticated()?.
        and()?.
        sessionManagement()?.
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.
        and()?.
        formLogin()?.disable()?.
        httpBasic()
    }

    /**
     * @Bean para o Spring gerenciar
     */
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    /**
     * AuthenticationManagerBuilder - responsavel por pegar infos de autenticação do usuario
     * e passa para um metodo responsável por conferir na base de dados
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        /**
         * passwordEncoder para gerar o Hash da senha
         */
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }
}