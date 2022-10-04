package br.com.danilo.exemplo.forum.repository

import br.com.danilo.exemplo.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long>