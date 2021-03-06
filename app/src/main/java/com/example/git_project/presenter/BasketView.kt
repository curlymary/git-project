package com.example.git_project.presenter

import com.example.git_project.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BasketView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketProducts(list: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProduct(position : Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductDetail(product : Product)
}