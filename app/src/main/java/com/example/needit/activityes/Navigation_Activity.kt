package com.example.needit.activityes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.needit.R
import com.example.needit.activityes.ui.dashboard.DashboardFragment
import com.example.needit.activityes.ui.notifications.NotificationsFragment

import com.example.needit.databinding.ActivityNavigationBinding
import kotlinx.android.synthetic.main.activity_navigation.*

class Navigation_Activity : AppCompatActivity() {

    private val dashboardFragment = DashboardFragment()
    private val notificationsFragment = NotificationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(dashboardFragment)

        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_dashboard -> replaceFragment(dashboardFragment)
                R.id.navigation_notifications -> replaceFragment(notificationsFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment !=null) {
            val transition = supportFragmentManager.beginTransaction()
            transition.replace(R.id.nav_host_fragment_activity_navigation, fragment)
            transition.commit()
        }
    }
}