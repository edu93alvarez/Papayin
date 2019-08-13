package pe.demo.papayin.remote.genres

import io.reactivex.Single
import pe.demo.papayin.data.contracts.genres.PDGenreRemote
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.remote.PDNetworkApiInterface
import pe.demo.papayin.remote.mapper.genres.PDGenreRemoteMapper

class PDGenresRemoteImpl(
    private val apiInterface: PDNetworkApiInterface,
    private val genresMapper: PDGenreRemoteMapper
) : PDGenreRemote {

    override fun getGenres(apiKey: String): Single<List<PDMovieGender>> {
        return apiInterface.getGenres(apiKey)
            .map {
                genresMapper.mapFromRemote(it)
            }
    }

}