package pe.demo.papayin.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.demo.papayin.R
import pe.demo.papayin.domain.model.PDMovie
import pe.demo.papayin.domain.model.PDMovieGender

class PDMovieAdapter(
    private var mMovieList: List<PDMovie>?,
    private var genderList: List<PDMovieGender>,
    private val itemClickListener: (PDMovie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder: PDMovieViewHolder = holder as PDMovieViewHolder
        mMovieList?.get(position)?.let { movie -> movieViewHolder.bind(movie, genderList, itemClickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PDMovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return mMovieList?.size ?: 0
    }
}