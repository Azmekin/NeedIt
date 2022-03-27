package com.example.needit.activityes.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.needit.R
import com.example.needit.activityes.ui.dashboard.PersonRequest
import com.example.needit.databinding.ActivityLoginBinding
import com.example.needit.databinding.RecyclerViewItemBinding


class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    val loginList=ArrayList<logindata>()
    class HomeHolder(item: View):RecyclerView.ViewHolder(item) {
        private val binding= ActivityLoginBinding.bind(item)

        fun bind(Logindata: logindata) = with(binding){
            username.setHint(Logindata.email)
            password.setHint(Logindata.password)
            login.setText(R.string.LoginButton)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_login,parent,false)
        return HomeHolder(view)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(loginList[position],)
    }

    override fun getItemCount(): Int {
        return loginList.size
    }
    fun addLog(Logindata:logindata){
        loginList.add(Logindata)
        notifyDataSetChanged()
    }
}