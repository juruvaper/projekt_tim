package com.example.todoapp.bazaDanych

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.todoapp.Todo


@Database(entities = [Todo::class], version=1)
@TypeConverters(KonwerterDat::class)
abstract class ToDoDATABASE: RoomDatabase() {

    companion object {
        const val NAME = "Todo_Db"
    }

    abstract fun getTodoDao() : TodoDAO
}