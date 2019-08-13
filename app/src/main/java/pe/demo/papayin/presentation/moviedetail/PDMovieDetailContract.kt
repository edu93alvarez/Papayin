package pe.demo.papayin.presentation.moviedetail

import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.presentation.base.PDBasePresenter
import pe.demo.papayin.presentation.base.PDBaseView

interface PDMovieDetailContract {
    interface View : PDBaseView<Presenter> {
        fun onSuccessGetMovieDetail(movie: PDMovie)
        fun onFailureGetMovieDetail(message: String)
        fun onSuccessGetMovieVideos(movies: List<PDMovieTrailer>)
        fun onFailureGetMovieVideos(message: String)
    }

    interface Presenter : PDBasePresenter<View> {
        fun getMovieDetail(movieId: String?)
        fun getMovieVideos(movieId: String?)
    }
}