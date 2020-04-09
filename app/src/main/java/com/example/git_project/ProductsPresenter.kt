package com.example.git_project

class ProductsPresenter(val view1 : PricePrinterView, val view2 : ListPrinterView) {

    //инициализировали содержимое списка
    private val book1 = Product(price = 150.0, salePercent = 15, name = "Гордость и предубеждение")
    private val book2 = Product(price = 173.0, salePercent = 10, name = "Приключения Робинзона Крузо")
    private val book3 = Product(price = 90.0, salePercent = 7, name = "Анжелика и Король")
    //инициализировали список
    private val bookList = listOf(book1, book2, book3)

    //создали корзину
    val storeBasket = Basket(bookList)

    fun totalPricePrint(){
        print("Общая сумма корзины: ")
        view1.print(storeBasket.calculateSumPrice())
    }

    fun productListPrint(){
        //вывели список покупок
        view2.printList(bookList)
    }
}