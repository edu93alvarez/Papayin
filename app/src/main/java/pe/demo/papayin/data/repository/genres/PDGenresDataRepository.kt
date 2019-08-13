package pe.demo.papayin.data.repository.genres

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.data.contracts.genres.PDGenreRemote
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.domain.repository.genres.PDGenresRepository

class PDGenresDataRepository(
    private val genresRemote: PDGenreRemote
) : PDGenresRepository, KoinComponent {

    override fun getGenres(apiKey: String): Single<List<PDMovieGender>> {
        return genresRemote.getGenres(apiKey)
    }
}