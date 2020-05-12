package com.example.git_project.domain.model

/**
 * Модель для создания заказа
 */

data class CreateOrderModel (

    var firstName : String = "",

    var middleName : String = "",

    var lastName : String = "",

    var phoneNumber : String = ""
)