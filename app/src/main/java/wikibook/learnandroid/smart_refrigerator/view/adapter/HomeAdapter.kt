package com.ebookfrenzy.carddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.HomeCardviewBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents

class HomeAdapter(val searchItem : String, val sortMethod : String, val selectLocationArray : ArrayList<String>, val contentSArrayList : ArrayList<Contents> ) : RecyclerView.Adapter<HomeAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(val homeCardviewBinding: HomeCardviewBinding)
        : RecyclerView.ViewHolder(homeCardviewBinding.root)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeAdapter.CustomViewHolder {
        val bind = DataBindingUtil.inflate<HomeCardviewBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.home_cardview,
            viewGroup,
            false
        )

        return CustomViewHolder(bind)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, i: Int) {
        if (searchItem.isBlank()) {
            viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
            viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
            viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
            viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
            viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
            viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
            viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
        } else {
            if (searchItem.lowercase() == contentSArrayList[i].kind.lowercase()) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
                viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
                viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
                viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
                viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
                viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
                viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
            } else {
                viewHolder.homeCardviewBinding.homeCardViewMain.visibility = View.INVISIBLE
                viewHolder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            }
        }

        if (selectLocationArray.isEmpty()) {
            viewHolder.homeCardviewBinding.homeCardViewMain.visibility = View.INVISIBLE
            viewHolder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else if (selectLocationArray[0] == "All" ) {
            viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
            viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
            viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
            viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
            viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
            viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
            viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
        } else {
            if (selectLocationArray.contains(contentSArrayList[i].location)) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
                viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
                viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
                viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
                viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
                viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
                viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
            } else {
                viewHolder.homeCardviewBinding.homeCardViewMain.visibility = View.INVISIBLE
                viewHolder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            }
        }

    }

    override fun getItemCount(): Int {
        return contentSArrayList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    fun updateReceiptsList(newlist: ArrayList<Contents>) {
        contentSArrayList.clear()
        contentSArrayList.addAll(newlist)
        this.notifyDataSetChanged()

    }

}


