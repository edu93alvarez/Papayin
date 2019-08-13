package pe.demo.papayin.ui.movies

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.ext.android.inject
import pe.demo.papayin.R
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.presentation.movies.PDMoviesContract
import pe.demo.papayin.ui.base.PDBaseActivity
import pe.demo.papayin.ui.moviedetail.PDMovieDetailActivity
import pe.demo.papayin.ui.util.PDConstans
import pe.demo.papayin.ui.util.PDUtilSharedPreference

class PDMoviesActivity : PDBaseActivity(), PDMoviesContract.View {

    override val presenter by inject<PDMoviesContract.Presenter>()
    private var mMovieList: List<PDMovie>? = null
    var mSharedPreferences: SharedPreferences? = null

    override fun getLayout(): Int {
        return R.layout.activity_movies
    }

    override fun setupView(savedInstanceState: Bundle?) {
        mSharedPreferences = this.getSharedPreferences(PDConstans.PD_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        presenter.view = this
        presenter.getMovies()
    }

    private fun setupAdapter() {
        rvMovies.layoutManager = LinearLayoutManager(
            this
        )
        val mMovieAdapter = PDMovieAdapter(mMovieList,
            PDUtilSharedPreference(mSharedPreferences).getList(PDConstans.GENRES_LIST, PDMovieGender::class.java),
            { movieItem: PDMovie -> onMovieItemClicked(movieItem) })
        rvMovies.adapter = mMovieAdapter
        mMovieAdapter.notifyDataSetChanged()
    }

    private fun onMovieItemClicked(movie: PDMovie) {
        val intent = Intent(this, PDMovieDetailActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

    override fun hideProgressLoading() {
    }

    override fun showProgressLoading() {

    }

    override fun onSuccessGetMovies(movies: List<PDMovie>) {
        mMovieList = movies
        setupAdapter()
    }

    override fun onFailureGetMovies(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
