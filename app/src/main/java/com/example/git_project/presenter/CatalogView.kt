package com.example.git_project.presenter

import com.example.git_project.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position : Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductIds(productIds: List<Long>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setViewedProducts(productList: List<Product>)
}