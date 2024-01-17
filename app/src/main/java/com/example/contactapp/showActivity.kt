package com.example.contactapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class showActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Contacts>
    lateinit var myAdapter: MyAdapter
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)


        recyclerView= findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        list = ArrayList()

        myAdapter = MyAdapter(this, list)
        recyclerView.adapter = myAdapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Contacts")

        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (contactSnapshot in snapshot.children){
                    val cont = contactSnapshot.getValue(Contacts::class.java)
                   // Log.d("FirebaseData", "Received contact: $cont")
                    if (cont!= null){
                        list.add(cont)
                    }
                }
                myAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@showActivity, "Unable to fetch data", Toast.LENGTH_SHORT).show()
            }
        })


    }


}

/*   val name = intent.getStringExtra(MainScreen.key1)
       val number = intent.getStringExtra(MainScreen.key2)
       val gender = intent.getStringExtra(MainScreen.key3)*/