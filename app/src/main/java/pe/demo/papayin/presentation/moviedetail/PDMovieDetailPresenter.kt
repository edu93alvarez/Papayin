package pe.demo.papayin.presentation.moviedetail

import io.reactivex.observers.DisposableSingleObserver
import org.koin.core.KoinComponent
import org.koin.core.inject
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.interactor.moviedetail.PDMovieDetailUseCase
import pe.demo.papayin.domain.interactor.trailer.PDMovieTrailerUseCase
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieTrailer

class PDMovieDetailPresenter : PDMovieDetailContract.Presenter, KoinComponent {
    override lateinit var view: PDMovieDetailContract.View
    private val movieDetailUseCase: SingleUseCase<PDMovie, PDMovieDetailUseCase.Params> by inject<PDMovieDetailUseCase>()
    private val movieTrailerUseCase: SingleUseCase<List<PDMovieTrailer>, PDMovieTrailerUseCase.Params> by inject<PDMovieTrailerUseCase>()

    override fun getMovieDetail(movieId: String?) {
        view.showProgressLoading()
        movieDetailUseCase.execute(MovieDetailSubscriber(), PDMovieDetailUseCase.Params((movieId)))
    }

    inner class MovieDetailSubscriber : DisposableSingleObserver<PDMovie>() {
        override fun onError(e: Throwable) {
            view.hideProgressLoading()
            view.onFailureGetMovieDetail(e.message.toString())
        }

        override fun onSuccess(movies: PDMovie) {
            view.hideProgressLoading()
            view.onSuccessGetMovieDetail(movies)
        }
    }

    override fun getMovieVideos(movieId: String?) {
        view.showProgressLoading()
        movieTrailerUseCase.execute(MovieTrailerSubscriber(), PDMovieTrailerUseCase.Params(movieId))
    }

    inner class MovieTrailerSubscriber : DisposableSingleObserver<List<PDMovieTrailer>>() {
        override fun onError(e: Throwable) {
            view.hideProgressLoading()
            view.onFailureGetMovieVideos(e.message.toString())
        }

        override fun onSuccess(movies: List<PDMovieTrailer>) {
            view.hideProgressLoading()
            view.onSuccessGetMovieVideos(movies)
        }
    }
}