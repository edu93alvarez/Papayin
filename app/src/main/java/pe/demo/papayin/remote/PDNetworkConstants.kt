package pe.demo.papayin.remote

class PDNetworkConstants {
    companion object {
        const val BASE_API_URL = "https://api.themoviedb.org"
        const val URL_NOW_PLAYING_MOVIES = "/3/movie/now_playing"
        const val URL_MOVIE_DETAIL = "/3/movie/{movie_id}"
        const val URL_MOVIE_VIDEOS = "/3/movie/{movie_id}/videos"
        const val URL_GENRES = "/3/genre/movie/list"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w200/"
        const val BASE_HEADER_URL = "https://image.tmdb.org/t/p/w400/"
        const val API_KEY_THE_MOVIE_DB = "752cd23fdb3336557bf3d8724e115570"
    }
}