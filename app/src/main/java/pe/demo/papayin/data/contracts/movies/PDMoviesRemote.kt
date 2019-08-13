package pe.demo.papayin.data.contracts.movies

import io.reactivex.Single
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer

interface PDMoviesRemote {
    fun getMovies(apiKey: String): Single<List<PDMovie>>
    fun getMovieDetail(movieId: String, apiKey: String): Single<PDMovie>
    fun getMovieVideos(movieId: String, apiKey: String): Single<List<PDMovieTrailer>>
}