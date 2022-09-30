package br.com.danilo.exemplo.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "Danilo",
        email = "danilo@email.com",
        password = "123456"
    )
}