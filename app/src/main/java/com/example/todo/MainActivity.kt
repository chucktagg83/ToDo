package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.ui.theme.ToDoTheme
import com.example.todo.ui.theme.TodoScreen

class MainActivity : ComponentActivity() {
    private val _viewModel : TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //This line causes the memory leak -- storing Activity context in a singleton
        AppState.initialize(this) // 'this' refers to the Activity
        setContent {
            ToDoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoScreen(viewModel = _viewModel)
                }

            }
        }
    }
}
