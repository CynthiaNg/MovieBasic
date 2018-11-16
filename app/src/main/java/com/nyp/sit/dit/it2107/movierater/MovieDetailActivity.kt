package com.nyp.sit.dit.it2107.movierater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = MovieEntity()

        movie_title.text = movie.title
        overview.text = movie.overview
        langauge.text = movie.language
        release_date.text = movie.releaseDate
        suitable.text = when(movie.suitableForChildren) {
            true -> "Yes"
            false -> "No"
        }
    }
}