package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.bazaDanych.TodoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel: ViewModel() {

    val todoDao = MainApplication.toDoDATABASE.getTodoDao()
    val listaRzeczy : LiveData<List<Todo>> = todoDao.wyswietlTodo()

    //Zmienna obslugujaca dane nadchodzace w czasie rzeczywistym


    fun usunRzeczid(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.usunTodo(id)}

    }

    fun dodajRzecz(tytul : String){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.dodajTodo(Todo(tytul=tytul, utworzono = Date.from(Instant.now())))}

    }
}