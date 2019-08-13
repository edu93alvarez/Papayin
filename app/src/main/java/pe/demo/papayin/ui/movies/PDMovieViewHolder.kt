package pe.demo.papayin.ui.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieGender
import pe.demo.papayin.remote.PDNetworkConstants
import pe.demo.papayin.ui.util.PDUtil

class PDMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View?
        get() = itemView

    fun bind(itemMovie: PDMovie, genderList: List<PDMovieGender>, itemClickListener: (PDMovie) -> Unit) {
        tvTitle.text = itemMovie.title ?: ""
        tvGeners.text = PDUtil.getGenresText(itemMovie.genres, genderList)
        tvRate.text = itemMovie.rating.toString()
        tvRelease.text = itemMovie.releaseDate.toString()
        Glide.with(itemView.context).load(PDNetworkConstants.BASE_POSTER_URL + itemMovie.posterImagePath)
            .apply(RequestOptions().centerInside()).into(ivPoster)

        clMovie.setOnClickListener { itemClickListener(itemMovie) }
    }
}