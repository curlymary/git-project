package com.example.git_project.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.git_project.ui.CatalogActivity.Companion.IS_USER_AUTH
import com.example.git_project.ui.CatalogActivity.Companion.PRODUCT_ID
import com.example.git_project.ui.CatalogActivity.Companion.REQUEST_AUTH
import com.example.git_project.presenter.CheckoutPresenter
import com.example.git_project.R
import com.example.git_project.data.BasketDaoImpl
import com.example.git_project.presenter.CheckoutView
import kotlinx.android.synthetic.main.checkout_layout.*
import moxy.ktx.moxyPresenter

class CheckoutActivity : BaseActivity(), CheckoutView {
    private val presenter by moxyPresenter {
        CheckoutPresenter(
            BasketDaoImpl(sharedPreferences)
        )
    }

    private var isAuth : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_layout)

        val productID = intent.extras?.getInt(PRODUCT_ID, -1)
        Log.d(tag, productID.toString())

        presenter.attachView(this)

        btnOrder.setOnClickListener{
            Toast.makeText(this,"Ваш заказ оформлен", Toast.LENGTH_SHORT).show()
            /*isAuth = true
            setResult(REQUEST_AUTH, Intent().apply{
                putExtra(IS_USER_AUTH, isAuth)
            })
            startActivityForResult(intent, REQUEST_AUTH)*/
        }
        setListeners()
    }

    private fun setListeners (){

        txtLastName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        txtMiddleName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        txtFirstName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        txtPhoneNumber.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhoneNumber(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        imgArrowCheckout.setOnClickListener{
            finish()
        }
    }

    fun EditText.ShowError(visible : Boolean){
        var drawable = if(visible) R.drawable.ic_error
        else 0
        this.setCompoundDrawablesWithIntrinsicBounds(0,0, drawable,0)
    }

    private fun checkSymbols(text : String) : Boolean = text.length < 3

    override fun print(price: Double) {
        Log.d("Print", "Price: $price")
    }

    override fun showErrorForFirstName(visible: Boolean) {
        txtFirstName.ShowError(visible)
    }

    override fun showErrorForMiddleName(visible: Boolean) {
        txtMiddleName.ShowError(visible)
    }

    override fun showErrorForLastName(visible: Boolean) {
        txtLastName.ShowError(visible)
    }

    override fun showErrorForPhoneNumber(visible: Boolean) {
        txtPhoneNumber.ShowError(visible)
    }

    override fun setSumPrice(price : String){
        txtCostSum.text = price + "Р"
    }

    override fun setDiscountPrice(price : String){
        txtDiscountSum.text = price + "Р"
    }

    override fun setFinalPrice(price : String){
        txtTotalSum.text = price + "Р"
    }
}
