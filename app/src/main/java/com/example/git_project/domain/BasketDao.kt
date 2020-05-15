package com.example.git_project.domain

interface BasketDao {

    /**
     * save this product id as viewed
     * */
    fun addProduct(productId: Long)

    /**
     * get all viewed product ids
     * might be empty
     * */
    fun getAllProducts(): List<Long>

    fun deleteProduct(productId: Long)
}
