package pe.demo.papayin.remote.mapper.genres

import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.remote.entities.genres.PDGenreData
import pe.demo.papayin.remote.entities.genres.PDGenresResponse
import pe.demo.papayin.remote.mapper.PDRemoteMapper

class PDGenreRemoteMapper : PDRemoteMapper<PDGenresResponse, List<PDMovieGender>> {

    override fun mapFromRemote(response: PDGenresResponse): List<PDMovieGender> {
        var genres = mutableListOf<PDMovieGender>()
        response.genres?.let {
            for (movieData in it) {
                genres.add(transformGenres(movieData))
            }
        }
        return genres
    }

    override fun mapToRemote(type: List<PDMovieGender>): PDGenresResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun transformGenres(genreData: PDGenreData): PDMovieGender {
        return PDMovieGender(
            genreData.id,
            genreData.name ?: ""
        )
    }

}