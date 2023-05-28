package com.ompava.tasknotesroom.database

import com.ompava.tasknotesroom.database.entities.TaskEntity

interface MyDao {
    fun getAllTasks(): MutableList<TaskEntity> // Método para obtener todas las tareas, devuelve una lista mutable de TaskEntity

    fun addTask(taskEntity: TaskEntity): Long // Método para agregar una nueva tarea, devuelve el ID de la nueva tarea (Long)

    fun getTaskById(id: Long): TaskEntity // Método para obtener una tarea por su ID, devuelve la TaskEntity correspondiente al ID

    fun updateTask(taskEntity: TaskEntity): Int // Método para actualizar una tarea existente, devuelve el número de filas afectadas (Int)

    fun deleteTask(taskEntity: TaskEntity): Int // Método para eliminar una tarea, devuelve el número de filas afectadas (Int)
}