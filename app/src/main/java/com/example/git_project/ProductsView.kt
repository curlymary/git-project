package com.example.git_project
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy :: class)
interface ProductsView : MvpView {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun print(price: Double){}

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun showErrorForFirstName(visible: Boolean){}

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun showErrorForMiddleName(visible: Boolean){}

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun showErrorForLastName(visible: Boolean){}

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun showErrorForPhoneNumber(visible: Boolean){}
}