package com.ebookfrenzy.carddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.HomeCardviewBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents
import wikibook.learnandroid.smart_refrigerator.repository.ImageInfo

class HomeAdapter(val searchItem : String, val sortMethod : String, val selectLocationArray : ArrayList<String>, val contentSArrayList : ArrayList<Contents>, val imagesArrayList : ArrayList<ImageInfo> ) : RecyclerView.Adapter<HomeAdapter.CustomViewHolder>() {
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

        var imageUrl : String?

        if (imagesArrayList.isNotEmpty()) {
            imageUrl = imagesArrayList[i].imageUrl
        } else {
            imageUrl = null
        }


        if (searchItem.isBlank()) {
            viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
            viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
            viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
            viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
            viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
            viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
            viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
            Glide.with(viewHolder.homeCardviewBinding.root.context)
                .load(imageUrl)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.homeCardviewBinding.homeContentImageview) // Glide를 사용하여 이미지 로드
        } else {
            if (searchItem.lowercase() == contentSArrayList[i].kind.lowercase()) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
                viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
                viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
                viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
                viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
                viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
                viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
                Glide.with(viewHolder.homeCardviewBinding.root.context)
                    .load(imageUrl)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.homeCardviewBinding.homeContentImageview) // Glide를 사용하여 이미지 로드
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
            Glide.with(viewHolder.homeCardviewBinding.root.context)
                .load(imageUrl)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.homeCardviewBinding.homeContentImageview) // Glide를 사용하여 이미지 로드
        } else {
            if (selectLocationArray.contains(contentSArrayList[i].location)) {
                viewHolder.homeCardviewBinding.homeKindTextview.text = contentSArrayList[i].kind
                viewHolder.homeCardviewBinding.homeLocationTextview.text = contentSArrayList[i].location
                viewHolder.homeCardviewBinding.homeShelflifeTextview.text = contentSArrayList[i].shelfTime
                viewHolder.homeCardviewBinding.homeUpdateTextview.text = contentSArrayList[i].updateTime
                viewHolder.homeCardviewBinding.homePurchaseTextview.text = contentSArrayList[i].purchaseDate
                viewHolder.homeCardviewBinding.homeMemoTextview.text = contentSArrayList[i].memo
                viewHolder.homeCardviewBinding.homeCountTextview.text = contentSArrayList[i].count.toString()
                Glide.with(viewHolder.homeCardviewBinding.root.context)
                    .load(imageUrl)
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.homeCardviewBinding.homeContentImageview) // Glide를 사용하여 이미지 로드
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



}


