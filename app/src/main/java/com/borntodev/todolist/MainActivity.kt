package com.borntodev.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val databaseName = "task_list"
    private val arrayListOfTaskItem = ArrayList<TaskClass>()
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
        for (i in readDataFromDatabase()){
            arrayListOfTaskItem.add(i)
        }

        val layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter(arrayListOfTaskItem, this)
        rv_main_main.adapter = adapter
        rv_main_main.layoutManager = layoutManager
        
        fab_new_task_main.setOnClickListener{
            val taskTitle = edt_new_task_main.text.toString()
            val taskItem = TaskClass(taskTitle, false)
            newTask(taskItem)
        }
    }

    override fun onStart() {
        super.onStart()
        arrayListOfTaskItem.clear()
        for (i in readDataFromDatabase()){
            arrayListOfTaskItem.add(i)
        }
    }
    
    private fun newTask(item:TaskClass){
        val arrayOfTaskClass = ArrayList<TaskClass>()
        for (i in readDataFromDatabase()){
            arrayOfTaskClass.add(i)
        }
        arrayOfTaskClass.add(item)
        saveDataToDatabase(arrayOfTaskClass)
        Toast.makeText(this, "new task", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveDataToDatabase(arrayToSave:ArrayList<TaskClass>){
        val databaseEditor = sharedPreferences.edit()

        var count = 0
        for (i in arrayToSave){
            val gson = Gson()
            val json = gson.toJson(i)
            databaseEditor.putString(count.toString(), json)
            count ++
        }
        databaseEditor.apply()
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