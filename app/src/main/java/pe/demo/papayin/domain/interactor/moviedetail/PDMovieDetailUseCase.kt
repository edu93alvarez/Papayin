package pe.demo.papayin.domain.interactor.moviedetail

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.executor.PostExecutionThread
import pe.demo.papayin.domain.executor.ThreadExecutor
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.repository.movies.PDMoviesRepository
import pe.demo.papayin.remote.PDNetworkConstants

class PDMovieDetailUseCase(
    private val movieRepository: PDMoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<PDMovie, PDMovieDetailUseCase.Params>(threadExecutor, postExecutionThread), KoinComponent {

    override fun buildUseCaseObservable(params: Params?): Single<PDMovie> {
        return movieRepository.getMovieDetail(params!!.movieId ?: "", PDNetworkConstants.API_KEY_THE_MOVIE_DB)
    }

    class Params constructor(var movieId: String?)
}