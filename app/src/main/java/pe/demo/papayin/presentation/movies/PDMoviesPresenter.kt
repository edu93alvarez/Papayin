package pe.demo.papayin.presentation.movies

import io.reactivex.observers.DisposableSingleObserver
import org.koin.core.KoinComponent
import org.koin.core.inject
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.interactor.movies.PDMoviesUseCase
import pe.demo.papayin.domain.model.PDMovie

class PDMoviesPresenter : PDMoviesContract.Presenter, KoinComponent {
    override lateinit var view: PDMoviesContract.View
    private val movieUseCase: SingleUseCase<List<PDMovie>, PDMoviesUseCase.Params> by inject<PDMoviesUseCase>()

    override fun getMovies() {
        view?.showProgressLoading()
        movieUseCase.execute(MoviesSubscriber(), PDMoviesUseCase.Params())
    }

    inner class MoviesSubscriber : DisposableSingleObserver<List<PDMovie>>() {
        override fun onError(e: Throwable) {
            view?.hideProgressLoading()
            view?.onFailureGetMovies(e.message.toString())
        }

        override fun onSuccess(movies: List<PDMovie>) {
            view?.hideProgressLoading()
            view?.onSuccessGetMovies(movies)
        }

    }


}