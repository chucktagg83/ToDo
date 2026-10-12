package com.example.todo

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {
    // The system under text - recreated fresh before each test
    private lateinit var _viewModel: TodoViewModel
    // Set up the ViewModel before each test
    @Before
    fun setup() {
        _viewModel = TodoViewModel()
    }

    @Test
    fun addTask_withValidTitle_appearsInList(){
        // Arrange - the setup() already does this.  Creates an empty viewModel

        // Act - call the function being tested
        _viewModel.addTask("Buy Groceries")

        // Assert - check the result
        assertEquals(1, _viewModel.getTaskCount())
        assertTrue(_viewModel.containsTask("Buy Groceries"))
    }

    // test a blank title should not be added
    @Test
    fun addTask_withBlankTitle_doesNotAdd(){

        // Act - call the function being tested
        _viewModel.addTask("")

        // Assert - check the result
        assertEquals(0, _viewModel.getTaskCount())
    }
    @Test
    fun addTask_withEmptyString_doesNotAdd(){
        _viewModel.addTask("")

        assertEquals(0,_viewModel.getTaskCount())
    }

    // Challenge: Leading and trailing spaces should be trimmed
    @Test
    fun addTask_withLeadingAndTrailingSpaces_trimmed(){
        _viewModel.addTask("Buy Groceries")
        assertTrue(_viewModel.tasks[0].title == "Buy Groceries")
    }
    @Test
    fun removeTask_withValidId_removesTask(){
        //Arrange -- add task first so we have something to remove
        _viewModel.addTask("Buy Groceries")
        val taskId = _viewModel.tasks[0].id

        //Act
        _viewModel.removeTask(taskId)

        //Assert - check the result
        assertEquals(0, _viewModel.getTaskCount())
        assertFalse(_viewModel.containsTask("Buy Groceries"))
    }
    @Test
    fun removeTask_removesCorrectTask_whenMultipleExist(){
        //when there are multiple tasks, remove the second one
        //Arrange = add multiple tasks
        _viewModel.addTask("Task A")
        _viewModel.addTask("Task B")
        _viewModel.addTask("Task C")
        //Act - call the function being tested
        val idOfTaskToRemove = _viewModel.tasks[1].id
        _viewModel.removeTask(idOfTaskToRemove)
        //Assert - check the result
        assertEquals(2, _viewModel.getTaskCount())
        assertFalse(_viewModel.containsTask("Task B"))
    }
}