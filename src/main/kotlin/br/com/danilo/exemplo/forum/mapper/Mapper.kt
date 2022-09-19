package br.com.danilo.exemplo.forum.mapper

/**
 * Mapeia de um objeto de um TipoT para TipoU
 */
interface Mapper<T, U> {

    //Contrato map
    fun map(t: T): U
}