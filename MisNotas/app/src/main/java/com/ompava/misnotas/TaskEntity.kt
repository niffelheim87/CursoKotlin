package com.ompava.misnotas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity") // Anotación que indica que esta clase es una entidad y se almacenará en una tabla llamada "task_entity"
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)  // Campo que representa la clave primaria de la entidad, con autoincremento
    var id:Int = 0,
    var name:String = "", // Campo que representa el nombre de la tarea
    var isDone:Boolean = false // Campo que indica si la tarea está completada o no
)