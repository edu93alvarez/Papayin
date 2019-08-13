package pe.demo.papayin.domain.repository.genres

import io.reactivex.Single
import pe.demo.papayin.domain.model.PDMovieGender

interface PDGenresRepository {
    fun getGenres(apiKey: String): Single<List<PDMovieGender>>
}
