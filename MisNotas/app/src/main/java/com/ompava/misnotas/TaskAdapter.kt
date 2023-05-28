package com.ompava.misnotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(
    val tasks: List<TaskEntity>, // Lista de tareas a mostrar en el RecyclerView
    val checkTask: (TaskEntity) -> Unit, // Función de retroalimentación para marcar una tarea como completada
    val deleteTask: (TaskEntity) -> Unit // Función de retroalimentación para eliminar una tarea
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTask = view.findViewById<TextView>(R.id.tvTask) // Referencia al TextView para mostrar el nombre de la tarea
        val cbIsDone = view.findViewById<CheckBox>(R.id.cbIsDone) // Referencia al CheckBox para marcar si la tarea está completada o no

        fun bind(task: TaskEntity, checkTask: (TaskEntity) -> Unit, deleteTask: (TaskEntity) -> Unit) {
            tvTask.text = task.name // Asigna el nombre de la tarea al TextView
            cbIsDone.isChecked = task.isDone // Marca el CheckBox si la tarea está completada
            cbIsDone.setOnClickListener { checkTask(task) } // Asigna la función de retroalimentación para marcar la tarea como completada al hacer clic en el CheckBox
            itemView.setOnClickListener { deleteTask(task) } // Asigna la función de retroalimentación para eliminar la tarea al hacer clic en el elemento del RecyclerView

        }
    }
}
