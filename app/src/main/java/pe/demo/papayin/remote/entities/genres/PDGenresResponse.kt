package pe.demo.papayin.remote.entities.genres

import com.google.gson.annotations.SerializedName

class PDGenresResponse(
    @SerializedName("genres")
    val genres: List<PDGenreData>? = null
)