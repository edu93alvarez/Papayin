package pe.demo.papayin.remote

import io.reactivex.Single
import pe.demo.papayin.remote.entities.genres.PDGenresResponse
import pe.demo.papayin.remote.entities.movies.detail.response.PDMovieDetailResponse
import pe.demo.papayin.remote.entities.movies.nowplaying.response.PDMoviesResponse
import pe.demo.papayin.remote.entities.movies.videos.response.PDMovieVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PDNetworkApiInterface {
    @GET(PDNetworkConstants.URL_NOW_PLAYING_MOVIES)
    fun getMovies(
        @Query("api_key") apiKey: String, @Query("page") page: String
    ): Single<PDMoviesResponse>

    @GET(PDNetworkConstants.URL_MOVIE_DETAIL)
    fun getMovieDetail(
        @Path("movie_id") movieId: String, @Query("api_key") apiKey: String
    ): Single<PDMovieDetailResponse>

    @GET(PDNetworkConstants.URL_MOVIE_VIDEOS)
    fun getMovieVideos(
        @Path("movie_id") movieId: String, @Query("api_key") apiKey: String
    ): Single<PDMovieVideosResponse>

    @GET(PDNetworkConstants.URL_GENRES)
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Single<PDGenresResponse>
}