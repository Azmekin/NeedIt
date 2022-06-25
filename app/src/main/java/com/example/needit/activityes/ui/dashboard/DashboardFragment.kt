package com.example.needit.activityes.ui.dashboard

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.needit.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*
import kotlin.collections.ArrayList

//Stegancev
class DashboardFragment : Fragment() {

    private lateinit var personaList : ArrayList<PersonRequest>
    private lateinit var personAdapter: DashAdapter
    private var check = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

      private fun init() {
          recycledVievDash.layoutManager = LinearLayoutManager(context)
          var progressDialog = ProgressDialog(context)  // Окно загрузки данных при ожидании
          try {
              progressDialog.setCanceledOnTouchOutside(false)
              progressDialog.setMessage("Загружаем данные...")
              progressDialog.show() // Показываем окно загрузки
              val db = FirebaseFirestore.getInstance()   // Подключение к БД
              db.collection("Stuff")    // Просматриваем все элементы коллекции
                  .get()
                  .addOnSuccessListener {
                          result ->
                      personaList = ArrayList()
                      for (document in result) {
                          personaList.add(
                              PersonRequest(    // Из БД инициализируем список объектов PersonalRequest
                                  document["imageID"].toString().toInt(),
                                  document["name"].toString(),
                                  document["surname"].toString(),
                                  document["description"].toString(),
                                  document["typeReq"].toString(),
                                  document["address"].toString()
                              )
                          )
                      }
                      progressDialog.dismiss()  // Убираем окно загрузки
                      personAdapter = context?.let { DashAdapter(it, personaList) }!!
                      recycledVievDash.adapter = personAdapter
                      check = true
                  }

          } catch (e:NoSuchElementException){  null }
      }

    companion object {

        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}