package com.ebookfrenzy.carddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.HomeCardviewBinding

class HomeAdapter(val searchItem : String, val sortMethod : String, val selectLocationArray : ArrayList<String> ) : RecyclerView.Adapter<HomeAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(val homeCardviewBinding: HomeCardviewBinding)
        : RecyclerView.ViewHolder(homeCardviewBinding.root)

    private val titles = arrayOf("Chapter One",
        "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven",
        "Chapter Eight")

    private val details = arrayOf("Item one details", "Item two details",
        "Item three details", "Item four details",
        "Item five details", "Item six details",
        "Item seven details", "Item eight details")

    private val images = intArrayOf(1,2,3,4,5,6,7,8)

    private val locationArray = arrayOf( "A", "B", "C", "C", "B", "A", "B", "A" )

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


        if (selectLocationArray[0] == "All") {
            viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
        } else {
            if (selectLocationArray.contains(locationArray[i])) {
                viewHolder.homeCardviewBinding.homeLocationTextview.text = locationArray[i]
            } else {
                viewHolder.homeCardviewBinding.homeCardViewMain.visibility = View.INVISIBLE
                viewHolder.itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
            }


        }

        //if (locationArray[i] == )


    }

    override fun getItemCount(): Int {
        return titles.size
    }
}


