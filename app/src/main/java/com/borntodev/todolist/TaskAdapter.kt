package com.borntodev.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borntodev.todolist.TaskAdapter.ViewHolder

class TaskAdapter(data:ArrayList<TaskClass>, context: Context): RecyclerView.Adapter<ViewHolder>() {

    private var data: ArrayList<TaskClass> ?= null
    private var context:Context ?= null

    init {
        this.data = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(data!![position])
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun dataBind(data:TaskClass){
            val tv_title_task_row:TextView = itemView.findViewById(R.id.tv_title_task_row)
            tv_title_task_row.text = data.title
        }
    }
}