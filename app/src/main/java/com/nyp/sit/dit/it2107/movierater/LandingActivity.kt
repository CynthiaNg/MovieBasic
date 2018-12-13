package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing)

        //Long press trigger add movie menu
        val text = findViewById<TextView>(R.id.text)
        text.setOnLongClickListener {
            val popupMenu = PopupMenu(it.context, it)
            popupMenu.menuInflater.inflate(R.menu.landing_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {menuItem ->
                if(menuItem.itemId == R.id.menu_item_add) {
                    val intent = Intent(it.context, MainActivity::class.java)
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