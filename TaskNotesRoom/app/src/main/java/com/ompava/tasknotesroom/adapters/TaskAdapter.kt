package com.ompava.tasknotesroom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ompava.tasknotesroom.R
import com.ompava.tasknotesroom.database.entities.TaskEntity

class TasksAdapter(
    val tasks: List<TaskEntity>, // Lista de tareas que se mostrarán en el adaptador
    val checkTask: (TaskEntity) -> Unit, // Función de devolución de llamada para manejar el evento de marcar una tarea como completada
    val deleteTask: (TaskEntity) -> Unit // Función de devolución de llamada para manejar el evento de eliminación de una tarea
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    // Crea y devuelve una instancia del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    // Vincula los datos de una tarea con los elementos visuales del ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
    }

    // Devuelve la cantidad de elementos en la lista de tareas
    override fun getItemCount() = tasks.size

    // ViewHolder que representa un elemento visual de una tarea en el RecyclerView
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTask = view.findViewById<TextView>(R.id.tvTask) // Referencia al TextView que muestra el nombre de la tarea
        val cbIsDone = view.findViewById<CheckBox>(R.id.cbIsDone) // Referencia al CheckBox que indica si la tarea está completada

        // Vincula los datos de una tarea con los elementos visuales del ViewHolder
        fun bind(task: TaskEntity, checkTask: (TaskEntity) -> Unit, deleteTask: (TaskEntity) -> Unit) {
            tvTask.text = task.name // Establece el nombre de la tarea en el TextView
            cbIsDone.isChecked = task.isDone // Establece el estado de completado de la tarea en el CheckBox
            cbIsDone.setOnClickListener { checkTask(task) } // Configura la acción de hacer clic en el CheckBox para marcar una tarea como completada
            itemView.setOnClickListener { deleteTask(task) } // Configura la acción de hacer clic en el elemento visual para eliminar una tarea
        }
    }
}
