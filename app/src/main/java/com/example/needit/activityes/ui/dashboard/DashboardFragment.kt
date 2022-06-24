package com.example.needit.activityes.ui.dashboard

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.needit.R
import com.example.needit.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

//Stegancev
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val adapter=DashAdapter()

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
          RecycledVievDash.layoutManager= LinearLayoutManager(activity)
          RecycledVievDash.adapter=adapter
          var progressDialog = ProgressDialog(context)  // Окно загрузки данных при ожидании
          try {
              progressDialog.setCanceledOnTouchOutside(false)
              progressDialog.setMessage("Загружаем данные...")
              progressDialog.show() // Показываем окно загрузки
              var personaList = mutableListOf<PersonRequest>()  // Инициализируем лист товаров
              val db = FirebaseFirestore.getInstance()   // Подключение к БД
              db.collection("Stuff")    // Просматриваем все элементы коллекции
                  .get()
                  .addOnSuccessListener {
                          result ->
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
                      for (i in personaList) {  // Добавлем все элементы в DashBoard
                          adapter.addReq(i)
                      }
                  }

          } catch (e:NoSuchElementException){  null }
      }
}