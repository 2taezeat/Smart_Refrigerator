package com.ebookfrenzy.carddemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.NotificationsCardviewBinding
import wikibook.learnandroid.smart_refrigerator.repository.NotificationInfo

class NotificationAdapter(val notificationInfo : List<NotificationInfo>) : RecyclerView.Adapter<NotificationAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(val notificationsCardviewBinding: NotificationsCardviewBinding)
        : RecyclerView.ViewHolder(notificationsCardviewBinding.root)



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotificationAdapter.CustomViewHolder {
        val bind = DataBindingUtil.inflate<NotificationsCardviewBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.notifications_cardview,
            viewGroup,
            false
        )
        return CustomViewHolder(bind)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, i: Int) {
        viewHolder.notificationsCardviewBinding.notificationKindTextview.text = notificationInfo[i].kind
        viewHolder.notificationsCardviewBinding.notificationLocationTextview.text = notificationInfo[i].location
        viewHolder.notificationsCardviewBinding.notificationCountTextview.text = notificationInfo[i].count.toString()
        viewHolder.notificationsCardviewBinding.notificationText.text = notificationInfo[i].notificationBody

        //val timeFormat = SimpleDateFormat("yy-MM-dd, hh:mm", Locale.getDefault())
        //val time = timeFormat.format(notificationInfo[i].notificationTime.time)
        viewHolder.notificationsCardviewBinding.notificationTimeTextview.text = notificationInfo[i].notificationTime

        when (notificationInfo[i].notificationCategory) {
            "N1" -> {
                viewHolder.notificationsCardviewBinding.notificationImageview.setBackgroundColor(
                    Color.parseColor("#FFE65151"))
            }
            "N2" -> {
                viewHolder.notificationsCardviewBinding.notificationImageview.setBackgroundColor(
                    Color.parseColor("#FFF1BA5D"))
            }
            "N3" -> {
                viewHolder.notificationsCardviewBinding.notificationImageview.setBackgroundColor(
                    Color.parseColor("#FFAEEC58"))
            }
            "N4" -> {
                viewHolder.notificationsCardviewBinding.notificationImageview.setBackgroundColor(
                    Color.parseColor("#FF58C2EC"))
            }
        }


    }

    override fun getItemCount(): Int {
        return notificationInfo.size
    }
}
