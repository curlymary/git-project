package com.example.git_project.presenter

import com.example.git_project.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProductToBasket(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductDetail(product: Product)
}