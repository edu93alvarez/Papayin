package pe.demo.papayin.presentation.moviedetail

import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.presentation.base.PDBasePresenter
import pe.demo.papayin.presentation.base.PDBaseView

interface PDGenresContract {
    interface View : PDBaseView<Presenter> {
        fun onSuccessGetGenres(genres: List<PDMovieGender>)
        fun onFailureGetGenres(message: String)
    }

    interface Presenter : PDBasePresenter<View> {
        fun getGenres()
    }
}