package com.example.todoapp.bazaDanych

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM TODO")
    fun wyswietlTodo() : LiveData<List<Todo>>

    @Insert
    fun dodajTodo(todo : Todo)

    @Query("Delete FROM Todo where id = :id")
    fun usunTodo(id: Int)
}