package br.com.danilo.exemplo.forum.mapper

import br.com.danilo.exemplo.forum.dto.NovoTopicoForm
import br.com.danilo.exemplo.forum.model.Topico
import br.com.danilo.exemplo.forum.service.CursoService
import br.com.danilo.exemplo.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService
) : Mapper<NovoTopicoForm, Topico> {
    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscaPorId(t.idCurso),
            autor = usuarioService.buscaPorId(t.idAutor)
        )
    }
}
