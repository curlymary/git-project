package com.example.git_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.basket_layout.*

class BasketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)

        //получили id продукта для добавления его в корзину
        val productID = intent.extras?.getInt(CatalogActivity.PRODUCT_ID, -1)
        Log.d(tag, productID.toString())

        btnMakeAnOrder.setOnClickListener{
            val intent = Intent(this, CheckoutActivity:: class.java).apply{
                //передали id продукта для просмотра деталей
                putExtra(CatalogActivity.PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        btnBasketToCatalog.setOnClickListener{
            val intent = Intent(this, CatalogActivity:: class.java)
            startActivity(intent)
        }

        imgArrowBasket.setOnClickListener{
            finish()
        }


    }
}
