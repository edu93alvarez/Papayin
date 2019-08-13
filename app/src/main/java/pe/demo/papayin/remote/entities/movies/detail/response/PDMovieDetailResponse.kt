package pe.demo.papayin.remote.entities.movies.detail.response

import com.google.gson.annotations.SerializedName
import pe.demo.papayin.remote.entities.genres.PDGenreData

class PDMovieDetailResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("genres")
    val genres: List<PDGenreData>? = null,
    @SerializedName("poster_path")
    val poster_path: String? = null,
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    @SerializedName("release_date")
    val release_date: String? = null,
    @SerializedName("original_title")
    val original_title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("production_countries")
    val production_countries: List<PDProductionCountryData>? = null,
    @SerializedName("production_companies")
    val production_companies: List<PDProductionCompanyData>? = null,
    @SerializedName("vote_average")
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    val vote_count: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null
)
