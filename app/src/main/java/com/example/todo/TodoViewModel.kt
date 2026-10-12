package com.example.todo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {
    //_task is private - only this class can access it or modify it
    //this is a compose-aware mutable list that holds all current task
    private val _tasks = mutableStateListOf<Task>()

    // Counter used to generate a unique id for each new task
    private var _nextId = 1

    // Challenge is declare the public value for task
    val tasks: List<Task> get() = _tasks

    //Adds a new task to the list
    fun addTask(title: String) {
        if (title.isBlank()) return
        _tasks.add(Task(id = _nextId++, title = title.trim()))
    }

    // Challenge create the removeTask function
    fun removeTask(taskId: Int) {
        _tasks.removeAll { it.id == taskId }
    }
    // Challenge return the number of tasks in the list
    // use in unit test to verify task were added correctly
    fun getTaskCount(): Int = _tasks.size
    // returns true if a task with the given title exist in the list
    // used in unit test to verify task were added correctly
    fun containsTask(title: String): Boolean {
        return _tasks.any {it.title == title }
    }
}