package com.nyp.sit.dit.it2107.movierater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_movie_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EditMovieActivity : AppCompatActivity() {
    val movie = MovieEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie_detail)

        name.setText(movie.title, TextView.BufferType.EDITABLE)
        description.setText(movie.overview, TextView.BufferType.EDITABLE)
        date.setText(movie.releaseDate, TextView.BufferType.EDITABLE)

        rbtnButtonEnglish.isChecked = true

        chkbxMyCheckBox1.setOnCheckedChangeListener { _, isChecked ->
            val visibility = when (isChecked) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            chkbxMyCheckBox2.visibility = visibility
            chkbxMyCheckBox3.visibility = visibility
        }

        chkbxMyCheckBox1.isChecked = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_movie_menu, menu)
        return true
    }
}
