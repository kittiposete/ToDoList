package com.borntodev.todolist

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borntodev.todolist.TaskAdapter.ViewHolder
import com.google.gson.Gson

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
        holder.dataBind(data!![position], position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val databaseName = "task_list"
        private var sharedPreferences: SharedPreferences ?= null
        private var rv_main_main:RecyclerView ?= null

        init {
            sharedPreferences = itemView.context.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
        }

        fun dataBind(data:TaskClass, index:Int){
            val tv_title_task_row:TextView = itemView.findViewById(R.id.tv_title_task_row)
            tv_title_task_row.text = data.title

            val iv_delete_task_row:ImageView = itemView.findViewById(R.id.iv_delete_task_row)
            iv_delete_task_row.setOnClickListener {
                deleteItemDatabase(index)
            }
        }

        private fun deleteItemDatabase(index:Int){
            Log.d("myDebug", "deleteItemDatabase")
            val data = readDataFromDatabase()
            data.removeAt(index)
            saveDataToDatabase(data)
            MainActivity().refreshRecycleView(data)
        }

        private fun readDataFromDatabase(): ArrayList<TaskClass> {
            val arrayOfClass = ArrayList<TaskClass>()
            var count = 0
            var endOfDataCheck:Any? = "" // if endOfDataCheck == null means that out of data
            while (endOfDataCheck != null){ // Until the data out, Read the data item by item.
                val gson = Gson()
                val jsonData = sharedPreferences!!.getString(count.toString(), null) //get json data as string type with index == count
                if (jsonData != null){
                    val dataObjectItem = gson.fromJson(jsonData, TaskClass::class.java) // convent json to object if json != null
                    arrayOfClass.add(dataObjectItem)
                }
                endOfDataCheck = jsonData
                count++
            }
            return arrayOfClass
        }

        private fun saveDataToDatabase(arrayToSave:ArrayList<TaskClass>){
            Log.d("myDebug", "saveDataToDatabase")

            val databaseEditor = sharedPreferences!!.edit()
            var count = 0
            for (i in arrayToSave){
                val gson = Gson()
                val json = gson.toJson(i) // convent object to json data  as string type
                databaseEditor.putString(count.toString(), json)
                count ++
            }
            databaseEditor.apply()
        }
    }
}
