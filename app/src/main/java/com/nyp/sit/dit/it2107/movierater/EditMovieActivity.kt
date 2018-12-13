package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_movie_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EditMovieActivity : AppCompatActivity() {
    val movie = MainActivity.movie
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_item_cancel) {
            finish()
            return true
        }
        else if(item?.itemId == R.id.menu_item_save) {
            var error = false
            if(name.text.isEmpty()) {
                name.error = "Field empty"
                error = true
            }
            if(description.text.isEmpty()) {
                description.error = "Field empty"
                error = true
            }
            if(date.text.isEmpty()) {
                date.error = "Field empty"
                error = true
            }
            else {
                val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                formatter.setLenient(false)
                try {
                    formatter.parse(date.getText().toString())
                } catch (e: ParseException) {
                    date.error = "Format not correct"
                    error = true
                }
            }

            if(!error) {
                MainActivity.movie.title =  name.text.toString()
                MainActivity.movie.overview = description.text.toString()
                MainActivity.movie.releaseDate = date.text.toString()
                MainActivity.movie.language = findViewById<RadioButton>(languageGroup.checkedRadioButtonId).text.toString()
                MainActivity.movie.suitableForChildren = !chkbxMyCheckBox1.isChecked
                MainActivity.movie.violence = chkbxMyCheckBox2.isChecked
                MainActivity.movie.languageUsed = chkbxMyCheckBox3.isChecked

                startActivity(Intent(this@EditMovieActivity, MovieDetailActivity::class.java))
                finish()
            }
            return true
        }

        return false
    }
}
