package com.example.git_project.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.git_project.R
import com.example.git_project.data.BasketDaoImpl
import com.example.git_project.domain.model.Product
import com.example.git_project.presenter.DetailedPresenter
import com.example.git_project.presenter.DetailedView
import kotlinx.android.synthetic.main.detailed_layout.*
import moxy.ktx.moxyPresenter

class DetailedActivity : BaseActivity(), DetailedView {
    private val presenter by moxyPresenter {
        DetailedPresenter(
            BasketDaoImpl(sharedPreferences)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)

        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return

        tvDetailedTitle.text = product.getName()
        tvDetailedPrice.text = product.makeFinalPrice(product.getPrice())

        btnAddProductToBasket.setOnClickListener{
            addProductToBasket(product)
        }

        imgArrowDetail.setOnClickListener{
            finish()
        }
    }

    override fun addProductToBasket(product: Product) {
        presenter.addItemToBasket(product)
        Toast.makeText(this, "Блюдо " + product.getName() + " добавлено в корзину", Toast.LENGTH_LONG).show()
    }

    override fun showButtonAddToBasket(productIds : List<Long>)
    {
        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return

        if(productIds.contains(product.getId())){
            btnAddProductToBasket.visibility = View.INVISIBLE
        }
        else{
            btnAddProductToBasket.visibility = View.VISIBLE
        }
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}