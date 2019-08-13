package pe.demo.papayin.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_splash.*

import org.koin.android.ext.android.inject
import pe.demo.papayin.R
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.presentation.moviedetail.PDGenresContract
import pe.demo.papayin.ui.base.PDBaseActivity
import pe.demo.papayin.ui.movies.PDMoviesActivity
import pe.demo.papayin.ui.util.PDConstans
import pe.demo.papayin.ui.util.PDUtilSharedPreference


class PDSplashActivity : PDBaseActivity(), PDGenresContract.View {
    override val presenter by inject<PDGenresContract.Presenter>()
    private var mSharedPreferences: SharedPreferences? = null
    private val mHandler = Handler()

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun setupView(savedInstanceState: Bundle?) {
        mSharedPreferences = this.getSharedPreferences(PDConstans.PD_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        presenter.view = this
        loadAnimations()
    }

    override fun onSuccessGetGenres(genres: List<PDMovieGender>) {
        PDUtilSharedPreference(mSharedPreferences).setList(PDConstans.GENRES_LIST, genres)
        startActivity(Intent(this, PDMoviesActivity::class.java))
        finish()
    }

    override fun onFailureGetGenres(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun loadAnimations() {
        mHandler.postDelayed({
            startLogoAnimation()
            fadeAnimation()
        }, 200)
    }

    @SuppressLint("NewApi")
    fun startLogoAnimation() {
        val mConstraintSet = ConstraintSet()
        mConstraintSet.clone(clSplash)
        mConstraintSet.setVerticalBias(R.id.ivMovieDb, 0.5f)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000
        transition.addListener(object : Transition.TransitionListener {

            override fun onTransitionEnd(transition: Transition?) {
                pbLoading.visibility = View.VISIBLE
                tvLoading.visibility = View.VISIBLE
                presenter.getGenres()
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }

        })
        TransitionManager.beginDelayedTransition(clSplash, transition)
        mConstraintSet.applyTo(clSplash)

    }

    private fun fadeAnimation() {
        ivMovieDb.visibility = View.VISIBLE
        ivMovieDb.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
    }

    override fun showProgressLoading() {

    }

    override fun hideProgressLoading() {
    }

}
