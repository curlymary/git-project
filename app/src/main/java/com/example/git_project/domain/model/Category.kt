package com.example.git_project.domain.model

class Category(
    private val id : Long,
    private val name : String)
{
    fun getId() : Long{
        return  this.id
    }

    fun getName () : String{
        return this.name
    }

}