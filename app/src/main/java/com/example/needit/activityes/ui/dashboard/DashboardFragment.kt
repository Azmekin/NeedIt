package com.example.needit.activityes.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.needit.databinding.FragmentDashboardBinding
import com.example.needit.firebase.firestore.PostFirestore
import com.example.needit.firebase.models.Post
import kotlinx.coroutines.withContext
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

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        init1()
        init()
        init()
        init()
        init()
        init()
        init()
        init()
        init()
        init()
        init()
        init()
        return root
    }
      private fun init()= with(binding) {

              RecycledVievDash.layoutManager= LinearLayoutManager(activity)
                RecycledVievDash.adapter=adapter
          try {
              val postFire: PostFirestore = PostFirestore()
              val post: Post =
                  Post(
                      "",
                      "",
                      "",
                      "",
                      "",
                      post_type = Post.PostType.GIVE_AWAY,
                      object_type = Post.ObjectType.LEISURE_GOODS,
                      "",
                      "",
                      Date()
                  )
          } catch (e:NoSuchElementException){null}
                val personRequest=PersonRequest(1,"Yasha","Lava","I need new boots 43 size","Need","Pushkin street Kolotushkin House")
                adapter.addReq(personRequest)

        }
    private fun init1()= with(binding) {

        RecycledVievDash.layoutManager= LinearLayoutManager(activity)
        RecycledVievDash.adapter=adapter
        try {
            val postFire: PostFirestore = PostFirestore()
            val post: Post =
                Post(
                    "",
                    "",
                    "",
                    "",
                    "",
                    post_type = Post.PostType.GIVE_AWAY,
                    object_type = Post.ObjectType.LEISURE_GOODS,
                    "",
                    "",
                    Date()
                )
        } catch (e:NoSuchElementException){null}
        val personRequest=PersonRequest(0,"Alex","Tumbaev","I need a wooden box","Need","Pushkin street Kolotushkin House")
        adapter.addReq(personRequest)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}