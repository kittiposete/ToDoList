package com.borntodev.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_main_main:RecyclerView = findViewById(R.id.rv_main_main)

        val data = ArrayList<TaskClass>()
        data.add(TaskClass("title", "none", false))

        val layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter(data, this)

        rv_main_main.adapter = adapter
        rv_main_main.layoutManager = layoutManager
    }
}