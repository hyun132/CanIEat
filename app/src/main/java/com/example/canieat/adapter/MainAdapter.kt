package com.example.canieat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canieat.R
import com.example.canieat.SearchViewModel
import com.example.canieat.model.SearchItem
import kotlinx.android.synthetic.main.search_item.view.*

class MainAdapter:RecyclerView.Adapter<MainAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item,parent,false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item =differ.currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SearchViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(item: SearchItem){
            itemView.search_result_textView.text=item.prdlstNm
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<SearchItem>() {
        //둘이 같은 객체인지
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.prdlstReportNo == newItem.prdlstReportNo
        }

        //둘이 같은 아이템인지
        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    //클릭리스너 선언
    private var onItemClickListener: ((SearchItem) -> Unit)? = null
    //클릭리스너 등록 매소드
    fun setOnItemClickListener(listener: (SearchItem) -> Unit) {
        onItemClickListener = listener
    }
}