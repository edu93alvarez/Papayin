package pe.demo.papayin.remote.entities.genres

import com.google.gson.annotations.SerializedName

class PDGenreData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String? = null
)