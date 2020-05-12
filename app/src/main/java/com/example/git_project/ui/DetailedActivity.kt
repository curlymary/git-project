package com.example.git_project.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.git_project.R
import com.example.git_project.data.ViewedProductDaoImpl
import com.example.git_project.domain.model.Product
import com.example.git_project.presenter.DetailedPresenter
import com.example.git_project.presenter.DetailedView
import kotlinx.android.synthetic.main.detailed_layout.*
import moxy.ktx.moxyPresenter

class DetailedActivity : BaseActivity(), DetailedView {
    private val presenter by moxyPresenter {
        DetailedPresenter(
            ViewedProductDaoImpl(sharedPreferences)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)
        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.getImgUrl())
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
        tvDetailedTitle.text = product.getName()
        tvDetailedPrice.text = product.calcDiscountPrice().toString()
        presenter.onProductShow(product)
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}