package com.example.git_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() //, PricePrinterView, ListPrinterView
{
    //val presenter = ProductsPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalSum.text = "2000Р"
        txtDiscountSum.text = "300Р"
        txtCostSum.text = "2300Р"

        btnOrder.setOnClickListener{
            Toast.makeText(this,"test", Toast.LENGTH_SHORT).show()
        }

        //presenter.totalPricePrint()
        //presenter.productListPrint()
    }

    /*override fun print(price: Double) {
        Log.d("Print", "Price: $price")
    }

    override fun printList(productList: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/
}
