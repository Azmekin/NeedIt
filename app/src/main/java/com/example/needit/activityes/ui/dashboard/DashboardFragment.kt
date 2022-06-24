package com.example.needit.activityes.ui.dashboard

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.needit.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

//Stegancev
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter=DashAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()
        return root
    }
      private fun init()= with(binding) {
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
                                  document["ImageID"].toString().toInt(),
                                  document["Name"].toString(),
                                  document["Surname"].toString(),
                                  document["Description"].toString(),
                                  document["typeReq"].toString(),
                                  document["Address"].toString()
                              )
                          )
                          progressDialog.dismiss()  // Убираем окно загрузки
                          for (i in personaList) {  // Добавлем все элементы в DashBoard
                              adapter.addReq(i)
                          }
                      }
                  }

          } catch (e:NoSuchElementException){  null }
      }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}