package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.view.*

import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing)
    }

    override fun onResume() {
        super.onResume()

        if(MainActivity.movie.title.isEmpty()) {
            movie_item_container.visibility = View.GONE
        }

        else {
            movie_item_container.visibility = View.VISIBLE
            movie_title.text = MainActivity.movie.title

            movie_item_container.setOnLongClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.menuInflater.inflate(R.menu.edit_movie_item,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {menuItem ->
                    if(menuItem.itemId == R.id.menu_item_edit) {
                        val intent = Intent(it.context, EditMovieActivity::class.java)
                        startActivity(intent)
                        return@setOnMenuItemClickListener true
                    }
                    return@setOnMenuItemClickListener false
                }

                popupMenu.gravity = Gravity.END
                popupMenu.show()

                return@setOnLongClickListener true

            }

            movie_item_container.setOnClickListener {
                val intent = Intent(it.context, MovieDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landing_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_item_add) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }
}