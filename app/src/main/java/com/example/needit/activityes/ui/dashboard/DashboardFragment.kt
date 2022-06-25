package com.example.needit.activityes.ui.dashboard

import android.app.ProgressDialog
import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.needit.R
import com.example.needit.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.recycler_view_item.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import java.util.*

//Stegancev
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val adapter=DashAdapter(object : onClickLis{
        override fun onClikeded(id_num: Int) {
            Toast.makeText(getActivity(),
                id_num.toString(), Toast.LENGTH_LONG).show();

      //      button.visibility= View.VISIBLE
            if (RecycledVievDash.findViewHolderForAdapterPosition(id_num)?.itemView?.button?.visibility== View.VISIBLE)
            RecycledVievDash.findViewHolderForAdapterPosition(id_num)?.itemView?.button?.visibility= View.GONE
            else
                RecycledVievDash.findViewHolderForAdapterPosition(id_num)?.itemView?.button?.visibility= View.VISIBLE
        }

    })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = spinner2
// Create an ArrayAdapter using the string array and a default spinner layout
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_moment,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }
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