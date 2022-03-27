package com.example.needit.activityes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.needit.R
import com.example.needit.databinding.FragmentNotificationsBinding
import com.example.needit.firebase.firestore.PostFirestore
import com.example.needit.firebase.models.Post
import java.util.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
var tr:Boolean=true
     //   val textView: TextView = binding.textNotifications
     //   notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
      //      textView.text = it
      //  }
        //  )
        val but = binding.button1
        val text1=binding.editTextTextMultiLine
        val text2=binding.editTextTextPersonName
        val switch1=binding.switch1
        but.setOnClickListener {
                if (tr) {
                    but.setText(R.string.Retry1)
                    text1.visibility = View.GONE
                    text2.visibility = View.GONE
                    switch1.visibility= View.GONE
        tr=false

   //                 try {
   //                     var post:Post  = Post(text1.text.toString(),"","","Barnaul","+7999999999",Post.PostType.REQUIRE,Post.ObjectType.LEISURE_GOODS,"","", Date())
   //                     if (switch1.isActivated){
    //                        post.post_type=Post.PostType.GIVE_AWAY
    //                    }
   //             val send:PostFirestore = PostFirestore()
   //             send.set(post)}catch (e:NoSuchElementException){null}
            } else{
                text1.visibility = View.VISIBLE
                text2.visibility = View.VISIBLE
                switch1.visibility= View.VISIBLE
                but.setText(R.string.Submit)
            tr=true}
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}