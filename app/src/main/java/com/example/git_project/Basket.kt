package com.example.git_project

import com.example.git_project.ui.ProductsView

class Basket (private val productList: List<Product>) :
    ProductsView {
    /**
     * @return total price of all products in list with applied discount
     */
    fun calculateSumPrice() : Double {
        var totalSum = 0.0
        //прошли по списку продуктов, иттеративно нарастили стоимость
        for (product in productList){
            totalSum = totalSum + product.calcDiscountPrice()
        }
        return totalSum
    }

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

    /*override fun printList(productList: List<Product>){
        productList.forEach({product ->
            print(product.getName() + " : ")
            product.print(product.calcDiscountPrice())
            print("\n")
        })
    }*/
}