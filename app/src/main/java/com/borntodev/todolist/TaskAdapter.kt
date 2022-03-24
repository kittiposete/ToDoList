package com.borntodev.todolist

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.borntodev.todolist.TaskAdapter.ViewHolder
import com.google.gson.Gson

class TaskAdapter(data:ArrayList<TaskClass>, context: Context): RecyclerView.Adapter<ViewHolder>() {
    private var data: ArrayList<TaskClass> ?= null
    private var context:Context ?= null
    private var mMainActivity:Activity ?= null

    init {
        this.data = data
        this.context = context
    }

    fun CurrencySelectorAdapter(activity: Activity) {
        mMainActivity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view, context!!, mMainActivity!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("myDebug", "update")
        holder.dataBind(data!![position], position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }


    class ViewHolder(itemView: View, context:Context, mActivity:Activity):RecyclerView.ViewHolder(itemView) {
        private var context:Context ?= null
        private val databaseName = "task_list"
        private var sharedPreferences: SharedPreferences ?= null
        private var mActivity:MainActivity ?= null

        init {
            this.context = context
            sharedPreferences = context.getSharedPreferences(databaseName, Context.MODE_PRIVATE)
            this.mActivity = mActivity as MainActivity
        }

        fun dataBind(data:TaskClass, index:Int){
            val tv_title_task_row:TextView = itemView.findViewById(R.id.tv_title_task_row)
            tv_title_task_row.text = data.title

            val iv_delete_task_row:ImageView = itemView.findViewById(R.id.iv_delete_task_row)
            val iv_important_task_row:ImageView = itemView.findViewById(R.id.iv_important_task_row)

            if (!data.important!!){
                iv_important_task_row.setImageResource(R.drawable.ic_star_disable)
            }else{
                iv_important_task_row.setImageResource(R.drawable.ic_star_enable)
            }

            iv_delete_task_row.setOnClickListener {
                Toast.makeText(mActivity, "delete", Toast.LENGTH_SHORT).show()
                mActivity!!.deleteItemFromArrayOfListItem(index)
                mActivity!!.updateRecycleView()
            }
            iv_important_task_row.setOnClickListener {
                mActivity!!.flipImportantItemInArrayOfListItem(index)
                mActivity!!.updateRecycleView()
            }
        }
    }
}
