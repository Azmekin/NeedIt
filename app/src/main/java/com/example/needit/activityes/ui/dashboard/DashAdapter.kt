package com.example.needit.activityes.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.needit.R
import com.example.needit.databinding.RecyclerViewItemBinding

//Stegancev
class DashAdapter:RecyclerView.Adapter<DashAdapter.DashHolder>() {
    val requestList=ArrayList<PersonRequest>()
    class DashHolder(item:View):RecyclerView.ViewHolder(item){
        val binding=RecyclerViewItemBinding.bind(item)
        fun bind(personRequest: PersonRequest) = with(binding){
            textView1.text=personRequest.name
            textView2.text=personRequest.description
            ImageVievTest.setImageResource(R.drawable.ic_baseline_settings_applications_24)
            Contacts.text="+7999999999"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashHolder {
val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return DashHolder(view)
    }

    override fun onBindViewHolder(holder: DashHolder, position: Int) {
        holder.bind(requestList[position],)
    }

    override fun getItemCount(): Int {
      return requestList.size
    }
    fun addReq(personRequest: PersonRequest){
        requestList.add(personRequest)
        notifyDataSetChanged()
    }
}