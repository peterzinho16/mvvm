package com.itsight.countries

import java.util.*

fun main(args: Array<String>){
    println("Hello Show")
    println(ThatsWhyWePlay(22).isOlderThanMe)
    println(ThatsWhyWePlay(26).isOlderThanMe)
    println(ThatsWhyWePlay(21).isOlderThanMe)
    println(ThatsWhyWePlay().age)

}
//Se ejecuta primero
class ThatsWhyWePlay(var age: Int) {
    var isOlderThanMe = false
    var myAge = 25

    constructor():this(0){
        this.isOlderThanMe = true
        this.age = 33
    }
    //Cuando el objeto es instanciando mediante el primary constructor, los init se ejecutan después del constructor
    //pero cuando el objeto es instanciando mediante un secundary constructor, los init se ejecutan primero y en último
    //lugar se ejecuta el body del secundary constructor

    init{
        this.isOlderThanMe = myAge<age
    }

    init{
        age = 2
    }

    init{
        age *= 2
    }

    init{
        age *= 2
        this.isOlderThanMe = false
    }
}
