package com.ompava.recycler_viewmodel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ompava.recycler_viewmodel.adapter.DogAdapter
import com.ompava.recycler_viewmodel.databinding.ActivityMainBinding
import com.ompava.recycler_viewmodel.viewmodel.DogViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: DogViewModel
    private lateinit var adapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflamos la vista y la establecemos como contenido de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Agregamos un listener para el cuadro de búsqueda
        binding.svDogs.setOnQueryTextListener(this)
        // Inicializamos el RecyclerView

        initRecyclerView()

        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        viewModel.itemsLiveData.observe(this, { items ->
            adapter = DogAdapter(items)
            recyclerView.adapter = adapter
        })

        viewModel.searchByName("akita")

    }

    private fun initRecyclerView() {
        recyclerView = binding.rvDogs
        // Creamos un adaptador de perros con la lista de imágenes vacía
        adapter = DogAdapter(emptyList())
        // Establecemos el diseño de lista lineal para el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Establecemos el adaptador para el RecyclerView
        recyclerView.adapter = adapter
    }

    // Función para ejecutar una búsqueda cuando el usuario envía un formulario de búsqueda
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.searchByName(query.lowercase())

        }
        return true
    }

    // Función para ejecutar una búsqueda en tiempo real mientras el usuario escribe en el campo de búsqueda
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        // Obtener el servicio de InputMethodManager
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        // Ocultar el teclado pasando el token de la ventana actual y una bandera de cierre suave
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}