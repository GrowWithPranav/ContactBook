package com.example.contactapp

data class Contacts(var Contact_Name:String,var Phone_Number: String, var Gender:String){

    constructor() : this("", "", "")
}