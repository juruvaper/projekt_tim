package com.example.todoapp

import java.time.Instant
import java.util.Date

object MagazynInformacji {
    private val memoryTodo = mutableListOf<Todo>()


    fun pokazWszystkieRzeczy() : List<Todo>{
        return memoryTodo
    }

    fun usunRzecz(id : Int){
        memoryTodo.removeIf{
            it.id==id;
        }
    }

    fun dodajRzecz(_tytul: String){
        memoryTodo.add(Todo(System.currentTimeMillis().toInt(), _tytul, Date.from(Instant.now())))
    }
}
