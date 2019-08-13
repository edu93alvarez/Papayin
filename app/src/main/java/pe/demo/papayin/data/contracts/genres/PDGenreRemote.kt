package pe.demo.papayin.data.contracts.genres

import io.reactivex.Single
import pe.demo.papayin.domain.model.PDMovieGender

interface PDGenreRemote {
    fun getGenres(apiKey: String): Single<List<PDMovieGender>>
}