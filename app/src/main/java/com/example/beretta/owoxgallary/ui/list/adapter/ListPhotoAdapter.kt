package com.example.beretta.owoxgallary.ui.list.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.models.network.response.PhotoRest

/**
 * Created by beretta on 12.10.2017.
 */
class ListPhotoAdapter(
        ctx: Context,
        val onClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {

    interface OnItemClickListener {
        fun photoClicked(photo: PhotoRest)
    }

    var dataset: List<PhotoRest>
    val displaymetrics = ctx.resources.displayMetrics
    val imageTransition = DrawableTransitionOptions.withCrossFade()

    init {
        dataset = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)

        return ViewHolder(view,
                view.findViewById<AppCompatImageView>(R.id.item_image_img),
                view.findViewById<AppCompatTextView>(R.id.item_image_author))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val nextItem = dataset[position]

        //image.width .... image.height
        //device.width ... device
        val ratio = nextItem.width.toFloat() / nextItem.height.toFloat()
        val finalHeight = (displaymetrics.widthPixels / ratio).toInt()
        holder.image.minimumHeight = finalHeight

        holder.author.text = "${nextItem.user.last_name} ${nextItem.user.first_name}"
        Glide.with(holder.itemView.context)
                .load(nextItem.urls.regular)
                .transition(imageTransition)
                .into(holder.image)

        holder.itemView.setOnClickListener { onClickListener.photoClicked(nextItem) }
    }

    override fun getItemCount(): Int = dataset.size

    fun swapData(newList: List<PhotoRest>) {
        if (dataset.isEmpty()) {
            /*
             * notifyDataSetChanged() - it's a magic to work around with
              * IndexOutOfBoundsException: Inconsistency detected. Invalid item position
             * Without this line after leaving app for example to Home and back, Activity  not
             * destroying, it returns in onStart() and I don't know why it always crash with
             *IndexOutOfBoundsException: Inconsistency detected. Invalid item position 0(offset:10).state:10 android.support.v7.widget.RecyclerView{f09a21a VFED..... .F....ID 0,0-1080,1731 #7f0b006c app:id/a_chat_list_recycler}, adapter:edu.beretta.ico.chat.ui.chat_list.adapter.ChatListAdapter@9a214b, layout:android.support.v7.widget.LinearLayoutManager@2c73728, context:edu.beretta.ico.chat.ui.chat_list.ChatListActivity@8b4e268
             * after couple of hours in research, I found several solution and stopped on this
             */
            notifyDataSetChanged()
            dataset = newList
            notifyItemRangeInserted(0, newList.size)
        } else {
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return dataset.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = dataset[oldItemPosition]
                    val newest = newList[newItemPosition]
                    return old.id.equals(newest.id, true)
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = dataset[oldItemPosition]
                    val newest = newList[newItemPosition]
                    return old.urls.regular.equals(newest.urls.regular, true)
                }
            })
            dataset = newList
            diffResult.dispatchUpdatesTo(this)
        }
    }
}

class ViewHolder(rootView: View,
                 val image: AppCompatImageView,
                 val author: AppCompatTextView) : RecyclerView.ViewHolder(rootView) {
}