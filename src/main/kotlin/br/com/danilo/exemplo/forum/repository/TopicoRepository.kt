package br.com.danilo.exemplo.forum.repository

import br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto
import br.com.danilo.exemplo.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Usa Generics passando entidade e tipo de id
 * <Entidade,Tipo do ID>
 */
interface TopicoRepository: JpaRepository<Topico, Long> {

    /**
     * findBY = Select From Topico com Join em Curso
     */
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    /**
     * Querys personalizadas no Repository
     */
    @Query("SELECT new br.com.danilo.exemplo.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>
}