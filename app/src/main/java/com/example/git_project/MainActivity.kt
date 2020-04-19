package com.example.git_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProductsView
{
    private val presenter = ProductsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalSum.text = "2000Р"
        txtDiscountSum.text = "300Р"
        txtCostSum.text = "2300Р"

        presenter.attachView(this)

        btnOrder.setOnClickListener{
            Toast.makeText(this,"test", Toast.LENGTH_SHORT).show()
        }

        setListeners()

        //presenter.totalPricePrint()
        //presenter.productListPrint()
    }

    private fun setListeners (){

        /*txtLastName.onFocusChangeListener = View.OnFocusChangeListener{v, hasFocus ->
            if(!hasFocus){
                val visible = if(checkSymbols(txtLastName.text.toString())) false
                else true
                txtLastName.ShowError(true)
            }
        }*/

        /*txtLastName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val visible = checkSymbols(s.toString())
                txtLastName.ShowError(visible)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })*/

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

    /*override fun printList(productList: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/
}
