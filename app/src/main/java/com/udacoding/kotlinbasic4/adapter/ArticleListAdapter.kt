package com.udacoding.kotlinbasic4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.kotlinbasic4.databinding.ListItemsBinding
import com.udacoding.kotlinbasic4.model.ArticleResponse
import com.udacoding.kotlinbasic4.model.ArticlesItems
import java.util.*
import kotlin.collections.ArrayList

class ArticleListAdapter(private var list: ArrayList<ArticlesItems>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>(), Filterable {

    private var onItemClickCallback: OnItemClickCallback? = null
    var filterList = ArrayList<ArticlesItems>()

    init {
        filterList = list
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    filterList = list
                }else{
                    val resultList = ArrayList<ArticlesItems>()

                    for (row in list) {
                        if (row.title?.toLowerCase(Locale.ROOT)!!.contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<ArticlesItems>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListAdapter.ViewHolder {
        val view = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleListAdapter.ViewHolder, position: Int) {
//        holder.bind(list[position])
        holder.bind(filterList[position])
    }

    override fun getItemCount(): Int = filterList.size

    inner class ViewHolder(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ArticlesItems){
            binding.apply {
                Glide.with(root)
                    .load(data.urlToImage)
                    .into(ivImage)

                tvTitle.text = data.title
                tvContent.text = data.content

                tvTitle.maxLines = 1
                tvContent.maxLines = 1

                root.setOnClickListener {
                    onItemClickCallback?.itemClicked(data)
                }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun itemClicked(data: ArticlesItems)
    }
}