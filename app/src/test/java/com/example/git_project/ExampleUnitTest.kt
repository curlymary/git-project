package com.example.git_project

import org.junit.Test
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val presenter = Presenter()

    @Test
    fun example() {
        val iphoneCase = Product(price = 123.5, salePercent = 15, name = "Case1")
        val pricePrinter: PricePrinter = iphoneCase

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)
    }

    @Test
    fun basketTest() {
        //инициализировали содержимое списка
        val book1 = Product(price = 150.0, salePercent = 15, name = "Гордость и предубеждение")
        val book2 = Product(price = 173.0, salePercent = 10, name = "Приключения Робинзона Крузо")
        val book3 = Product(price = 90.0, salePercent = 7, name = "Анжелика и Король")
        //инициализировали список
        val bookList = listOf(book1, book2, book3)
        //создали корзину
        val storeBasket = Basket(bookList)
        //вывели сумму корзины
        val pricePrinter : PricePrinter = storeBasket
        print("Общая сумма корзины: ")
        pricePrinter.print(storeBasket.calculateSumPrice())

        //создали интерфейс
        val listPrinter : ListPrinter = storeBasket
        //вывели список покупок
        listPrinter.printList(bookList)
    }

    @Test
    fun presenterTest() {
        presenter.productListPrint()
        presenter.totalPricePrint()
    }
}

class Presenter{
    //инициализировали содержимое списка
    private val book1 = Product(price = 150.0, salePercent = 15, name = "Гордость и предубеждение")
    private val book2 = Product(price = 173.0, salePercent = 10, name = "Приключения Робинзона Крузо")
    private val book3 = Product(price = 90.0, salePercent = 7, name = "Анжелика и Король")
    //инициализировали список
    private val bookList = listOf(book1, book2, book3)

    //создали корзину
    val storeBasket = Basket(bookList)
    //иницииализировали PricePrinter для корзины
    private val pricePrinter : PricePrinter = storeBasket
    //создали интерфейс
    private val listPrinter : ListPrinter = storeBasket

    fun totalPricePrint(){
        print("Общая сумма корзины: ")
        pricePrinter.print(storeBasket.calculateSumPrice())
    }

    fun productListPrint(){
        //вывели список покупок
        listPrinter.printList(bookList)
    }
}

interface ListPrinter{
    /**
     * Outputs all members of the List
     * form of output "<Name> <DiscountPrice>Р"
     */
    fun printList(productList: List<Product>){}
}

class Basket (private val productList: List<Product>) : ListPrinter, PricePrinter {
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

    override fun printList(productList: List<Product>){
        productList.forEach({product ->
            print(product.getName() + " : ")
            product.print(product.calcDiscountPrice())
            print("\n")
        })
    }
}

interface PricePrinter {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class Product  (
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0,
    private val name : String
) : PricePrinter {
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
