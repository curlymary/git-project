package com.example.git_project.presenter

import com.example.git_project.domain.ViewedProductDao
import com.example.git_project.domain.model.Product
import moxy.MvpPresenter

class DetailedPresenter(private val viewedProductDao: ViewedProductDao) :
    MvpPresenter<DetailedView>() {

    fun onProductShow(product: Product) {
        viewedProductDao.addProduct(product.getId().toLong())
    }
}