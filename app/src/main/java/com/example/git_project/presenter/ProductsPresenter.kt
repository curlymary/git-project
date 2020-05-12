package com.example.git_project.presenter

import com.example.git_project.domain.model.Basket
import com.example.git_project.domain.model.CreateOrderModel
import com.example.git_project.domain.model.Product
import moxy.MvpPresenter

class ProductsPresenter : MvpPresenter<ProductsView> () {

    //инициализировали содержимое списка
    private val book1 = Product(
        id = 1,
        price = 150.0,
        salePercent = 15,
        name = "Гордость и предубеждение",
        imgUrl = ""
    )
    private val book2 = Product(
        id = 2,
        price = 173.0,
        salePercent = 10,
        name = "Приключения Робинзона Крузо",
        imgUrl = ""
    )
    private val book3 = Product(
        id = 3,
        price = 90.0,
        salePercent = 7,
        name = "Анжелика и Король",
        imgUrl = ""
    )
    //инициализировали список
    private val bookList = listOf(book1, book2, book3)

    private val model = CreateOrderModel()

    //создали корзину
    val storeBasket = Basket(bookList)

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
            return !((text[0] != '8')||(text[0] != '+'))
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
        }
        else{
            //скорректировали длину номера на случай +7
            return !((text.length == 12)&&(text[0] == '+')&&(text[1] == '7'))
        }
    }

    fun totalPricePrint(){
        print("Общая сумма корзины: ")
        viewState.print(storeBasket.calculateSumPrice())
    }
}