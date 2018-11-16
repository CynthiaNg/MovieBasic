package com.nyp.sit.dit.it2107.movierater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
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

            if(!error)
                Toast.makeText(it.context, getToastData(), Toast.LENGTH_LONG).show()
        }

        chkbxMyCheckBox1.setOnCheckedChangeListener { _, isChecked ->
            val visibility = when(isChecked) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            chkbxMyCheckBox2.visibility = visibility
            chkbxMyCheckBox3.visibility = visibility
        }
    }

    fun getToastData() : String {
        return "Title = ${name.text} \n" +
                "Overview = ${description.text} \n" +
                "Release date = ${date.text} \n" +
                "Language = ${findViewById<RadioButton>(languageGroup.checkedRadioButtonId).text} \n" +
                "Suitable for all ages = ${!chkbxMyCheckBox1.isChecked}"
                    .let {
                        var reason : String = "\n Reason: \n";
                        if(chkbxMyCheckBox1.isChecked) {
                            if(chkbxMyCheckBox2.isChecked)
                                reason += "${chkbxMyCheckBox2.text} \n"
                            if(chkbxMyCheckBox3.isChecked)
                                reason += "${chkbxMyCheckBox3.text} \n"
                            return@let it+reason
                        }
                        it
                    }


    }
}
