package com.example.git_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), PricePrinterView, ListPrinterView {

    val presenter = ProductsPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.totalPricePrint()
        presenter.productListPrint()
    }

    override fun print(price: Double) {
        Log.d("Print", "Price: $price")
    }

    override fun printList(productList: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
