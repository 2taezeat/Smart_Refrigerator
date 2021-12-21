package com.ebookfrenzy.carddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.DashboardEditCardviewBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents

class DashboardEditingAdapter(val contentSArrayList : ArrayList<Contents>) : RecyclerView.Adapter<DashboardEditingAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(val dashboardEditCardviewBinding: DashboardEditCardviewBinding)
        : RecyclerView.ViewHolder(dashboardEditCardviewBinding.root)


    interface OnItemClickListener {
        fun onClick(v: View, position: Int, id : Long, count : Int, kind : String, location : String, delete : Boolean)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener



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

        viewHolder.dashboardEditCardviewBinding.dashboardEditApplyButton.setOnClickListener {

            val editKind = viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingKind.text.toString()
            val editCount = viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingCount.text.toString().toInt()
            val editLocation = viewHolder.dashboardEditCardviewBinding.dashboardEdittextEditingLocation.text.toString()
            val editDelete = viewHolder.dashboardEditCardviewBinding.dashboardEditingDeleteCheckbox.isChecked


            itemClickListener.onClick(it, i, contentSArrayList[i].id, editCount, editKind, editLocation, editDelete)
        }
    }

    override fun getItemCount(): Int {
        return contentSArrayList.size
    }
}
