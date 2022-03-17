package com.borntodev.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val databaseName = "task_list"
    private val arrayListOfTaskItem = ArrayList<TaskClass>()
    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)

        refreshRecycleView(readDataFromDatabase())

        val layoutManager = LinearLayoutManager(this)
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

    fun getSharedPreferencesVar(): SharedPreferences? {
        return sharedPreferences
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

    fun refreshRecycleView(data: ArrayList<TaskClass>){
        Log.d("myDebug", "refreshRecycleView")
        for (i in data){
            arrayListOfTaskItem.add(i)
        }
        val adapter = TaskAdapter(arrayListOfTaskItem, this)
        rv_main_main.adapter = adapter
    }
    private fun readDataFromDatabase(): ArrayList<TaskClass> {
        val arrayOfClass = ArrayList<TaskClass>()
        var count = 0
        var check:Any? = ""
        while (check != null){
            val gson = Gson()
            val jsonData = sharedPreferences!!.getString(count.toString(), null)
            if (jsonData != null){
                val dataObjectItem = gson.fromJson(jsonData, TaskClass::class.java)
                arrayOfClass.add(dataObjectItem)
            }
            check = jsonData
            count++
        }
        return arrayOfClass
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveDataToDatabase(arrayToSave:ArrayList<TaskClass>){
        Log.d("myDebug", "saveDataToDatabase")

        val databaseEditor = sharedPreferences!!.edit()
        var count = 0
        for (i in arrayToSave){
            val gson = Gson()
            val json = gson.toJson(i)
            databaseEditor.putString(count.toString(), json)
            count ++
        }
        databaseEditor.apply()
    }
}