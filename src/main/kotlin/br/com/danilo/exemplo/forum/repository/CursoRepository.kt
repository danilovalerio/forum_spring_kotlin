package br.com.danilo.exemplo.forum.repository

import br.com.danilo.exemplo.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}