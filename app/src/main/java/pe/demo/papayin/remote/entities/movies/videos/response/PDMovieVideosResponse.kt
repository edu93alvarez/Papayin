package pe.demo.papayin.remote.entities.movies.videos.response

import com.google.gson.annotations.SerializedName

class PDMovieVideosResponse(
    @SerializedName("results")
    var results: List<PDMovieVideoData>? = null
)