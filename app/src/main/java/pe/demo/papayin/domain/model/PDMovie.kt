package pe.demo.papayin.domain.model

data class PDMovie(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var posterImagePath: String? = null,
    var headerImagePath: String? = null,
    var voteCount: Int? = null,
    var rating: Double? = null,
    var releaseDate: String? = null,
    var duration: Int? = null,
    var genres: MutableList<Int>? = null,
    var productionCompanies: String? = null,
    var productionCountries: String? = null
)