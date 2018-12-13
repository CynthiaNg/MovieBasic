package com.nyp.sit.dit.it2107.movierater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_rate_movie.*

class MovieRateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movie)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_rate_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_item_submit) {

            val rating = movieRateBar.rating
            val reviewText = movie_review.text

            var error = false

            if(rating == 0.0f){
                error = true
            }

            if(reviewText.isNullOrEmpty()) {
                error = true
            }

            if(!error) {

                val movie = MainActivity.movie
                movie.reviewStar = rating
                movie.reviewText = reviewText.toString()

                finish()
            }
            return true
        }

        return false
    }


}