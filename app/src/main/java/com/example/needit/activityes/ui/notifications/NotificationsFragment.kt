package com.example.needit.activityes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.needit.R
import com.example.needit.activityes.ui.dashboard.PersonRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
        button_submit.setOnClickListener {
            val NeedOrGive: String  // Проверка на свич (Give или Need)
            if (switch1.isChecked) {
                NeedOrGive = "Give"
            } else {
                NeedOrGive = "Need"
            }

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
            Toast.makeText(activity, "Заявка создана", Toast.LENGTH_SHORT).show()
            editTextTextMultiLine.setText("")
            editTextTextPersonName.setText("")
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }
}