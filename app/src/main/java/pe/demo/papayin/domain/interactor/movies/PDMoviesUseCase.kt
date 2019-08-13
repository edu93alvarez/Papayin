package pe.demo.papayin.domain.interactor.movies

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.executor.PostExecutionThread
import pe.demo.papayin.domain.executor.ThreadExecutor
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.repository.movies.PDMoviesRepository
import pe.demo.papayin.remote.PDNetworkConstants

class PDMoviesUseCase(
    private val movieRepository: PDMoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<PDMovie>, PDMoviesUseCase.Params>(threadExecutor, postExecutionThread), KoinComponent {

    class Params constructor()

    override fun buildUseCaseObservable(params: Params?): Single<List<PDMovie>> {
        return movieRepository.getMovies(PDNetworkConstants.API_KEY_THE_MOVIE_DB)
    }


}