package com.ompava.doglist_retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ompava.doglist_retrofit2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    // Creamos variables para almacenar la vista, el adaptador y las imágenes de los perros
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // Inflamos la vista y la establecemos como contenido de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Agregamos un listener para el cuadro de búsqueda
        binding.svDogs.setOnQueryTextListener(this)
        // Inicializamos el RecyclerView
        initRecyclerView()

    }

    private fun initRecyclerView() {
        // Creamos un adaptador de perros con la lista de imágenes vacía
        adapter = DogAdapter(dogImages)
        // Establecemos el diseño de lista lineal para el RecyclerView
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        // Establecemos el adaptador para el RecyclerView
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        // Creamos un objeto Retrofit para interactuar con la API de perros
        return Retrofit.Builder()
            // Establecemos la URL base para la API
            .baseUrl("https://dog.ceo/api/breed/")
            // Usamos Gson para convertir la respuesta JSON en objetos Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun searchByName(query:String){
        // Iniciamos una tarea en segundo plano para obtener las imágenes de los perros de la API
        CoroutineScope(Dispatchers.IO).launch {
            // Obtenemos las imágenes de los perros que coinciden con la raza proporcionada
            val call : Response<DogResponse> = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            // Obtenemos el cuerpo de la respuesta y lo almacenamos en un objeto DogResponse
            val puppies:DogResponse? = call.body()
            // Actualizamos la interfaz de usuario con las imágenes de los perros obtenidos
            runOnUiThread{
                if (call.isSuccessful){
                    // Obtenemos la lista de imágenes de perros de la respuesta o una lista vacía si no hay imágenes
                    val images: List<String> = puppies?.images ?: emptyList()
                    // Imprimimos en el log el número de imágenes obtenidas
                    Log.d("DOG_LIST", "Images received: ${images.size}")
                    // Limpiamos la lista de imágenes de perros y agregamos las nuevas imágenes
                    dogImages.clear()
                    dogImages.addAll(images)
                    // Notificamos al adaptador que los datos han cambiado para actualizar la vista
                    adapter.notifyDataSetChanged()

                } else {
                    // Mostramos un mensaje de error si la llamada no fue exitosa
                    showError()
                }
                hideKeyboard()
            }

        }
    }

    // Función para mostrar un mensaje de error en caso de que la búsqueda no sea exitosa
    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    // Función para ejecutar una búsqueda cuando el usuario envía un formulario de búsqueda
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())

        }
        return true
    }

    // Función para ejecutar una búsqueda en tiempo real mientras el usuario escribe en el campo de búsqueda
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    // Función para ocultar el teclado suavemente
    private fun hideKeyboard() {
        // Obtener el servicio de InputMethodManager
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        // Ocultar el teclado pasando el token de la ventana actual y una bandera de cierre suave
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}