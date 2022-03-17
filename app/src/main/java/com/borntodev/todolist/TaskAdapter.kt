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
    private val databaseName = "task_list"
    lateinit var sharedPreferences: SharedPreferences


    init {
        this.data = data
        this.context = context
//        sharedPreferences = this.context!!.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
//        sharedPreferences = MainActivity().getContextOfApplication()!!.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view, sharedPreferences)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(data!![position], position, context!!)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    class ViewHolder(itemView: View, private var sharedPreferences: SharedPreferences):RecyclerView.ViewHolder(itemView) {
        fun dataBind(data:TaskClass, index:Int, context:Context){
            val tv_title_task_row:TextView = itemView.findViewById(R.id.tv_title_task_row)
            tv_title_task_row.text = data.title

            val iv_delete_task_row:ImageView = itemView.findViewById(R.id.iv_delete_task_row)
            iv_delete_task_row.setOnClickListener {
                deleteItemInDatabase(index)
            }
        }

        private fun deleteItemInDatabase(index:Int){
            val data = readDataFromDatabase()
            data.removeAt(index)
            saveDataToDatabase(data)
        }

        private fun saveDataToDatabase(arrayToSave:ArrayList<TaskClass>){
            val databaseEditor = sharedPreferences.edit()
            databaseEditor.clear()
            var count = 0
            for (i in arrayToSave){
                val gson = Gson()
                val json = gson.toJson(i)
                databaseEditor.putString(count.toString(), json)
                databaseEditor.apply()
                Log.d("myDebug json", json)
                Log.d("myDebug count", count.toString())
                count ++
            }
            MainActivity().refreshRecycleView(arrayToSave)
        }

        private fun readDataFromDatabase(): ArrayList<TaskClass> {
            val arrayOfClass = ArrayList<TaskClass>()
            var count = 0
            var check:Any? = ""
            while (check != null){
                val gson = Gson()
                val jsonData = sharedPreferences.getString(count.toString(), null)
                if (jsonData != null){
                    val dataObjectItem = gson.fromJson(jsonData, TaskClass::class.java)
                    arrayOfClass.add(dataObjectItem)
                }
                check = jsonData
                count++
            }
            return arrayOfClass
        }
    }
}
