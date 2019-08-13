package pe.demo.papayin.remote.movies

import io.reactivex.Single
import pe.demo.papayin.data.contracts.movies.PDMoviesRemote
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.remote.PDNetworkApiInterface
import pe.demo.papayin.remote.mapper.movies.PDMoviesRemoteMapper

class PDMoviesRemoteImpl(
    private val apiInterface: PDNetworkApiInterface,
    private val movieRemoteMapper: PDMoviesRemoteMapper
) : PDMoviesRemote {

    override fun getMovieVideos(movieId: String, apiKey: String): Single<List<PDMovieTrailer>> {
        return apiInterface.getMovieVideos(movieId, apiKey)
            .map {
                movieRemoteMapper.transformMovieVideos(it)
            }
    }

    override fun getMovieDetail(movieId: String, apiKey: String): Single<PDMovie> {
        return apiInterface.getMovieDetail(movieId, apiKey)
            .map {
                movieRemoteMapper.transformMovieDetail(it)
            }
    }

    override fun getMovies(apiKey: String): Single<List<PDMovie>> {
        return apiInterface.getMovies(apiKey, "1")
            .map {
                movieRemoteMapper.mapFromRemote(it)
            }
    }
}