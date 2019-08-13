package pe.demo.papayin.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject
import pe.demo.papayin.R
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.domain.model.PDMovieTrailer
import pe.demo.papayin.presentation.moviedetail.PDMovieDetailContract
import pe.demo.papayin.remote.PDNetworkConstants
import pe.demo.papayin.ui.base.PDBaseActivity
import pe.demo.papayin.ui.trailer.PDTrailerActivity
import pe.demo.papayin.ui.util.PDConstans
import pe.demo.papayin.ui.util.PDUtil
import pe.demo.papayin.ui.util.PDUtilSharedPreference

class PDMovieDetailActivity : PDBaseActivity(), PDMovieDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    override val presenter by inject<PDMovieDetailContract.Presenter>()
    private var mMovie = PDMovie()
    private var mMovieId: String? = ""
    var mSharedPreferences: SharedPreferences? = null
    private var mMovieTrailers = mutableListOf<PDMovieTrailer>()
    private var scrollRange: Int = -1
    private var isShow = false

    override fun getLayout(): Int {
        return R.layout.activity_movie_detail
    }

    override fun setupView(savedInstanceState: Bundle?) {
        mSharedPreferences = this.getSharedPreferences(PDConstans.PD_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        getDataFromIntent()
        appBarLayout.addOnOffsetChangedListener(this)
        presenter.view = this
        presenter.getMovieDetail(mMovieId)
    }

    private fun getDataFromIntent() {
        val bundle: Bundle? = intent.extras
        mMovieId = bundle?.getInt("movie_id").toString()
    }

    override fun onSuccessGetMovieDetail(movie: PDMovie) {
        this.mMovie = movie
        setDetailMovie()
        presenter.getMovieVideos(mMovieId)
    }

    private fun setDetailMovie() {
        tvTitle.text = mMovie.title
        tvDateRelease.text = mMovie.releaseDate
        tvGenres.text = PDUtil.getGenresText(
            mMovie.genres,
            PDUtilSharedPreference(mSharedPreferences).getList(PDConstans.GENRES_LIST, PDMovieGender::class.java)
        )
        tvRate.text = mMovie.rating.toString()
        tvDuration.text = mMovie.duration.toString()
        tvDescription.text = mMovie.description
        tvCompanyProduction.text = mMovie.productionCompanies
        tvCountryProduction.text = mMovie.productionCountries
        Glide.with(this).load(PDNetworkConstants.BASE_POSTER_URL + mMovie.posterImagePath)
            .apply(RequestOptions().fitCenter()).into(ivPoster)
        Glide.with(this).load(PDNetworkConstants.BASE_HEADER_URL + mMovie.headerImagePath)
            .apply(RequestOptions().fitCenter()).into(ivMovieCover)
    }

    override fun onFailureGetMovieDetail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetMovieVideos(movies: List<PDMovieTrailer>) {
        this.mMovieTrailers = movies.toMutableList()
        var trailerList: MutableList<String> = movies.map { it.name ?: "" }.toMutableList()
        val adapter = ArrayAdapter(this, R.layout.item_trailer, trailerList)
        lvTrailers.adapter = adapter
        lvTrailers.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            if (mMovieTrailers.get(position).videoSource.equals("YouTube")) {
                val intent = Intent(this, PDTrailerActivity::class.java)
                intent.putExtra("video_id", mMovieTrailers.get(position).videoKey)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Por el momento, solo trailers de YouTube", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onFailureGetMovieVideos(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOffsetChanged(appBar: AppBarLayout?, offSet: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }
        if (scrollRange + offSet == 0) {
            isShow = true
            ivPoster.visibility = View.GONE
            startGuideline.setGuidelinePercent(0f)
        } else if (isShow) {
            isShow = false
            ivPoster.visibility = View.VISIBLE
            startGuideline.setGuidelinePercent(0.4f)
        }
    }

    override fun showProgressLoading() {
    }

    override fun hideProgressLoading() {
    }

}
