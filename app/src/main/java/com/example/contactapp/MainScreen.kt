package com.example.contactapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainScreen : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var dialog: Dialog
   /* companion object {
       const val key1 = "com.example.contactapp.MainScreen.name"
       const  val key2 = "com.example.contactapp.MainScreen.phone"
       const val key3 = "com.example.contactapp.MainScreen.gender"
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        var name = intent.getStringExtra(LoginActivity.key1)
        var mail = intent.getStringExtra(LoginActivity.key2)
        var userid = intent.getStringExtra(LoginActivity.key3)
        var welcome = findViewById<TextView>(R.id.tVwelcom)
        var gender = findViewById<TextInputEditText>(R.id.tVuser)

        welcome.text="WELCOME ${name?.capitalize()}"
        var ctname = findViewById<TextInputEditText>(R.id.tVctname)
        var number = findViewById<TextInputEditText>(R.id.tVnumber)
        var add = findViewById<Button>(R.id.btnadd)
        dialog= Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.dialogback))

        val btnshow = dialog.findViewById<Button>(R.id.show)

        btnshow.setOnClickListener {
            val intent = Intent(this,showActivity::class.java)
            startActivity(intent)
        }

        var btnok = dialog.findViewById<Button>(R.id.OK)
        btnok.setOnClickListener {
            dialog.dismiss()
        }

        add.setOnClickListener {

            var phnnum = number.text.toString()
            var Gender = gender.text.toString()
            var name = ctname.text.toString()
            var cont =Contacts(name,phnnum,Gender)
            databaseReference=FirebaseDatabase.getInstance().getReference("Contacts")
            databaseReference.child(phnnum).setValue(cont).addOnSuccessListener {
                dialog.show()
            }
        }
    }
}