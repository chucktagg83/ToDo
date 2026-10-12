package com.example.todo

data class Task(
    // A data class automatically generates equals(), hashCode(), to String() and copy()
    // This means two Task objects with the same id and title are considered equal
    val id : Int, // unique identifier for the task
    val title: String, // the text the user types
)