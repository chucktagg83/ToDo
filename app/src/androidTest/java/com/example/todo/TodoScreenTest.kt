package com.example.todo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.todo.ui.theme.TodoScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoScreenTest {
    @get:Rule
    // This rule allows us to run Compose UI tests in this class
    val composeTestRule = createComposeRule()
    // A fresh TodoViewModel is created before each test
    private lateinit var _viewModel: TodoViewModel

    @Before
    fun setup(){
        _viewModel = TodoViewModel()
        //set the content to display in the test -- same as MainActivity does
        composeTestRule.setContent{
            TodoScreen(viewModel = _viewModel)
        }
    }
    @Test
    fun addTask_userTypesAndClicksAdd_taskAppearsInList(){
        // Arrange - the setup() already does this.  Creates an empty viewModel launch the TodoScreen
        // Verify the task does not exist yet
        composeTestRule.onNodeWithText("Buy Groceries").assertDoesNotExist()
        // Act - type a task and click to add it
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("Buy Groceries")

        //Act - click the add button
        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()

        //Assert - verify the task now appears on the screen
        composeTestRule
            .onNodeWithText("Buy Groceries")
            .assertIsDisplayed()
    }

    @Test
    fun addTask_withBlankInput_taskDoesNotAppearInList(){
        //Arrange - the setup()
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput(" ")
        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()

        //Assert the task counter should still be 0
        composeTestRule
            .onNodeWithText("Tasks: 0")
            .assertIsDisplayed()
    }
    // Challenge: Updates the task counter when a task is added
    //1. Check if the task counter is 0
    //2. Add a task
    //3. Click the add button
    //4. Check if the task counter is 1
    @Test
    fun addTask_withUserClick_addToTaskCounter(){
        //Arrange
        composeTestRule
            .onNodeWithText("Tasks: 0")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("Do Homework")
        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()
        composeTestRule
            .onNodeWithText("Tasks: 1")
            .assertIsDisplayed()
    }
    // Challenge remove task and verify it is removed
    //1. Add task
    //2. Click the remove button
    //3. Verify the task is removed
    @Test
    fun removeTask_withUserClick_removeTaskFromCounter(){
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("Wash Car")
        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()
        composeTestRule
            .onNodeWithTag("removeButton")
            .performClick()
        composeTestRule
            .onNodeWithText("Wash Car")
            //.assertIsDisplayed()
            .assertDoesNotExist()
    }


}