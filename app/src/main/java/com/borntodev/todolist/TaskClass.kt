package com.borntodev.todolist

open class TaskClass() {
    var title:String ?= null
    var important:Boolean ?= null

    constructor(title:String, important:Boolean) : this() {
        this.title = title
        this.important = important
    }
}