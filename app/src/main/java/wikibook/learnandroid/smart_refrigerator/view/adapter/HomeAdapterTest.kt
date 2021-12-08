//package com.ebookfrenzy.carddemo
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import wikibook.learnandroid.smart_refrigerator.R
//import wikibook.learnandroid.smart_refrigerator.databinding.HomeCardviewBinding
//import android.R
//import android.content.Context
//import android.view.View
//import android.widget.Filter
//import android.widget.Filterable
//
//import android.widget.TextView
//
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//
//
//
//
//class FileListAdapter internal constructor(val context: Context, val fileList: ArrayList<String>?)
//    : RecyclerView.Adapter<FileListAdapter.FileViewHolder>(), Filterable {
//
//    private var files: ArrayList<String>? = fileList
//    private lateinit var itemClickListner: ItemClickListener
//
//    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val str: TextView = itemView.an_str
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
//        val itemView = LayoutInflater.from(context).inflate(R.layout.analytics_item, parent, false)
//        return FileViewHolder(itemView)
//
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
//        val current = files?.get(position)
//        holder.str.text = current
//
//        holder.itemView.setOnClickListener {
//            itemClickListner.onClick(it, position)
//        }
//
//    }
//
//
//    override fun getItemCount() = files?.size!!
//
//    override fun getFilter(): Filter? {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence): FilterResults {
//                val charString = constraint.toString()
//                files = if (charString.isEmpty()) {
//                    fileList
//                } else {
//                    val filteredList = ArrayList<String>()
//                    if (fileList != null) {
//                        for (name in fileList) {
//                            if(name.toLowerCase().contains(charString.toLowerCase())) {
//                                filteredList.add(name);
//                            }
//                        }
//                    }
//                    filteredList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = files
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                files  = results.values as ArrayList<String>
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    interface ItemClickListener {
//        fun onClick(view: View, position: Int)
//    }
//    fun setItemClickListener(itemClickListener: ItemClickListener) {
//        this.itemClickListner = itemClickListener
//    }
//
//}