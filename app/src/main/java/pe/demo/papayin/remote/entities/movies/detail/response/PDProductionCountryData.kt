package pe.demo.papayin.remote.entities.movies.detail.response

import com.google.gson.annotations.SerializedName

class PDProductionCountryData(
    @SerializedName("iso_3166_1")
    var iso_3166_1: String? = null,
    @SerializedName("name")
    var name: String? = null
)