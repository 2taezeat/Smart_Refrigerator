package com.ebookfrenzy.carddemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.DashboardEditCardviewBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents

class DashboardEditingAdapter(val contentSArrayList : ArrayList<Contents>) : RecyclerView.Adapter<DashboardEditingAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(val dashboardEditCardviewBinding: DashboardEditCardviewBinding)
        : RecyclerView.ViewHolder(dashboardEditCardviewBinding.root)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DashboardEditingAdapter.CustomViewHolder {
        val bind = DataBindingUtil.inflate<DashboardEditCardviewBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.dashboard_edit_cardview,
            viewGroup,
            false
        )
        return CustomViewHolder(bind)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, i: Int) {
        viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingKind.setText(contentSArrayList[i].kind)
        viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingLocation.setText(contentSArrayList[i].location)
        viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingCount.setText(contentSArrayList[i].count.toString())
    }

    override fun getItemCount(): Int {
        return contentSArrayList.size
    }
}
