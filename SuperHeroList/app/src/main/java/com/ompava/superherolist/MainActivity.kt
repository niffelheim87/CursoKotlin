package com.ompava.superherolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var test: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test = findViewById<TextView>(R.id.test)

        // crea un objeto Superhero llamado batman
        val batman:Superhero = Superhero("Batman", "DC", "Bruce Wayne", "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg")

        val batmanRealName = batman.realName //Recuperamos RealName "Bruce Wayne"

        batman.realName = "Soy Batman" //Cambiamos "Brece Wayne" por "Soy Batman"

        batman.toString() //Devolverá todos los atributos con su valortest.setText(batman.superhero)

        // Copiamos el objeto y lo modificamos
        val superBatman:Superhero = batman.copy(superhero = "SuperBatman")

        // Parametros de recuperacion
        batman.component1() //Batman
        batman.component2() //DC
        batman.component3() //Bruce Wayne
        batman.component4() //https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg

        // Almacena todos los parametros de golpe
        val (superheroFav, publisherFav, realNameFav, photoFav) = batman
        superheroFav //Batman
        publisherFav //DC
        realNameFav //Bruce Wayne
        photoFav //https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg
        test.setText(batman.toString())

        // crea una lista mutable y añade los superheroes
        var superheros:MutableList<Superhero> = mutableListOf()
        superheros.add(Superhero("Spiderman", "Marvel", "Peter Parker", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        superheros.add(Superhero("Daredevil", "Marvel", "Matthew Michael Murdock", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"))
        superheros.add(Superhero("Wolverine", "Marvel", "James Howlett", "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"))
        superheros.add(Superhero("Batman", "DC", "Bruce Wayne", "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"))
        superheros.add(Superhero("Thor", "Marvel", "Thor Odinson", "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"))
        superheros.add(Superhero("Flash", "DC", "Jay Garrick", "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"))
        superheros.add(Superhero("Green Lantern", "DC", "Alan Scott", "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"))
        superheros.add(Superhero("Wonder Woman", "DC", "Princess Diana", "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"))
    }
}