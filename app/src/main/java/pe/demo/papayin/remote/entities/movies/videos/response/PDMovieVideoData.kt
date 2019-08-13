package pe.demo.papayin.remote.entities.movies.videos.response

import com.google.gson.annotations.SerializedName

class PDMovieVideoData(
    @SerializedName("key")
    var key: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("site")
    var site: String? = null
)