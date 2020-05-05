package com.example.git_project.presenter

import com.example.git_project.Product
import com.example.git_project.ui.BasketView
import moxy.MvpPresenter

class BasketPresenter : MvpPresenter<BasketView>() {
    //инициализировали содержимое списка
    private val book1 = Product(price = 150.0, salePercent = 15, name = "Гордость и предубеждение")
    private val book2 = Product(price = 173.0, salePercent = 10, name = "Приключения Робинзона Крузо")
    private val book3 = Product(price = 90.0, salePercent = 7, name = "Анжелика и Король")
    private val book4 = Product(price = 79.0, salePercent = 10, name = "Унесенные ветром")
    private val book5 = Product(price = 200.0, salePercent = 5, name = "Колобок")
    private val book6 = Product(price = 170.0, salePercent = 15, name = "Капитан Немо")
    private val book7 = Product(price = 150.0, salePercent = 10, name = "Денискины рассказы")
    private val book8 = Product(price = 60.0, salePercent = 11, name = "Война и мир")
    private val book9 = Product(price = 190.0, salePercent = 4, name = "Вино из одуванчиков")
    private val book10 = Product(price = 90.0, salePercent = 6, name = "100 рецептов")

    private val book11 = Product(price = 125.0, salePercent = 15, name = "Просто добавь воды")

    var list = mutableListOf(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10)



    fun setData(){
        viewState.setProducts(list)
    }

    fun removeItem(product : Product){
        val position = list.indexOf(product)
        list.remove(product)
        viewState.removeItem(position)
    }

    fun addItem(){
        val position = list.indexOf(list.last()) + 1
        list.add(book11)
        viewState.addItem(position)
    }
}