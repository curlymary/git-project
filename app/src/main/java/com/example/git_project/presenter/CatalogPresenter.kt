package com.example.git_project.presenter

import com.example.git_project.ui.CatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<CatalogView>() {
    var list = mutableListOf(
        "Телевизоры",
        "Планшеты",
        "Телефоны",
        "Часы",
        "Телевизоры",
        "Планшеты",
        "Телефоны",
        "Часы",
        "Компьютеры",
        "Ноутбуки")

    fun setData(){
        viewState.setCategories(list)
    }

    fun removeItem(category : String){
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }
}

