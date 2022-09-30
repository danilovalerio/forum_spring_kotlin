package br.com.danilo.exemplo.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin API",
        mensagem = "Aprendendo Kotlin Api Testes",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}