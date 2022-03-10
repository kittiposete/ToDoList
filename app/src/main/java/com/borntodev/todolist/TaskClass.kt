package com.borntodev.todolist

class TaskClass() {
    var title:String ?= null
    var description:String ?= null
    var important:Boolean ?= null

    constructor(title:String, description:String, important:Boolean) : this() {
        this.title = title
        this.description = description
        this.important = important
    }
}