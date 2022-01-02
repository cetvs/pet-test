package com.example.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.classes.Person
import com.example.myapplication.R
import java.io.Serializable

class MyRecyclerAdapter(private val context: Context, private var list: ArrayList<Person>,
                        var listener: AdapterView.OnItemClickListener? = null)
    : Serializable, RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_layout, parent, false)
        return MyRecyclerHolder(itemView)
    }

    override fun getItemCount(): Int = list.size


    fun getItem(index: Int) : Person
    {
        return list[index]
    }

    fun getList() : ArrayList<Person>
    {
        return list
    }

    fun setData(lst: ArrayList<Person>)
    {
        list = lst
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
        val person: Person = list[position]
        holder.bind(person)
    }


    inner class MyRecyclerHolder(itemView: View):
            RecyclerView.ViewHolder(itemView)//, View.OnClickListener
    {

        private var positionView: TextView?= null
        private var nameView: TextView?= null
        private var imageView: ImageView?= null

        init {
            //itemView.setOnClickListener(this)
            positionView = itemView.findViewById(R.id.tv_description)
            nameView = itemView.findViewById(R.id.tv_name)
            imageView = itemView.findViewById(R.id.iv_poster)
        }

        fun bind(person: Person) {
            //imageView?.setImageResource(movie.img!!)
            imageView!!.setImageResource(R.drawable.anon)
//            Glide.with(context)
//                    .load(person.avatarUrl)
//                    .into(imageView!!)
            nameView?.text = person.firstName +  person.lastName
            positionView?.text = person.position


        }

//        override fun onClick(v: View?) {
//            val position = adapterPosition
////            if (v?.getId() == R.id.imageView)
////            {
////                favImageView?.setImageResource(R.drawable.fav)
//            if(position != RecyclerView.NO_POSITION){
//                listener!!.onItemClick(position)
//            }
//        }
    }


}