package com.example.needit.activityes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.needit.R
import com.example.needit.activityes.ui.dashboard.PersonRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val spinner: Spinner = spinner
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
        button_submit.setOnClickListener {
            button_submit.setText(R.string.Retry1)
            val NeedOrGive: String  // Проверка на свич (Give или Need)
            if (switch1.isChecked) {
                NeedOrGive = "Give"
            } else {
                NeedOrGive = "Need"
            }
            spinner.selectedItem.toString()  //добить в бд надо
            val personalRequest = PersonRequest(1,  // Создаём объект PersonalRequest
                editTextTextPersonName.text.toString(),
                "Null",
                editTextTextMultiLine.text.toString(),
                NeedOrGive,
                "Lenina, 53"
            )
            db.collection("Stuff")      // Добавляем его в БД
                .document(editTextTextPersonName
                    .text.toString())
                .set(personalRequest)


        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }
}