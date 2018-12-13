package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }

    override fun onResume() {
        super.onResume()
        val movie = MainActivity.movie

        movie_title.text = movie.title
        overview.text = movie.overview
        langauge.text = movie.language
        release_date.text = movie.releaseDate
        suitable.text = when(movie.suitableForChildren) {
            true -> "Yes"
            false -> "No"
        }

        if(movie.reviewStar != -1f) {
            ratingContainer.visibility = View.VISIBLE
            no_reviews.visibility = View.GONE

            movieRateBar.rating = movie.reviewStar
            movie_review.text = movie.reviewText
        }

        else {
            ratingContainer.visibility = View.GONE
            no_reviews.visibility = View.VISIBLE

            //Long press trigger add movie menu
            no_reviews.setOnLongClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.menuInflater.inflate(R.menu.review_menu,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {menuItem ->
                    if(menuItem.itemId == R.id.menu_item_add_review) {
                        val intent = Intent(it.context, MovieRateActivity::class.java)
                        startActivity(intent)
                        return@setOnMenuItemClickListener true
                    }
                    return@setOnMenuItemClickListener false
                }

                popupMenu.gravity = Gravity.END
                popupMenu.show()

                return@setOnLongClickListener true
            }
        }


    }
}