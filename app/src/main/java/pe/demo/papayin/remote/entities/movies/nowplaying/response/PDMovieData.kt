package pe.demo.papayin.remote.entities.movies.nowplaying.response

import com.google.gson.annotations.SerializedName

class PDMovieData(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: String? = null,
    @SerializedName("vote_count")
    var vote_count: Int? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    @SerializedName("genre_ids")
    var genre_ids: List<Int>? = null,
    @SerializedName("release_date")
    var release_date: String? = null
)