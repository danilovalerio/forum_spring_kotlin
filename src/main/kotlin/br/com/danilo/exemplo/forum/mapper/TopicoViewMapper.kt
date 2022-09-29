package br.com.danilo.exemplo.forum.mapper

import br.com.danilo.exemplo.forum.dto.TopicoView
import br.com.danilo.exemplo.forum.model.Topico
import org.springframework.stereotype.Component

/**
 * Converte de um Topico (model) para um TopicoView
 * Sera um @Component (componente) por questão de semantica, mas poderia ser service
 */
@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(topico: Topico): TopicoView {
        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status,
            dataAltercao = topico.dataAlteracao
        )
    }
}