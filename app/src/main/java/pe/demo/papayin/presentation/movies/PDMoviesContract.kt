package pe.demo.papayin.presentation.movies

import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.presentation.base.PDBasePresenter
import pe.demo.papayin.presentation.base.PDBaseView

interface PDMoviesContract {
    interface View : PDBaseView<Presenter> {
        fun onSuccessGetMovies(movies: List<PDMovie>)
        fun onFailureGetMovies(message: String)
    }

    interface Presenter : PDBasePresenter<View> {
        fun getMovies()
    }
}