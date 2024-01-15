package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    companion object{
        const val key1 = "com.example.contactapp.LoginActivity.name"
        const val key2 = "com.example.contactapp.LoginActivity.email"
        const val key3 = "com.example.contactapp.LoginActivity.ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var useridtxt = findViewById<TextInputEditText>(R.id.tVuserid)
        var login = findViewById<Button>(R.id.btnlogin)
        login.setOnClickListener { 
            var useridString = useridtxt.text.toString()
            if(useridString.isNotEmpty()){
                readData(useridString)
            }else{
                Toast.makeText(this,"Please Enter user ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(useridString: String) {
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(useridString).get().addOnSuccessListener {
            if(it.exists()){
                var name = it.child("name").value
                var email = it.child("email_ID").value
               var userid = it.child("userId").value
               var intent = Intent(this,MainScreen::class.java)
                intent.putExtra(key1,name.toString())
                intent.putExtra(key2,email.toString())
                intent.putExtra(key3,userid.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this,"User Does not Exist, Please sign in first",Toast.LENGTH_SHORT).show()
            }
        }
    }
}