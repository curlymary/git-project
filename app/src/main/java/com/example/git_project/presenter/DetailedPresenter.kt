package com.example.git_project.presenter

import com.example.git_project.domain.BasketDao
import com.example.git_project.domain.model.Product
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailedPresenter(private val basketDao: BasketDao) : MvpPresenter<DetailedView>() {

    fun addItemToBasket(product : Product){
        basketDao.addProduct(product.getId())
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: DetailedView?) {
        super.attachView(view)
        val productIds = basketDao.getAllProducts()

        viewState.showButtonAddToBasket(productIds)
    }
}