package pe.demo.papayin.domain.interactor.genres

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.executor.PostExecutionThread
import pe.demo.papayin.domain.executor.ThreadExecutor
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.domain.repository.genres.PDGenresRepository
import pe.demo.papayin.remote.PDNetworkConstants

class PDGenresUseCase(
    private val genresRepository: PDGenresRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<PDMovieGender>, PDGenresUseCase.Params>(threadExecutor, postExecutionThread), KoinComponent {

    override fun buildUseCaseObservable(params: Params?): Single<List<PDMovieGender>> {
        return genresRepository.getGenres(PDNetworkConstants.API_KEY_THE_MOVIE_DB)
    }

    class Params constructor()

}