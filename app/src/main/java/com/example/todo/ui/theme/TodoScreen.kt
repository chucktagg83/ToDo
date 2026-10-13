package com.example.todo.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.Task
import com.example.todo.TodoViewModel

@Composable
fun TodoScreen (viewModel: TodoViewModel) {
    var inputText by remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "My To-do List",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //challenge add the text field
            OutlinedTextField(
                value = inputText,
                onValueChange = {inputText = it},
                placeholder = {Text("Enter a task")},
                modifier = Modifier.weight(1f)
                    .testTag("inputField"),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(12.dp))
            // Add the button
            Button(
                onClick = {
                    viewModel.addTask(inputText)
                    inputText = ""
                },
                modifier = Modifier.testTag("addButton")
            ) {
                Text("Add")
            }
        } // end of Row for input
        Spacer(modifier = Modifier.height(16.dp))
        // Challenge add the task counter -- to show how many tasks are in the list
        Text(
            text = "Tasks: ${viewModel.tasks.size}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Scrollable list of tasks
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = viewModel.tasks,
                key = {task -> task.id},
            ) { task ->
                TaskItem(
                task = task,
                onDelete = { viewModel.removeTask(task.id)}
                )
            }
        }
    } // end of Column for content
} // end of TodoScreen

@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDelete, modifier = Modifier.testTag("removeButton")) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoScreen(viewModel = TodoViewModel())
}