package com.example.needit.activityes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.needit.R
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
        /*lateinit var notificationsViewModel: NotificationsViewModel
        notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]*/
        button_submit.setOnClickListener {
            button_submit.setText(R.string.Retry1)
            editTextTextMultiLine.visibility = View.GONE
            editTextTextPersonName.visibility = View.GONE
            switch1.visibility= View.GONE
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }
}