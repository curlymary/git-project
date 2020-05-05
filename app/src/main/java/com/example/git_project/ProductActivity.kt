package com.example.git_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.git_project.ui.BaseActivity
import com.example.git_project.ui.BasketActivity
import com.example.git_project.ui.CatalogActivity
import kotlinx.android.synthetic.main.product_layout.*

class ProductActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_layout)

        //получили id продукта для отображения информации о нем
        val productID = intent.extras?.getInt(CatalogActivity.PRODUCT_ID, -1)
        Log.d(tag, productID.toString())

        btnGoToBasket.setOnClickListener{
            val intent = Intent(this, BasketActivity:: class.java).apply{
                //передаем id товара для добавления в корзину
                putExtra(CatalogActivity.PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        btnProductToCatalog.setOnClickListener{
            val intent = Intent(this, CatalogActivity:: class.java)
            startActivity(intent)
        }

        imgArrowProduct.setOnClickListener{
            finish()
        }
    }

    override fun onSaveInstanceState(outState : Bundle){
        outState.putInt(SAVE_STATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode : Int, resultCode: Int, data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(REQUEST_AUTH == requestCode){
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, isUserAuth.toString())
        }
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH : Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_STATE_INT = "SAVE_STATE_INT"
    }
}