package com.ompava.tasknotesroom.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ompava.tasknotesroom.database.entities.TaskEntity

@Dao
interface TaskDao : MyDao {
    @Query("SELECT * FROM task_entity")
    override fun getAllTasks(): MutableList<TaskEntity> // Obtiene todas las tareas de la tabla "task_entity"

    @Insert
    override fun addTask(taskEntity: TaskEntity): Long // Inserta una nueva tarea en la tabla "task_entity" y devuelve el ID de la nueva tarea

    @Query("SELECT * FROM task_entity WHERE id LIKE :id")
    override fun getTaskById(id: Long): TaskEntity // Obtiene una tarea de la tabla "task_entity" por su ID

    @Update
    override fun updateTask(taskEntity: TaskEntity): Int // Actualiza una tarea existente en la tabla "task_entity" y devuelve el número de filas afectadas

    @Delete
    override fun deleteTask(taskEntity: TaskEntity): Int // Elimina una tarea de la tabla "task_entity" y devuelve el número de filas afectadas
}
