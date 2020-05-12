package com.example.git_project.presenter

import com.example.git_project.domain.model.Product
import moxy.MvpPresenter

class BasketPresenter : MvpPresenter<BasketView>() {
    //инициализировали содержимое списка
    private val book1 = Product(
        id = 1,
        price = 150.0,
        salePercent = 15,
        name = "Гордость и предубеждение",
        imgUrl = ""
    )
    private val book2 = Product(
        id = 2,
        price = 173.0,
        salePercent = 10,
        name = "Приключения Робинзона Крузо",
        imgUrl = ""
    )
    private val book3 = Product(
        id = 3,
        price = 90.0,
        salePercent = 7,
        name = "Анжелика и Король",
        imgUrl = ""
    )
    private val book4 = Product(
        id = 4,
        price = 79.0,
        salePercent = 10,
        name = "Унесенные ветром",
        imgUrl = ""
    )
    private val book5 = Product(
        id = 5,
        price = 200.0,
        salePercent = 5,
        name = "Колобок",
        imgUrl = ""
    )
    private val book6 = Product(
        id = 6,
        price = 170.0,
        salePercent = 15,
        name = "Капитан Немо",
        imgUrl = ""
    )
    private val book7 = Product(
        id = 7,
        price = 150.0,
        salePercent = 10,
        name = "Денискины рассказы",
        imgUrl = ""
    )
    private val book8 = Product(
        id = 8,
        price = 60.0,
        salePercent = 11,
        name = "Война и мир",
        imgUrl = ""
    )
    private val book9 = Product(
        id = 9,
        price = 190.0,
        salePercent = 4,
        name = "Вино из одуванчиков",
        imgUrl = ""
    )
    private val book10 = Product(
        id = 10,
        price = 90.0,
        salePercent = 6,
        name = "100 рецептов",
        imgUrl = ""
    )

    private val book11 = Product(
        id = 11,
        price = 125.0,
        salePercent = 15,
        name = "Просто добавь воды",
        imgUrl = ""
    )

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