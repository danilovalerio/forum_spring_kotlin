package br.com.danilo.exemplo.forum.repository

import br.com.danilo.exemplo.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Usa Generics passando entidade e tipo de id
 * <Entidade,Tipo do ID>
 */
interface TopicoRepository: JpaRepository<Topico, Long> {

    /**
     * findBY = Select From Topico com Join em Curso
     */
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>
}