package com.example.tablettracker

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Add_Prescription : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_prescription)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //---------------------------------------------------------------------------------------------------------------------------
        //Sets up dropdown box for choosing the time frame for renewal
        val dropdown_Renewal_Time_Frame = findViewById<Spinner>(R.id.renewalTimeFrame)
        val renewal_Options = arrayOf("Daily", "Weekly", "Fortnightly", "Monthly", "When ran out")
        val array_Adp_Time_Frame = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, renewal_Options)
        lateinit var chosen_Time_Frame:String
        dropdown_Renewal_Time_Frame.adapter = array_Adp_Time_Frame

        dropdown_Renewal_Time_Frame?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosen_Time_Frame = renewal_Options[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //This will be used for validation later? maybe or it will remain empty as it will default to the pre selected item.
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------
        //Sets up dropdown box for choosing the type of medication
        val dropdown_Type_Medication = findViewById<Spinner>(R.id.chooseTypeMedication)
        val type_Medication_Options = arrayOf("Tablets", "Ml")
        val array_Adp_Type_Medication = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, type_Medication_Options)
        lateinit var chosen_Type_Medication:String
        dropdown_Type_Medication.adapter = array_Adp_Type_Medication

        dropdown_Type_Medication?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosen_Type_Medication = type_Medication_Options[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //This will be used for validation later? maybe or it will remain empty as it will default to the pre selected item.
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------
        //This is initialising an object of the prescription class to allow for all the data to be stored easily.
        val prescription_Item = Prescription() //Creates new instance of Prescription class


        //These are getting the inputted text from the textboxes.
        val medication_Amount: EditText = findViewById(R.id.amnt_Medication)
        val medication_Name: EditText = findViewById(R.id.medicine_Name)
        val price: EditText = findViewById(R.id.price)
        val bttn_Confirm: Button = findViewById(R.id.confirm)

        //This runs when the confirm button is pressed and stores all the inputs as strings in the class.
        bttn_Confirm.setOnClickListener(){
            //Only run this if text boxes arent empty
            val str_medication_Amount = medication_Amount.text.toString()
            prescription_Item.main_Set_Up(medication_Name.text.toString(),price.text.toString(),"", "$str_medication_Amount $chosen_Type_Medication")
            startActivity(Intent(this, Home_Page::class.java))
        }

        val bttn_Cancel: Button = findViewById(R.id.cancel)
        bttn_Cancel.setOnClickListener(){
            startActivity(Intent(this, Home_Page::class.java))
        }
        //---------------------------------------------------------------------------------------------------------------------------






    }


}