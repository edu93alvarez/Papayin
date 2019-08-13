package pe.demo.papayin.presentation.moviedetail

import io.reactivex.observers.DisposableSingleObserver
import org.koin.core.KoinComponent
import org.koin.core.inject
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.interactor.genres.PDGenresUseCase
import pe.demo.papayin.domain.model.PDMovieGender

class PDGenresPresenter : PDGenresContract.Presenter, KoinComponent {
    override lateinit var view: PDGenresContract.View
    private val genresUseCase: SingleUseCase<List<PDMovieGender>, PDGenresUseCase.Params> by inject<PDGenresUseCase>()

    override fun getGenres() {
        view.showProgressLoading()
        genresUseCase.execute(GenResSubscriber(), PDGenresUseCase.Params())
    }

    inner class GenResSubscriber : DisposableSingleObserver<List<PDMovieGender>>() {
        override fun onError(e: Throwable) {
            view.hideProgressLoading()
            view.onFailureGetGenres(e.message.toString())
        }

        override fun onSuccess(genres: List<PDMovieGender>) {
            view.hideProgressLoading()
            view.onSuccessGetGenres(genres)
        }
    }

}