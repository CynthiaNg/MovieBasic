package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val movie = MovieEntity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chkbxMyCheckBox1.setOnCheckedChangeListener { _, isChecked ->
            val visibility = when(isChecked) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            chkbxMyCheckBox2.visibility = visibility
            chkbxMyCheckBox3.visibility = visibility
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_item_add) {
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
                movie.title =  name.text.toString()
                movie.overview = description.text.toString()
                movie.releaseDate = date.text.toString()
                movie.language = findViewById<RadioButton>(languageGroup.checkedRadioButtonId).text.toString()
                movie.suitableForChildren = !chkbxMyCheckBox1.isChecked
                movie.violence = chkbxMyCheckBox2.isChecked
                movie.languageUsed = chkbxMyCheckBox3.isChecked

                startActivity(Intent(this@MainActivity, MovieDetailActivity::class.java))
                finish()
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
