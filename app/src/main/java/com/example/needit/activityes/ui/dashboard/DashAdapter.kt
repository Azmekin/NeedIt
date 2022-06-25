package com.example.needit.activityes.ui.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.needit.R

class DashAdapter(private var c:Context, private val personalList: ArrayList<PersonRequest>)
    : RecyclerView.Adapter<DashAdapter.DashViewHolder>(){


    var onItemClick : ((PersonRequest) -> Unit)? = null

    class DashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ImageVievTest)
        val name: TextView = itemView.findViewById(R.id.tvName)
        val desc: TextView = itemView.findViewById(R.id.tvDescription)
        val phone: TextView = itemView.findViewById(R.id.tvPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return DashViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashViewHolder, position: Int) {
        val person = personalList[position]
        holder.imageView.setImageResource(R.drawable.ic_baseline_settings_applications_24)
        holder.name.text = person.name
        holder.desc. text = person.description
        holder.phone.text = "+79029990970"

        holder.imageView.setOnClickListener {
            onItemClick?.invoke(person)
            var intent = Intent(c, DetailDashActivity::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("description", person.description)
            intent.putExtra("address", person.address)
            c.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return personalList.size
    }
}