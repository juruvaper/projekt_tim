package com.example.todoapp

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text
import java.nio.file.WatchEvent
import java.util.Locale

@Composable
fun ToDoListPage(viewModel: ToDoViewModel){
    val todoList by viewModel.listaRzeczy.observeAsState()

    var noweZadanie by remember {
        mutableStateOf("")

        //Potrzebny był import, straciłem tu dużo czasu. Przeoczenie.

        //Komponent ten pamieta stany pomiedzy tzw. rekompozycjami.
    }

    //Wywolujemy tutaj funkcje, ktora zwraca nam w returnie elementy
    //Z odpowiedniej klasy (T_odo)


    //Definiuje kolumne, do ktorej bede wstawial swoje dane (orientacja wertykalna). Nastepnie
    //Do tej kolumny wstawie tzw. Lazy column - ktora mozna swobodnie przewijac.
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    )

    {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                modifier = Modifier.weight(1f)
                    .padding(10.dp),

                value = noweZadanie, onValueChange = {
                noweZadanie = it
            } )
            Button(onClick = {
                viewModel.dodajRzecz(noweZadanie)
                noweZadanie = ""} )
            {
                Text(text="+")
            }

            //Wywolanie funkcji na tzw. Onclicku (a wlasciwie onchannge). Nasza zmienna
            //Nowe zadanie ma przypisywana nowa wartosc za kazdym razem gdy ta
            //ulega zmianie w polu tekstowym input
        }
todoList?.let {
    //Po co używam let? Aby uniknąć null exception w przypadku, gdy todoList jest puste. it poniżej
    //działa na zasadzie jak this w javascript - odnosi się do obiektu, na którym została
    //wywołana dana metoda

    LazyColumn (
        content = {
            itemsIndexed(it) { index: Int, item: Todo ->
                Zadanie(zadanie = item, onDelete = {
                    viewModel.usunRzeczid(item.id)
                })
            }

            //Podajemy parametr z funkcji obiegowej do naszej funkcji odpowiedzialnej
            //Za stworzenie pojedynczego elementu - zadanie. Funkcja 'Zadanie' jest odpowiedzialna
            //Za sparsowanie wyniku do postaci zwyklego tekst

            //Uzywam tutaj instrukcji podobnej do map z JS - itemsIndexed.
            //Bierze ona indeks aktualnie przetwarzanej pozycji, zas na
            //Kazdym kroku mamy dostep do jej zawartosci - itemb
        }
    )
}?: Text(
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center,
    text = "Nie dodano żadnych rzeczy",
    fontSize = 16.sp
)





    }

}

@Composable
//
fun Zadanie(zadanie: Todo, onDelete : ()-> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(text=zadanie.tytul,
            fontSize = 25.sp,
            color = Color.White
            )
            Text(text= SimpleDateFormat("HH:mm, dd.MM.yyyy", Locale("pl", "PL")).format(zadanie.utworzono),
                color=Color.LightGray,
                fontSize = 15.sp)



        }

        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                contentDescription = "Delete",
                tint = Color.Blue)
        }
    }
}

//Nalezy pamietac o deklaracji typow zaraz po zdefiniowaniu parametru - parametr : typ