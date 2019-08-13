package pe.demo.papayin.remote.entities.base

import com.google.gson.annotations.SerializedName

open class PDBaseResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("total_results")
    var total_results: Int? = null,
    @SerializedName("total_pages")
    var total_pages: Int? = null
)