package com.example.tablettracker

class Prescription {
    private var medication_Name:String = ""
    private var price: String = "£"
    private var date_To_Renew:String = ""
    private var amnt_To_Take:String = ""

    fun main_Set_Up(medication_Name: String, price:String, date_To_Renew: String,amnt_To_Take:String){
        this.medication_Name = medication_Name
        this.amnt_To_Take = amnt_To_Take
        this.price = this.price + "" + price
        this.date_To_Renew = date_To_Renew

        println(this.medication_Name)
        println(this.amnt_To_Take)
        println(this.price)
        println(this.date_To_Renew)

    }
}