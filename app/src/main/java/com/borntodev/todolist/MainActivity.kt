package com.borntodev.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val databaseName = "task_list"
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
        val rv_main_main:RecyclerView = findViewById(R.id.rv_main_main)
        val btn_create_task_main:Button = findViewById(R.id.btn_create_task_main)

        val data = ArrayList<TaskClass>()
        data.add(TaskClass("title", "dd", false))
        data.add(TaskClass("title2", "des", true))
        data.add(TaskClass("title3", "aaaa", false))
        saveDataToDatabase(data)

        val layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter(readDataFromDatabase(), this)

        rv_main_main.adapter = adapter
        rv_main_main.layoutManager = layoutManager

        btn_create_task_main.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }
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
        databaseEditor.apply();
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