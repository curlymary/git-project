package com.example.git_project

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.catalog_layout.*

class CatalogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        Log.d(tag, savedInstanceState.toString())
        val savedInt = savedInstanceState?.getInt("SAVE_STATE_INT")
        Log.d(tag, "saved $savedInt")

        btnMakeAnOrder.setOnClickListener{
            val intent = Intent(this, CheckoutActivity:: class.java).apply{
                putExtra(PRODUCT_ID, 1000)
            }
            startActivity(intent)
        }

        btnProductDetails.setOnClickListener{
            val intent = Intent(this, ProductActivity:: class.java).apply{
                //передали id продукта для просмотра деталей
                putExtra(PRODUCT_ID, 1000)
            }
            startActivity(intent)
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