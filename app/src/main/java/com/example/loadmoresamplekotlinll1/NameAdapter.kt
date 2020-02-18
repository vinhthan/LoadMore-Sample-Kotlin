package com.example.loadmoresamplekotlinll1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(
    private val list: ArrayList<String?>,
    itemOnClick: ItemOnClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NAME = 0
    private val VIEW_TYPE_LOADING = 1

    private val itemOnClick: ItemOnClick = itemOnClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NAME){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.name_layout, parent, false)
            NameViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == null){
            VIEW_TYPE_LOADING
        }else{
            VIEW_TYPE_NAME
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NameViewHolder){
            populateNameRows(holder, position)
        }else if (holder is LoadingViewHolder){
            showLoadingView(holder, position)
        }
    }

    private inner class NameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tvName)

        init {
            //click item
            itemView.setOnClickListener {
                itemOnClick.OnItemClick(position)
            }
        }
    }

    private inner class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    private fun showLoadingView(
        viewHolder: LoadingViewHolder,
        position: Int
    ){
        //ProgressBar would be displayed
    }

    private fun populateNameRows(
        viewHolder: NameViewHolder,
        position: Int
    ){
        val name = list[position]
        viewHolder.tvName.text = name
    }


}