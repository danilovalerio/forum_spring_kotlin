package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Usuario
import br.com.danilo.exemplo.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
data class UsuarioService(
    private val repository: UsuarioRepository
): UserDetailsService {

    fun buscaPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }

}
