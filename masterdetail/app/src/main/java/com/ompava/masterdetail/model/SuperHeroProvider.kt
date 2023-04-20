package com.ompava.masterdetail.model

class SuperHeroProvider {
    companion object{
        val superHeroList = listOf<SuperHero>(
            SuperHero(
                1,
                "KotlinMan",
                "Jetbrains",
                "Omar",
                "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"
            ),
            SuperHero(
                2,
                "Spiderman",
                "Marvel",
                "Peter Parker",
                "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"
            ),
            SuperHero(
                3,
                "Daredevil",
                "Marvel",
                "Matthew Michael Murdock",
                "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"
            ),
            SuperHero(
                4,
                "Wolverine",
                "Marvel",
                "James Howlett",
                "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"
            ),
            SuperHero(
                5,
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"
            ),
            SuperHero(
                6,
                "Thor",
                "Marvel",
                "Thor Odinson",
                "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"
            ),
            SuperHero(
                7,
                "Flash",
                "DC",
                "Jay Garrick",
                "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"
            ),
            SuperHero(
                8,
                "Green Lantern",
                "DC",
                "Alan Scott",
                "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"
            ),
            SuperHero(
                9,
                "Wonder Woman",
                "DC",
                "Princess Diana",
                "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"
            )
        )
    }
    fun getSuperHeroById(mId: Int?): SuperHero {
        val superhero = getSuperheros().filter { superHero ->
            superHero.id==mId
        }

        return superhero[0]
    }

    fun getSuperheros(): List<SuperHero>{
        return superHeroList
    }

    fun getFirstID():Int{
        return getSuperheros()[0].id
    }

}