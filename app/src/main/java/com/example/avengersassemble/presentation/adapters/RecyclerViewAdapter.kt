package com.example.avengersassemble.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.avengersassemble.R
import com.example.avengersassemble.data.local.entity.FavouriteList
import com.example.avengersassemble.util.CommonUtil.loadImageFromCoil

class RecyclerViewAdapter(
    private val items: List<FavouriteList>,
    private val onClickListener: ((doctorData: FavouriteList) -> Unit)
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)

        return ViewHolder(view,onClickListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val view : View,
        private val onClickListener: ((doctorData: FavouriteList) -> Unit))
        : RecyclerView.ViewHolder(view){

        private val imgViewProfile : ImageView = view.findViewById(R.id.imgProfileImage)
        private val imageBtnFavourite : ImageView = view.findViewById(R.id.imgbtnFavourite)
        private val textViewName : TextView = view.findViewById(R.id.txtProfileName)
        private val textViewMovieName : TextView = view.findViewById(R.id.txtMovieName)
        private val textViewCreatedby : TextView = view.findViewById(R.id.txtCreatedby)
        private val textViewPublisher : TextView = view.findViewById(R.id.txtPublisher)
        private val btnBiography : TextView = view.findViewById(R.id.btnBiography)

        fun bind(data : FavouriteList){

            textViewName.text = data.name
            textViewMovieName.text = data.realname
            textViewCreatedby.text = data.createdby
            textViewPublisher.text = data.publisher

            imgViewProfile.loadImageFromCoil(data.imageurl)

            if (data.isFavourite) {
                imageBtnFavourite.setImageResource(R.drawable.ic_favorite)

            } else {
                imageBtnFavourite.setImageResource(R.drawable.ic_unfavorite)
            }

           // view.setOnClickListener { onClickListener.invoke(data) }
            btnBiography.setOnClickListener{
                onClickListener.invoke(data)
            }
        }

    }

}