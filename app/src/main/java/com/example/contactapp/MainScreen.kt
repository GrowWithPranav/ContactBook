package com.example.contactapp

import android.app.Dialog
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        var name = intent.getStringExtra(LoginActivity.key1)
        var mail = intent.getStringExtra(LoginActivity.key2)
        var userid = intent.getStringExtra(LoginActivity.key3)
        var welcome = findViewById<TextView>(R.id.tVwelcom)
        var ID = findViewById<TextInputEditText>(R.id.tVuser)

        welcome.text="WELCOME ${name?.uppercase()}"
        var ctname = findViewById<TextInputEditText>(R.id.tVctname)
        var number = findViewById<TextInputEditText>(R.id.tVnumber)
        var add = findViewById<Button>(R.id.btnadd)
        dialog= Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.dialogback))

        var btnok = dialog.findViewById<Button>(R.id.OK)
        btnok.setOnClickListener {
            dialog.dismiss()
        }

        add.setOnClickListener {

            var phnnum = number.text.toString()
            var UserID = ID.text.toString()
            var name = ctname.text.toString()
            var cont =contact(name,phnnum,UserID)
            databaseReference=FirebaseDatabase.getInstance().getReference("Contacts")
            databaseReference.child(UserID).setValue(cont).addOnSuccessListener {
                dialog.show()
            }
        }
    }
}