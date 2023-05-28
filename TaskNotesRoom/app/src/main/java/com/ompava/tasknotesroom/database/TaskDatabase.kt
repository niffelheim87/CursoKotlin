package com.ompava.tasknotesroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ompava.tasknotesroom.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao // Método abstracto para obtener el objeto de acceso a datos (DAO) para las tareas

    companion object { // Patrón Singleton para la base de datos
        private var instance: TaskDao? = null

        fun getInstance(context: Context): TaskDao {
            // Si la instancia ya existe, se devuelve. De lo contrario, se crea una nueva instancia y se guarda en la variable "instance"
            return instance ?: Room.databaseBuilder(context, TasksDatabase::class.java, "tasks-db")
                .build().taskDao().also { instance = it }
        }
    }
}