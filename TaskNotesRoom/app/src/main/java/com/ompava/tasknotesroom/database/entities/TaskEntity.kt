package com.ompava.tasknotesroom.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity") // Define que esta clase representa una entidad de la base de datos con nombre "task_entity"
data class TaskEntity (
    @PrimaryKey(autoGenerate = true) // Define que el campo "id" es la clave primaria y se genera automáticamente
    var id: Int = 0, // Campo que representa el ID de la tarea
    var name: String = "", // Campo que representa el nombre de la tarea
    var isDone: Boolean = false // Campo que indica si la tarea está completada o no
)
