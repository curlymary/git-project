package com.example.git_project.ui

import com.example.git_project.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BasketView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position : Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addItem(position : Int)
}