package com.example.tablettracker

import kotlinx.coroutines.delay

class Medication {
    private var medication_Name:String = ""
    private var time = mutableListOf("")
    private var days_To_Take = mutableListOf("")
    private var dosage: String = ""

    fun main_Set_Up(medication_Name: String,dosage: String){
        this.medication_Name = medication_Name
        this.dosage = dosage

        println(this.medication_Name)
        println(this.dosage)
        println(this.time)
        println(this.days_To_Take)
    }
    fun add_time(time: String){
        this.time.add(time)//Split by :
    }

    fun get_time(): MutableList<String> {
        return(this.time)
    }

    fun remove_time(time:String){
        if(this.time.indexOf(time)!= 0)
            if(this.time.contains(time)){
                this.time.remove(time)
            }
    }

    fun add_days(days_To_Take: String): String {
        if(!this.days_To_Take.contains(days_To_Take)){
            this.days_To_Take.add(days_To_Take) //Remove duplicates? check if it is in the list already before adding :)
            return("added")
        }
        else if(this.days_To_Take.contains(days_To_Take)){ //Removes the day if it is added again.
            this.days_To_Take.remove(days_To_Take)
            return("removed")
        }

        return("error")

    }
    fun get_Name(): String {
        return(this.medication_Name)
    }

    fun get_Dosage(): String {
        return (this.dosage)
    }

    fun get_Days(): MutableList<String> {
        return (this.days_To_Take)
    }

}