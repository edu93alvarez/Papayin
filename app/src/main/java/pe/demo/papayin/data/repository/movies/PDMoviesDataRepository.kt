package pe.demo.papayin.data.repository.movies

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.data.contracts.movies.PDMoviesRemote
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.domain.repository.movies.PDMoviesRepository

class PDMoviesDataRepository(
    private val movieRemote: PDMoviesRemote
) : PDMoviesRepository, KoinComponent {

    override fun getMovieVideos(movieId: String, apiKey: String): Single<List<PDMovieTrailer>> {
        return movieRemote.getMovieVideos(movieId, apiKey)
    }

    override fun getMovieDetail(movieId: String, apiKey: String): Single<PDMovie> {
        return movieRemote.getMovieDetail(movieId, apiKey)
    }

    override fun getMovies(apiKey: String): Single<List<PDMovie>> {
        return movieRemote.getMovies(apiKey)
    }


}