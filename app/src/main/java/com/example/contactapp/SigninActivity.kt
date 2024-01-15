package com.example.contactapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SigninActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    companion object{
        const val keya = "com.example.contactapp.SigninActivity.name"
        const val keyb = "com.example.contactapp.SigninActivity.email"
        const val keyc = "com.example.contactapp.SigninActivity.ID"
    }
   var CHANNEL_NAME = "channel name"
    var CHANNEL_ID= "channel id"
    var notification_id = 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    createNotificationChannel()
        val intent = Intent(this, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        val Notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_verified_user_24)
            .setContentText("This is a trial text, Alert")
            .setContentTitle("My notification")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        var notificationManager = NotificationManagerCompat.from(this)


        var txtname = findViewById<TextInputEditText>(R.id.tVname)
        var txtemail = findViewById<TextInputEditText>(R.id.tVemail)
        var txtuserid = findViewById<TextInputEditText>(R.id.tVuserid)
        var txtpassword = findViewById<TextInputEditText>(R.id.tVpass)
        var Login = findViewById<Button>(R.id.btnlogin)
        var sign = findViewById<Button>(R.id.btnsign)

        Login.setOnClickListener {

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        sign.setOnClickListener {


            var name = txtname.text.toString()
            var email = txtemail.text.toString()
            var userid = txtuserid.text.toString()
            var password = txtpassword.text.toString()

            txtemail.text?.clear()
            txtname.text?.clear()
            txtuserid.text?.clear()
            txtpassword.text?.clear()
            var user = User(name, email,userid,password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(userid).setValue(user).addOnSuccessListener {
                notificationManager.notify(notification_id, Notification)


            }
        }

    }

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val  channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description ="Notification Alert"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}