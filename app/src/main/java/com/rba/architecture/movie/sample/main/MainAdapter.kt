package com.rba.architecture.movie.sample.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rba.architecture.movie.domain.model.MovieModel
import com.rba.architecture.movie.sample.R
import com.rba.architecture.movie.sample.util.diffUtil
import com.rba.architecture.movie.sample.util.inflate
import com.rba.architecture.movie.sample.util.loadUrl
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val listener: (MovieModel.ResultResponse) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var list: List<MovieModel.ResultResponse> by diffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent.inflate(R.layout.item_list, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }


    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieModel.ResultResponse) {
            itemView.tvTitle.text = movie.title
            itemView.tvDescription.text = movie.overview
            itemView.ivMovie.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }

}