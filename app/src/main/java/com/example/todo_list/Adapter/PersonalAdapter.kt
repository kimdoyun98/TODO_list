package com.example.todo_list.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.Adapter.PersonalAdapter.MyViewHolder
import com.example.todo_list.R
import com.example.todo_list.data.ToDoEntity

class PersonalAdapter(val context: Context,
                      val list : List<ToDoEntity>) : RecyclerView.Adapter<MyViewHolder>(){

    // Controller
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){

        var title :TextView = v.findViewById(R.id.recycler_title)
        var startDate : TextView = v.findViewById(R.id.recycler_startdate)
        var deadLine : TextView = v.findViewById(R.id.recycler_deadline)
        var rating : TextView = v.findViewById(R.id.recycler_importance)
    }

    // 여기서 set 설정
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: ToDoEntity = list.get(position)
        holder.title.text = data.title
        holder.startDate.text = data.start_date
        holder.deadLine.text = data.deadline_date
        holder.rating.text = data.importance.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflatedView = LayoutInflater.from(context)
            .inflate(R.layout.recyclerview, parent, false)
        return MyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
