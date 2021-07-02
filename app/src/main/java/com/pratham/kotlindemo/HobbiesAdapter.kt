package com.pratham.kotlindemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.pratham.kotlindemo.Model.Model_Hobby
import kotlinx.android.synthetic.main.rv_item.view.*

class HobbiesAdapter(val context : Context, val hobbies : List<Model_Hobby>) : RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(hobby: Model_Hobby, position: Int){
            itemView.tv_name.text = hobby.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby = hobbies[position]
        holder.setData(hobby,position)

        holder.itemView.setOnClickListener {
            Toast.makeText(context,hobby.name,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }
}