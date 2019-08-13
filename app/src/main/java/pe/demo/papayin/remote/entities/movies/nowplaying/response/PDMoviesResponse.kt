package pe.demo.papayin.remote.entities.movies.nowplaying.response

import com.google.gson.annotations.SerializedName
import pe.demo.papayin.remote.entities.base.PDBaseResponse

class PDMoviesResponse(
    @SerializedName("results")
    var results: List<PDMovieData>? = null
) : PDBaseResponse()