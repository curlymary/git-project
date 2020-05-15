package com.example.git_project.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product (
    /**
     * Must be positive
     */
    private val id : Long,
    private val categoryId : Long,
    private val price: Double,
    private val salePercent: Int = 0,
    private val name : String,
    private val imgUrl : String

) : Parcelable {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun getId() : Long{
        return  this.id
    }

    fun getCategoryId() : Long{
        return this.categoryId
    }

    fun getName () : String{
        return this.name
    }

    fun getSalePercent() : Int{
        return this.salePercent
    }

    fun getPrice() : Double{
        return this.price
    }

    fun getImgUrl() : String{
        return this.imgUrl
    }

    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)

    fun makeFinalPrice(price : Double) : String{
        val finalPrice : String
        finalPrice  = if (Math.floor(price) == (price)) Math.floor(price).toInt().toString() else String.format("%.2f", price)
        return finalPrice + "P"
    }
}