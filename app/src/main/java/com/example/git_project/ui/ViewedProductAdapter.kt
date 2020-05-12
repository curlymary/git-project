package com.example.git_project.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.git_project.R
import com.example.git_project.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_viewed_product.view.*

class ViewedProductAdapter() : RecyclerView.Adapter<ViewedProductAdapter.ViewHolder>(){
    private var viewedProducts : List<Product> = listOf()

    fun setData(productList: List<Product>){
        this.viewedProducts = productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedProductAdapter.ViewHolder {
        Log.d("Adapter", "onCreateViewFolder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewed_product, parent, false))
    }

    override fun getItemCount(): Int = viewedProducts.size

    override fun onBindViewHolder(holder: ViewedProductAdapter.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewFolder position $position")
        holder.bind(viewedProducts[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(product: Product){
            containerView.viewedProductTv.text = product.getName()
        }
    }
}