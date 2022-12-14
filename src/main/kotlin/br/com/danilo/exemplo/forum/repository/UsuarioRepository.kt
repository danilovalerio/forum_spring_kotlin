package br.com.danilo.exemplo.forum.repository

import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

    fun findByEmail(username: String?): Usuario?
}