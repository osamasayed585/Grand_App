package com.osandroid.grandapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osandroid.grandapp.R
import com.osandroid.grandapp.databinding.RowPhotoItemBinding
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.squareup.picasso.Picasso

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {
    private var photos: List<Photos> = emptyList()
    lateinit var mListener: OnClickAlbumListener


    fun setData(dataList: List<Photos>) {
        this.photos = dataList
        notifyDataSetChanged()
    }

    fun initListener(mListener: OnClickAlbumListener) {
        this.mListener = mListener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowPhotoItemBinding.bind(itemView)

        fun bind(photo: Photos) {
            Picasso.get()
                .load(photo.url)
                .placeholder(R.drawable.grand)
                .error(R.drawable.image_error)
                .into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    interface OnClickAlbumListener {
        fun onAlbumClicked(id: Int)
    }
}