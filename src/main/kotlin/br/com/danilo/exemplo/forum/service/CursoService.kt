package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Curso
import br.com.danilo.exemplo.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService (
    private val repository: CursoRepository
) {
    fun buscaPorId(id: Long): Curso {
        return repository.getReferenceById(id)
    }
}
