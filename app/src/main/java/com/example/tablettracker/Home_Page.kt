package com.example.tablettracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerId = findViewById<Spinner>(R.id.dropdown)
        val choice = arrayOf("Prescription", "Medication")
        val array_Adp = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, choice)
        lateinit var chosen_Item: String
        spinnerId.adapter = array_Adp

        spinnerId?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosen_Item = choice[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //This will do nothing
            }
        }


        val bttn_Add: Button = findViewById(R.id.add_new)
        bttn_Add.setOnClickListener() {

            when (chosen_Item) {
                "Prescription" -> startActivity(Intent(this, Add_Prescription::class.java))
                "Medication" -> startActivity(Intent(this, add_Medication_New::class.java))
            }
        }

        val bttn_Remove_Edit: Button = findViewById(R.id.remove_Edit)
        bttn_Remove_Edit.setOnClickListener() {
            startActivity(Intent(this, Remove_Edit_Page::class.java))
        }


    }
}