package br.com.danilo.exemplo.forum.service

import br.com.danilo.exemplo.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService (
    var cursos: List<Curso>
) {
    init {
        val curso = Curso(id = 1, nome = "Kotlin", categoria = "Programacao")
        val curso2 = Curso(id = 2, nome = "Spring Boot", categoria = "Programacao")

        cursos = Arrays.asList(curso, curso2)
    }

    fun buscaPorId(id: Long): Curso {
        return cursos.stream().filter({
            curso -> curso.id == id
        }).findFirst().get()
    }
}
