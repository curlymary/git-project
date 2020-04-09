package com.example.git_project

interface ListPrinterView {
    /**
     * Outputs all members of the List
     * form of output "<Name> <DiscountPrice>Р"
     */
    fun printList(productList: List<Product>){}
}