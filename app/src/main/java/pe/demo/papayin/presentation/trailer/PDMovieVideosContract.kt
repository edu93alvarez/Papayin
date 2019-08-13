package pe.demo.papayin.presentation.movies

import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.presentation.base.PDBasePresenter
import pe.demo.papayin.presentation.base.PDBaseView

interface PDMovieVideosContract {
    interface View : PDBaseView<Presenter> {
        fun onSuccessGetMovieVideos(movies: List<PDMovieTrailer>)
        fun onFailureGetMovieVideos(message: String)
    }

    interface Presenter : PDBasePresenter<View> {
        fun getMovieVideos(movieId: String?)
    }
}