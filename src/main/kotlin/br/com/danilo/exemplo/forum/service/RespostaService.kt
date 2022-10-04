package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.dto.NovaRespostaForm
import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.model.Resposta
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.model.Usuario
import br.com.danilo.exemplo.forum.repository.RespostaRepository
import br.com.danilo.exemplo.forum.repository.TopicoRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository
) {

    fun cadastrar(resposta: NovaRespostaForm){
        // todo objetos para teste remover depois
        val curso = Curso(
            id = 1,
            nome = "Kotlin Rest",
            categoria = "PROGRAMACAO"
        )

        val autor = Usuario(
            id = 1,
            nome = "Danilo",
            email = "danilo@email.com",
            password = "123456"
        )

        val topico = Topico(
            id = 1,
            titulo = "Kotlin API",
            mensagem = "Aprendendo Kotlin Api Testes",
            curso = curso,
            autor = autor
        )

        val respostaNova = Resposta(
            mensagem = resposta.mensagem,
            autor = autor,
            topico = topico,
            solucao = false
        )

        repository.save(respostaNova)
    }

}