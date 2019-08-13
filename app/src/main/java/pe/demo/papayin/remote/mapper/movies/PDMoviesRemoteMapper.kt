package pe.demo.papayin.remote.mapper.movies

import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.remote.entities.movies.detail.response.PDMovieDetailResponse
import pe.demo.papayin.remote.entities.movies.nowplaying.response.PDMovieData
import pe.demo.papayin.remote.entities.movies.nowplaying.response.PDMoviesResponse
import pe.demo.papayin.remote.entities.movies.videos.response.PDMovieVideoData
import pe.demo.papayin.remote.entities.movies.videos.response.PDMovieVideosResponse
import pe.demo.papayin.remote.mapper.PDRemoteMapper

class PDMoviesRemoteMapper : PDRemoteMapper<PDMoviesResponse, List<PDMovie>> {

    override fun mapToRemote(type: List<PDMovie>): PDMoviesResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapFromRemote(response: PDMoviesResponse): List<PDMovie> {
        var movieList = mutableListOf<PDMovie>()
        response.results?.let {
            for (movieData in it) {
                movieList.add(transformMovie(movieData))
            }
        }
        return movieList
    }

    fun transformMovie(movieData: PDMovieData): PDMovie {
        val movie = PDMovie()
        movie.id = movieData.id
        movie.title = movieData.title ?: ""
        movie.posterImagePath = movieData.poster_path ?: ""
        movie.genres = movieData.genre_ids?.map { it }?.toMutableList()
        movie.rating = movieData.vote_average
        movie.releaseDate = movieData.release_date ?: ""
        return movie
    }

    fun transformMovieDetail(movieDetail: PDMovieDetailResponse): PDMovie {
        val movie = PDMovie()
        movie.id = movieDetail.id
        movie.title = movieDetail.title ?: ""
        movie.description = movieDetail.overview ?: ""
        movie.posterImagePath = movieDetail.poster_path ?: ""
        movie.headerImagePath = movieDetail.backdrop_path ?: ""
        //movie.genres = movieDetail?.genres?.toMutableList()?.joinToString { "${it.name}" }
        movie.genres = movieDetail.genres?.map { it.id }?.toMutableList()
        movie.productionCompanies = movieDetail.production_companies?.toMutableList()?.joinToString { "${it.name}" }
        movie.productionCountries = movieDetail.production_countries?.toMutableList()?.joinToString { "${it.name}" }
        movie.rating = movieDetail.vote_average
        movie.voteCount = movieDetail.vote_count
        movie.releaseDate = movieDetail.release_date ?: ""
        movie.duration = movieDetail.runtime
        return movie
    }

    fun transformMovieVideos(movieVideosResponse: PDMovieVideosResponse): List<PDMovieTrailer> {
        var movieVideoList = mutableListOf<PDMovieTrailer>()
        movieVideosResponse.results?.let {
            for (movieData in it) {
                movieVideoList.add(transformMovieVideo(movieData))
            }
        }
        return movieVideoList
    }

    fun transformMovieVideo(movieVideoData: PDMovieVideoData): PDMovieTrailer {
        val movieVideo = PDMovieTrailer()
        movieVideo.videoKey = movieVideoData.key
        movieVideo.name = movieVideoData.name ?: ""
        movieVideo.videoSource = movieVideoData.site ?: ""
        return movieVideo
    }

}