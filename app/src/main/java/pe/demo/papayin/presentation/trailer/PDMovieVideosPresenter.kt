package pe.demo.papayin.presentation.movies

import io.reactivex.observers.DisposableSingleObserver
import org.koin.core.KoinComponent
import org.koin.core.inject
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.interactor.trailer.PDMovieTrailerUseCase
import pe.demo.papayin.domain.model.PDMovieTrailer

class PDMovieVideosPresenter : PDMovieVideosContract.Presenter, KoinComponent {

    override lateinit var view: PDMovieVideosContract.View
    private val movieVideosUseCase: SingleUseCase<List<PDMovieTrailer>, PDMovieTrailerUseCase.Params> by inject<PDMovieTrailerUseCase>()

    override fun getMovieVideos(movieId: String?) {
        view.showProgressLoading()
        movieVideosUseCase.execute(MovieVideoSubscriber(), PDMovieTrailerUseCase.Params(movieId))
    }

    inner class MovieVideoSubscriber : DisposableSingleObserver<List<PDMovieTrailer>>() {
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