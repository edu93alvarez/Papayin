package pe.demo.papayin.domain.interactor.trailer

import io.reactivex.Single
import org.koin.core.KoinComponent
import pe.demo.papayin.domain.SingleUseCase
import pe.demo.papayin.domain.executor.PostExecutionThread
import pe.demo.papayin.domain.executor.ThreadExecutor
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.domain.repository.movies.PDMoviesRepository
import pe.demo.papayin.remote.PDNetworkConstants

class PDMovieTrailerUseCase(
    private val movieRepository: PDMoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<PDMovieTrailer>, PDMovieTrailerUseCase.Params>(threadExecutor, postExecutionThread),
    KoinComponent {

    class Params constructor(var movieId: String?)

    override fun buildUseCaseObservable(params: Params?): Single<List<PDMovieTrailer>> {
        return movieRepository.getMovieVideos(params!!.movieId ?: "", PDNetworkConstants.API_KEY_THE_MOVIE_DB)
    }


}