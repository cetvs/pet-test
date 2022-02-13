package com.example.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.models.Person
import com.example.myapplication.R
import com.example.myapplication.presentation.adapters.OnItemClickListener
import java.io.Serializable

class MyRecyclerAdapter(private val context: Context, private var list: ArrayList<Person>,
                        var listener: OnItemClickListener? = null)
    : Serializable, RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>(){

    private var partList : ArrayList<Person> = arrayListOf()

    init {
        setPartList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_layout, parent, false)
        return MyRecyclerHolder(itemView)
    }

    override fun getItemCount(): Int = partList.size


    fun getItem(index: Int) : Person
    {
        return partList[index]
    }

    fun getList() : ArrayList<Person>
    {
        return list
    }

    fun getPartList() : ArrayList<Person>
    {
        return partList
    }

    fun setData(lst: ArrayList<Person>)
    {
        list = lst
        setPartList(list)
        notifyDataSetChanged()
    }

    fun setPartList(lst: ArrayList<Person>)
    {
        partList = arrayListOf()
        for(i in 0 until lst.size)
            partList!!.add(lst[i])
    }

    override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
        val person: Person = partList[position]
        holder.bind(person)
    }


    inner class MyRecyclerHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        private var person: Person? = null

        private var positionView: TextView?= null
        private var nameView: TextView?= null
        private var imageView: ImageView?= null

        init {
            itemView.setOnClickListener(this)
            positionView = itemView.findViewById(R.id.tv_description)
            nameView = itemView.findViewById(R.id.tv_name)
            imageView = itemView.findViewById(R.id.iv_poster)
        }

        fun bind(person: Person) {
            //imageView?.setImageResource(movie.img!!)
            this.person = person
            imageView!!.setImageResource(R.drawable.anon)
//            Glide.with(context)
//                    .load(person.avatarUrl)
//                    .into(imageView!!)
            nameView?.text = person.firstName + " " + person.lastName
            positionView?.text = person.position


        }

        override fun onClick(v: View?) {
            val position = adapterPosition
//            if (v?.getId() == R.id.imageView)
//            {
//                favImageView?.setImageResource(R.drawable.fav)
            if(position != RecyclerView.NO_POSITION){
                listener!!.onItemClick(person!!)
            }
        }
    }


}