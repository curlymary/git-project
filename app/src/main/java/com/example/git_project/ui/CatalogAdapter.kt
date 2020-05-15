package com.example.git_project.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.git_project.R
import com.example.git_project.domain.model.Category
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.view.*

class CatalogAdapter (
    private val onCategoryClick : (category: Category)  -> Unit) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {
    private var categories : List<Category> = listOf()

    fun setData(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.ViewHolder {
        Log.d("Adapter", "onCreateViewFolder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CatalogAdapter.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewFolder position $position")
        holder.bind(categories[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(category: Category){
            containerView.categoryTv.text = category.getName()
            containerView.setOnClickListener{
                onCategoryClick(category)
            }
        }
    }
}