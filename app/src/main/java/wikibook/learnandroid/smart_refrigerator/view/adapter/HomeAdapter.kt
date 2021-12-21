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


    private val locationArray = arrayOf( "A", "B", "C", "C", "B", "A", "B", "A" )
    private val kindArray = arrayOf( "Apple", "asd", "ASD", "asD", "Bannna", "Apple", "apple", "cccc" )
    private val dataArray = arrayOf( "2010-03-25", "2011-03-25", "2012-03-25", "2010-02-25", "2010-02-28", "2009-12-25", "2030-01-01", "1999-03-25" )


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
        //viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingKind.text = titles[i]
        //viewHolder.homeCardviewBinding.dashboardEdittextEditingKind.setText(titles[i])
        //viewHolder.notificationsCardviewBinding.itemImage.setImageResource(images[i])


        viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
        viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
        viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location


        if (searchItem.isBlank()) {
            viewHolder.homeCardviewBinding.homeKindTextview.text = kindArray[i]
            viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
        } else {
            if (searchItem.lowercase() == kindArray[i].lowercase()) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = kindArray[i]
                viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
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
            viewHolder.homeCardviewBinding.homeKindTextview.text = kindArray[i]
            viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
        } else {
            if (selectLocationArray.contains(locationArray[i])) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = kindArray[i]
                viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
            } else {
                viewHolder.homeCardviewBinding.homeCardViewMain.visibility = View.INVISIBLE
                viewHolder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            }


        }

        //if (locationArray[i] == )


    }

    override fun getItemCount(): Int {
        return contentSArrayList.size
    }
}


