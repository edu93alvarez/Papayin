package pe.demo.papayin.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pe.demo.papayin.data.contracts.genres.PDGenreRemote
import pe.demo.papayin.data.contracts.movies.PDMoviesRemote
import pe.demo.papayin.data.repository.genres.PDGenresDataRepository
import pe.demo.papayin.data.repository.movies.PDMoviesDataRepository
import pe.demo.papayin.domain.executor.JobExecutor
import pe.demo.papayin.domain.executor.PostExecutionThread
import pe.demo.papayin.domain.executor.ThreadExecutor
import pe.demo.papayin.domain.interactor.genres.PDGenresUseCase
import pe.demo.papayin.domain.interactor.moviedetail.PDMovieDetailUseCase
import pe.demo.papayin.domain.interactor.movies.PDMoviesUseCase
import pe.demo.papayin.domain.interactor.trailer.PDMovieTrailerUseCase
import pe.demo.papayin.domain.repository.genres.PDGenresRepository
import pe.demo.papayin.domain.repository.movies.PDMoviesRepository
import pe.demo.papayin.presentation.moviedetail.PDGenresContract
import pe.demo.papayin.presentation.moviedetail.PDGenresPresenter
import pe.demo.papayin.presentation.moviedetail.PDMovieDetailContract
import pe.demo.papayin.presentation.moviedetail.PDMovieDetailPresenter
import pe.demo.papayin.presentation.movies.PDMovieVideosContract
import pe.demo.papayin.presentation.movies.PDMovieVideosPresenter
import pe.demo.papayin.presentation.movies.PDMoviesContract
import pe.demo.papayin.presentation.movies.PDMoviesPresenter
import pe.demo.papayin.remote.genres.PDGenresRemoteImpl
import pe.demo.papayin.remote.mapper.genres.PDGenreRemoteMapper
import pe.demo.papayin.remote.mapper.movies.PDMoviesRemoteMapper
import pe.demo.papayin.remote.movies.PDMoviesRemoteImpl
import pe.demo.papayin.ui.UiThread

val presentationModule = module {
    factory<PDMoviesContract.Presenter> { PDMoviesPresenter() }
    factory<PDMovieDetailContract.Presenter> { PDMovieDetailPresenter() }
    factory<PDMovieVideosContract.Presenter> { PDMovieVideosPresenter() }
    factory<PDGenresContract.Presenter> { PDGenresPresenter() }
}

val repositoryModule = module {
    single<ThreadExecutor>(named("threadExecutor")) { JobExecutor() }
    single<PostExecutionThread>(named("postExecutionThread")) { UiThread() }

    //movies
    single { PDMoviesRemoteMapper() }
    single<PDMoviesRemote> { PDMoviesRemoteImpl(get(), get()) }
    single<PDMoviesRepository>(named("movieRepository")) { PDMoviesDataRepository(get()) }
    //genres
    single { PDGenreRemoteMapper() }
    single<PDGenreRemote> { PDGenresRemoteImpl(get(), get()) }
    single<PDGenresRepository>(named("genresRepository")) { PDGenresDataRepository(get()) }


    factory {
        PDMoviesUseCase(
            get(named("movieRepository")),
            get(named("threadExecutor")),
            get(named("postExecutionThread"))
        )
    }

    factory {
        PDMovieDetailUseCase(
            get(named("movieRepository")),
            get(named("threadExecutor")),
            get(named("postExecutionThread"))
        )
    }

    factory {
        PDMovieTrailerUseCase(
            get(named("movieRepository")),
            get(named("threadExecutor")),
            get(named("postExecutionThread"))
        )
    }

    factory {
        PDGenresUseCase(
            get(named("genresRepository")),
            get(named("threadExecutor")),
            get(named("postExecutionThread"))
        )
    }
}
