package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
data class UsuarioService(var usuarios: List<Usuario>) {
    init {
        val user1 = Usuario(id = 1, nome = "Danilo", email = "danilo_vs@hotmail.com")
        val user2 = Usuario(id = 2, nome = "Daniela", email = "danivviana@gmail.com")

        usuarios = Arrays.asList(user1, user2)
    }

    fun buscaPorId(id: Long): Usuario {
        return usuarios.stream().filter({
            user -> user.id == id
        }).findFirst().get()
    }

}
