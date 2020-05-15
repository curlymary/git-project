package com.example.git_project.presenter

import com.example.git_project.domain.BasketDao
import com.example.git_project.domain.model.Category
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatalogPresenter (private val basketDao: BasketDao): MvpPresenter<CatalogView>() {
    //инициализировали содержимое списка
    private val category1 = Category(id = 1, name = "Понедельник")
    private val category2 = Category(id = 2, name = "Вторник")
    private val category3 = Category(id = 3, name = "Среда")
    private val category4 = Category(id = 4, name = "Четверг")
    private val category5 = Category(id = 5, name = "Пятница")
    private val category6 = Category(id = 6, name = "Суббота")
    private val category7 = Category(id = 7, name = "Воскресенье")

    var list = mutableListOf(category1, category2, category3, category4, category5, category6, category7)

    fun setData(){
        viewState.setCategories(list)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        val productIds = basketDao.getAllProducts()
        viewState.showProductIds(productIds)
    }
}