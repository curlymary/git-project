package com.example.git_project.presenter

import com.example.git_project.domain.BasketDao
import com.example.git_project.domain.model.CreateOrderModel
import com.example.git_project.domain.model.Product
import moxy.MvpPresenter

class CheckoutPresenter(private val basketDao: BasketDao) : MvpPresenter<CheckoutView> () {

    //инициализировали содержимое списка
    private val product1 = Product(id = 1, categoryId = 1, price = 150.0, salePercent = 15, name = "Скрэмбл c тофу", imgUrl = "")
    private val product2 = Product(id = 2,  categoryId = 1, price = 173.0, salePercent = 10, name = "Салат с горбушей", imgUrl = "")
    private val product3 = Product(id = 3, categoryId = 1, price = 79.0, salePercent = 10, name = "Крем-суп из грибов", imgUrl = "")
    private val product4 = Product(id = 4, categoryId = 1, price = 125.0, salePercent = 15, name = "Блинчики из гречневой муки", imgUrl = "")


    private val product5 = Product(id = 5, categoryId = 2, price = 90.0, salePercent = 7, name = "Сырники", imgUrl = "")
    private val product6 = Product(id = 6,  categoryId = 2, price = 200.0, salePercent = 5, name = "Крем-суп из шпината", imgUrl = "")
    private val product7 = Product(id = 7, categoryId = 2,  price = 170.0, salePercent = 15, name = "Семга на пару", imgUrl = "")
    private val product8 = Product(id = 8, categoryId = 5, price = 125.0, salePercent = 15, name = "Брауни из свеклы", imgUrl = "")

    private val product9 = Product(id = 9, categoryId = 3, price = 90.0, salePercent = 7, name = "Овсяная каша", imgUrl = "")
    private val product10 = Product(id = 10, categoryId = 3, price = 150.0,  salePercent = 10, name = "Куриное филе в сливках",  imgUrl = "")
    private val product11 = Product(id = 11, categoryId = 3, price = 60.0, salePercent = 11, name = "Запеканка из кабачков и брынзы", imgUrl = "")
    private val product12 = Product(id = 12, categoryId = 3, price = 190.0, salePercent = 4, name = "Морковные маффины", imgUrl = "")

    private val product13 = Product(id = 13, categoryId = 4, price = 90.0, salePercent = 7, name = "Тар-тар из лосося", imgUrl = "")
    private val product14 = Product(id = 14, categoryId = 4, price = 150.0,  salePercent = 10, name = "Борщ",  imgUrl = "")
    private val product15 = Product(id = 15, categoryId = 4, price = 90.0, salePercent = 6, name = "Рулетики из баклажанов", imgUrl = "")
    private val product16 = Product(id = 16, categoryId = 4, price = 125.0, salePercent = 15, name = "Блинчики из гречневой муки", imgUrl = "")

    private val product17 = Product(id = 17, categoryId = 5, price = 90.0, salePercent = 7, name = "Яйца пашот", imgUrl = "")
    private val product18 = Product(id = 18, categoryId = 5, price = 150.0,  salePercent = 10, name = "Сырный суп",  imgUrl = "")
    private val product19 = Product(id = 19, categoryId = 5, price = 90.0, salePercent = 6, name = "Брускетта с авокадо", imgUrl = "")
    private val product20 = Product(id = 20, categoryId = 5, price = 125.0, salePercent = 15, name = "Шоколадный фондан", imgUrl = "")

    private val product21 = Product(id = 21, categoryId = 6, price = 90.0, salePercent = 7, name = "Яйца пашот", imgUrl = "")
    private val product22 = Product(id = 22, categoryId = 6, price = 150.0,  salePercent = 10, name = "Паста болоньезе",  imgUrl = "")
    private val product23 = Product(id = 23, categoryId = 7, price = 90.0, salePercent = 6, name = "Артишоки с чесноком", imgUrl = "")
    private val product24 = Product(id = 24, categoryId = 7, price = 125.0, salePercent = 15, name = "Фисташковое мороженое", imgUrl = "")

    var list = mutableListOf(product1, product2, product3, product4, product5,
        product6, product7, product8, product9, product10, product11, product12,
        product13, product14, product15, product16, product17,
        product18, product19, product20, product21, product22, product23, product24)

    private var sumPrice : Double = 0.0;
    private var discountPrice : Double= 0.0;
    private var finalPrice : Double= 0.0;

    private val model = CreateOrderModel()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val productIds = basketDao.getAllProducts()
        setPrices(productIds)
        viewState.setSumPrice(makeFinalPrice(sumPrice))
        viewState.setDiscountPrice(makeFinalPrice(discountPrice))
        viewState.setFinalPrice(makeFinalPrice(finalPrice))
    }

    override fun attachView(view: CheckoutView?) {
        super.attachView(view)
        val productIds = basketDao.getAllProducts()
        setPrices(productIds)
        viewState.setSumPrice(makeFinalPrice(sumPrice))
        viewState.setDiscountPrice(makeFinalPrice(discountPrice))
        viewState.setFinalPrice(makeFinalPrice(finalPrice))
    }

    fun checkFirstName(text : String){
        if(!checkSymbols((text))) model.firstName = text
        viewState.showErrorForFirstName(checkSymbols(text))
    }

    fun checkMiddleName(text : String){
        if(!checkSymbols((text))) model.middleName = text
        viewState.showErrorForMiddleName(checkSymbols(text))
    }

    fun checkLastName(text : String){
        if(!checkSymbols((text)))  model.lastName = text
        viewState.showErrorForLastName(checkSymbols(text))
    }

    fun checkPhoneNumber(text : String){
        if(!checkPhoneNumberSymbols((text))) model.phoneNumber = text
        viewState.showErrorForPhoneNumber(checkPhoneNumberSymbols(text))
    }

    private fun checkSymbols(text : String) : Boolean = text.length < 3

    private fun checkPhoneNumberSymbols (text : String) : Boolean{
        //проверили длину номера
        if(text.length <= 11){
            //проверили первый символ
            if((text[0] != '8')||(text[0] != '+')) {
                return false
            }
            else{
                //проверили второй символ
                if(text.length >= 2) {
                    //комбинация +7
                    if(text[0] == '+'){
                        return text[1] != '7'
                    }
                    else{
                        return false
                    }
                }
                return false
            }

        }
        else{
            //скорректировали длину номера на случай +7
            return !((text.length == 12)&&(text[0] == '+')&&(text[1] == '7'))
        }
    }

    fun setPrices(productIds : List<Long>) {
        var sumPriceLocal : Double = 0.0
        var finalPriceLocal : Double = 0.0
        var discountPriceLocal : Double = 0.0
        for (i in 0 until list.size) {
            for(j in 0 until productIds.size){
                if(productIds[j] == list[i].getId()){
                    sumPriceLocal = sumPriceLocal + list[i].getPrice()
                    finalPriceLocal = finalPriceLocal + list[i].calcDiscountPrice()
                }
            }
        }

        sumPrice = sumPriceLocal
        finalPrice = finalPriceLocal
        discountPrice = sumPriceLocal - finalPriceLocal
    }

    fun makeFinalPrice(price : Double) : String{
        val finalPrice : String
        finalPrice  = if (Math.floor(price) == (price)) Math.floor(price).toInt().toString() else String.format("%.2f", price)
        return finalPrice
    }

}