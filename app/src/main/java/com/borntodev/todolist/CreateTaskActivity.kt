package com.borntodev.todolist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_task.*

class CreateTaskActivity : AppCompatActivity() {
    private val databaseName = "task_list"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)

        btn_add_create_task.setOnClickListener {
            val title = edt_title_create_task.text.toString()
            val description = edt_description_create_task.text.toString()
            val isImportant = false
            val dataObject = TaskClass(title, isImportant)
            putDataToDatabase(dataObject)
            finish()
        }
    }
    private fun putDataToDatabase(data:TaskClass){
        val arrayOfTaskClass = ArrayList<TaskClass>()
        for (i in readDataFromDatabase()){
            arrayOfTaskClass.add(i)
        }
        arrayOfTaskClass.add(data)
        saveDataToDatabase(arrayOfTaskClass)
    }

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