package com.osandroid.grandapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osandroid.grandapp.databinding.RowProfileItemBinding
import com.osandroid.grandapp.roomDatabase.model.Albums

class UserAlbumsAdapter : RecyclerView.Adapter<UserAlbumsAdapter.MyViewHolder>() {
    private var albums: List<Albums> = emptyList()
    lateinit var mListener: OnClickAlbumListener


    fun setData(dataList: List<Albums>) {
        this.albums = dataList
        notifyDataSetChanged()
    }

    fun initListener(mListener: OnClickAlbumListener) {
        this.mListener = mListener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowProfileItemBinding.bind(itemView)

        fun bind(album: Albums) {
            binding.albumTitle.text = album.title
            binding.albumId.text = album.id.toString()

            itemView.setOnClickListener {
                mListener.onAlbumClicked(albums[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    interface OnClickAlbumListener {
        fun onAlbumClicked(id: Int)
    }
}