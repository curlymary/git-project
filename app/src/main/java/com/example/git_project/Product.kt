package com.example.git_project

class Product  (
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0,
    private val name : String
) : ProductsView {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun getName () : String{
        return this.name
    }

    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)

    override fun print(price: Double){
        val finalPrice : String
        if(price >= 0){
            finalPrice  = if (Math.floor(price) == (price)) Math.floor(price).toInt().toString() else String.format("%.2f", price)
            print(finalPrice +"P")
        }
        else {
            print("Цена не может принимать отрицательное значение")
        }
    }
}