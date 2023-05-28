package com.ompava.misnotas

import androidx.room.Dao
import androidx.room.Query

@Dao // Anotación que indica que esta interfaz es un DAO (Objeto de Acceso a Datos)
interface TaskDao {
    @Query("SELECT * FROM task_entity")  // Consulta para obtener todas las tareas de la tabla "task_entity"
    fun getAllTasks(): MutableList<TaskEntity> // Método para obtener todas las tareas y devolver una lista mutable de entidades de tarea
}