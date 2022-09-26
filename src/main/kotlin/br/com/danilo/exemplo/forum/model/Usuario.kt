package br.com.danilo.exemplo.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @JsonIgnore - para não exibir as roles do usuario quando consultado o recurso
 * @ManyToMany com fetchType EAGER para quando carregar o usuário busque também todas as roles imediatamente
 */
@Entity
data class Usuario (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_role")
    val role: List<Role> = mutableListOf()
)
