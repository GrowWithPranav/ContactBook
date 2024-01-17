package com.example.contactapp

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.jar.Attributes.Name

class MyAdapter(val context : Activity, val contactList : ArrayList<Contacts>):
RecyclerView.Adapter<MyAdapter.MyViewholder>(){
    class MyViewholder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val name : TextView
        val Phone: TextView
        val Gender : TextView
        val Image : ImageView
        init {
            name = itemView.findViewById(R.id.txtName)
            Phone = itemView.findViewById(R.id.txtNo)
            Gender = itemView.findViewById(R.id.txtgender)
            Image = itemView.findViewById(R.id.imgContact)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
    val itemView = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return  MyViewholder(itemView)
    }

    override fun getItemCount(): Int {
       return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val CurrentItem = contactList[position]
        holder.name.text =CurrentItem.Contact_Name.toString()
        holder.Phone.text = CurrentItem.Phone_Number.toString()
        holder.Gender.text= CurrentItem.Gender.toString()
    }
}