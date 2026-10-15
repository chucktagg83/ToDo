package com.example.todo

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

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
        // Log.d: debug message - useful during development to track the state of the app
    }
    /*fun addTask(title: String) {
        if (title.isBlank()) return
        // viewModelScope.launch starts a new coroutine in the viewModelScope
        //Everything inside the launch block will run on a different thread (independently)
        viewModelScope.launch {
            // withContext switches to the IO dispatcher to run in the background
            // The main thread will not block while waiting for the results
            withContext(Dispatchers.IO){
                simulatesSlowOperation() // this blocks the main thread for 5 seconds
            }
        }
        _tasks.add(Task(id = _nextId++, title = title.trim()))
    }*/

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

    // This simulates a slow operation running on the main thread
    // Thread.sleep(5000) pauses the current thread for exactly 5 seconds
    // For testing purposes only -- we will remove this after the demo
    /*private fun simulatesSlowOperation(){
        Thread.sleep(5000)
    }*/
}