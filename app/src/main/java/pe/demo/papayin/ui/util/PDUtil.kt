package pe.demo.papayin.ui.util

import pe.demo.papayin.domain.model.PDMovieGender

class PDUtil {
    companion object {
        fun getGenresText(genreIds: MutableList<Int>?, genres: List<PDMovieGender>): String {
            var listGenres: MutableList<String> = mutableListOf()

            genreIds?.let {
                for (idGenre in it) {
                    listGenres.add(genres.find { it.id == idGenre }!!.gender)
                }
            }
            return listGenres.joinToString { it }
        }
    }
}