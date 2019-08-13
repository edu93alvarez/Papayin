package pe.demo.papayin.remote.mapper

interface PDRemoteMapper<M, E> {

    fun mapFromRemote(response: M): E

    fun mapToRemote(type: E): M

}