package com.borntodev.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var arrayListOfTaskItem = ArrayList<TaskClass>()
    private val databaseName = "task_list"
    lateinit var recyclerView: RecyclerView
    private var adapter:TaskAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_main_main)

        rv_main_main.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(arrayListOfTaskItem, this)
        adapter!!.CurrencySelectorAdapter(this)
        rv_main_main.adapter = adapter

        fab_new_task_main.setOnClickListener{
            val taskTitle = edt_new_task_main.text.toString().trim()
            if (!TextUtils.isEmpty(taskTitle)){
                val taskItem = TaskClass(taskTitle, false)
                addItemInArrayListOfTaskItem(taskItem)
                edt_new_task_main.setText("")
                updateRecycleView()
            }else{
                Toast.makeText(this, "please enter task name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun flipImportantItemInArrayOfListItem(index:Int){
        val newArrayListOfTaskItem = ArrayList<TaskClass>()
        var count = 0
        for (i in arrayListOfTaskItem){
            if (count == index){
                val newItem = TaskClass(i.title.toString(), !i.important!!)
                newArrayListOfTaskItem.add(newItem)
            }else{
                newArrayListOfTaskItem.add(i)
            }
            count++
        }
        arrayListOfTaskItem.clear()
        for (i in newArrayListOfTaskItem){
            arrayListOfTaskItem.add(i)
        }
    }

    fun deleteItemFromArrayOfListItem(index:Int){
        arrayListOfTaskItem.removeAt(index)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRecycleView(){
        adapter!!.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        // read data from database
        val sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)

        var count = 0
        var check:Any? = ""
        while (check != null){
            val gson = Gson()
            val jsonData = sharedPreferences!!.getString(count.toString(), null)
            if (jsonData != null){
                val dataObjectItem = gson.fromJson(jsonData, TaskClass::class.java)
                addItemInArrayListOfTaskItem(dataObjectItem)
            }
            check = jsonData
            count++
        }
    }

    override fun onStop() {
        // save data to database
        super.onStop()

        val sharedPreferences = this.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
        val databaseEditor = sharedPreferences!!.edit()
        databaseEditor.clear()
        databaseEditor.apply()
        var count = 0
        for (i in arrayListOfTaskItem){
            val gson = Gson()
            val json = gson.toJson(i)
            databaseEditor.putString(count.toString(), json)
            count ++
        }
        databaseEditor.apply()
    }

    fun addItemInArrayListOfTaskItem(item:TaskClass){
        arrayListOfTaskItem.add(item)
    }
}