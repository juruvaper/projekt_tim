package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel =  ViewModelProvider(owner = this)[ToDoViewModel::class.java]
        //Poprzez wywłanie funkcji ViewModelprovider upewniamy się, że podawany model danych
        //tj. ToDoViewModel będzie powiązany semantycznie z wywołaniem funkcji MainActivity -
        //owner: this
        setContent {
              TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoListPage(toDoViewModel)
                    //Podajemy do funkcji model, który odpowiedzialny jest za przechowywanie
                    //Dodawanych przez nas danych
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {

    }
}