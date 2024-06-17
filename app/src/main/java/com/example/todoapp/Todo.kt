package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var tytul: String,
    var utworzono: Date

)

fun testTodo() : List<Todo>{
    return listOf<Todo>(
        Todo(1, "Pierwsze do zrobienia", Date.from(Instant.now())),
        Todo(2, "Drugie do zrobienia", Date.from(Instant.now())),
        Todo(3, "Trzecie do zrobienia", Date.from(Instant.now())),
        Todo(1, "Pierwsze do zrobienia", Date.from(Instant.now())),
        Todo(2, "Drugie do zrobienia", Date.from(Instant.now())),
        Todo(3, "Trzecie do zrobienia", Date.from(Instant.now())),
        Todo(1, "Pierwsze do zrobienia", Date.from(Instant.now())),
        Todo(2, "Drugie do zrobienia", Date.from(Instant.now())),
        Todo(3, "Trzecie do zrobienia", Date.from(Instant.now()))

    );
}