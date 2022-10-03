package br.com.danilo.exemplo.forum.model

import br.com.danilo.exemplo.forum.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin Bascio",
        mensagem = "Aprendendo Spring com Kotlin",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAltercao = LocalDate.now()
    )
}