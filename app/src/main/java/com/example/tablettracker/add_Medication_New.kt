package com.example.tablettracker

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
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

class add_Medication_New : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    //Private public variables for the time and date picker.
    private var hour = 0
    private var minute = 0
    private var SavedHour = 0
    private var SavedMinute = 0

    private var day = 0
    private var month = 0
    private var SavedDay = 0
    private var SavedMonth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_medication_new)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //---------------------------------------------------------------------------------------------------------------------------
        //Creating a new instance of Medication class
        val Medication_Item =  Medication()

        //---------------------------------------------------------------------------------------------------------------------------

        //Sets up dropdown box for choosing dosage measurements
        val dropdown_Choose_Dosage = findViewById<Spinner>(R.id.chooseDosage)
        val types_Of_Measurements = arrayOf("Tablets", "Mg", "mcg/μg")//sets up array of options
        val array_Adp_Dosage = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types_Of_Measurements) //Turns the types_Of_Measurements array and allows it to be turned into items for the dropdown box
        lateinit var chosen_Dosage:String//Stores the chosen item from the dropdown box.
        dropdown_Choose_Dosage.adapter = array_Adp_Dosage//Tells the dropdown box to use array_Adp_Dosage.


        dropdown_Choose_Dosage?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{ //When an item in the dropdown box is selected it runs the below
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { //Gets which item is selected.
                chosen_Dosage = types_Of_Measurements[p2] //Saves the item to be stored.
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //This will be used for validation later? maybe or it will remain empty as it will default to the pre selected item.
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------

        var time_Added = Medication_Item.get_time()
        lateinit var chosen_Time_Remove: String

        val bttn_Add_Time: Button = findViewById(R.id.add_Time)
        bttn_Add_Time.setOnClickListener(){
            Medication_Item.add_time("$SavedHour : $SavedMinute")
        }
        val bttn_Remove_Time: Button = findViewById(R.id.removeTime)
        bttn_Remove_Time.setOnClickListener(){
            Medication_Item.remove_time("$SavedHour : $SavedMinute")
        }

        //---------------------------------------------------------------------------------------------------------------------------

        val dropdown_time = findViewById<Spinner>(R.id.dropdownTime)

        val array_Adp_Time = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, time_Added)
        dropdown_time.adapter = array_Adp_Time

        dropdown_time?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosen_Time_Remove = time_Added[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------

        //This is ran when the choose time button is pressed
        val bttn_Time: Button = findViewById(R.id.chooseTime)
        bttn_Time.setOnClickListener(){
            getTimeCalendar() //This function creates a new instance of the Calendar class, shows the time picker section and stores all the time.
        }
        //---------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------


        val medicationName:EditText = findViewById(R.id.medicine_Name)
        val dosage:EditText = findViewById(R.id.dosage)


        val bttn_Confirm: Button = findViewById(R.id.confirm)
        bttn_Confirm.setOnClickListener(){
            //This calls the methods for the class.
            val str_Dosage = dosage.text.toString()
            Medication_Item.main_Set_Up(medicationName.text.toString(),"$str_Dosage $chosen_Dosage")

            val filename = "Medication"
            /*context.openFileOutput(filename, Context.MODE_PRIVATE).use {
                it.write(Medication_Item.get_Name(), ",", Medication_Item.get_Dosage(), ",", Medication_Item.get_time(), Medication_Item.get_Days())
            }
             */
            startActivity(Intent(this,Home_Page::class.java))
        }

        val bttn_Cancel: Button = findViewById(R.id.cancel)
        bttn_Cancel.setOnClickListener(){
            startActivity(Intent(this,Home_Page::class.java))
        }


        //---------------------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------------

        //This allows the user to select the days that they need to take the tablets on.
        val bttn_Monday: Button = findViewById(R.id.dayMonday)
        val bttn_Tuesday: Button = findViewById(R.id.dayTuesday)
        val bttn_Wednesday: Button = findViewById(R.id.dayWednesday)
        val bttn_Thursday: Button = findViewById(R.id.dayThursday)
        val bttn_Friday: Button = findViewById(R.id.dayFriday)
        val bttn_Saturday: Button = findViewById(R.id.daySaturday)
        val bttn_Sunday: Button = findViewById(R.id.daySunday)

        bttn_Monday.setOnClickListener(){

            when(Medication_Item.add_days("Monday")){ //case statement allows to check if the day has been added an updates the button text depending on the returned result.
                "added" -> bttn_Monday.text = "✓"
                "removed" -> bttn_Monday.text = "M"
            }
        }
        bttn_Tuesday.setOnClickListener(){
            when(Medication_Item.add_days("Tuesday")){
                "added" -> bttn_Tuesday.text = "✓"
                "removed" -> bttn_Tuesday.text = "T"
            }
        }
        bttn_Wednesday.setOnClickListener(){
            when(Medication_Item.add_days("Wednesday")){
                "added" -> bttn_Wednesday.text = "✓"
                "removed" -> bttn_Wednesday.text = "W"
            }
        }
        bttn_Thursday.setOnClickListener(){
            when(Medication_Item.add_days("Thursday")){
                "added" -> bttn_Thursday.text = "✓"
                "removed" -> bttn_Thursday.text = "T"
            }
        }
        bttn_Friday.setOnClickListener(){
            when(Medication_Item.add_days("Friday")){
                "added" -> bttn_Friday.text = "✓"
                "removed" -> bttn_Friday.text = "F"
            }
        }
        bttn_Saturday.setOnClickListener(){
            when(Medication_Item.add_days("Saturday")){
                "added" -> bttn_Saturday.text = "✓"
                "removed" -> bttn_Saturday.text = "S"
            }
        }
        bttn_Sunday.setOnClickListener(){
            when(Medication_Item.add_days("Sunday")){
                "added" -> bttn_Sunday.text = "✓"
                "removed" -> bttn_Sunday.text = "S"
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------
    }

    private fun getTimeCalendar(){ //Creates new calendar instance for the time picker
        val time_Cal : Calendar = Calendar.getInstance()
        hour = time_Cal.get(Calendar.HOUR_OF_DAY)
        minute = time_Cal.get(Calendar.MINUTE)
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        SavedHour = hour
        SavedMinute = minute
        var bttn_Time: Button = findViewById(R.id.chooseTime)
        bttn_Time.text = "$SavedHour : $SavedMinute"
    }
}