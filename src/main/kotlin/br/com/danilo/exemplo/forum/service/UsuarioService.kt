package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Usuario
import br.com.danilo.exemplo.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
data class UsuarioService(
    private val repository: UsuarioRepository
) {

    fun buscaPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

}
