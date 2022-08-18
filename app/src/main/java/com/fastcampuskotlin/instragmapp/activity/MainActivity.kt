package com.fastcampuskotlin.instragmapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fastcampuskotlin.instragmapp.R
import com.fastcampuskotlin.instragmapp.fragment.FeedFragment
import com.fastcampuskotlin.instragmapp.fragment.PostFragment
import com.fastcampuskotlin.instragmapp.fragment.ProfileFragment
import com.google.android.material.tabs.TabLayout

/**
 * 인스타 피드 액티비티
 */
class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val tabs = findViewById<TabLayout>(R.id.main_tab)
		tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_home))
		tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_post))
		tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_my))


		val pager = findViewById<ViewPager2>(R.id.main_pager)
		pager.adapter = MainPagerAdapter(this@MainActivity, 3)

		tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

			override fun onTabSelected(tab: TabLayout.Tab?) {
				pager.setCurrentItem(tab!!.position)
			}

			override fun onTabUnselected(tab: TabLayout.Tab?) {
				Log.d("MainActivity", "onTabUnselected: $tab")
			}

			override fun onTabReselected(tab: TabLayout.Tab?) {
				Log.d("MainActivity", "onTabReselected: $tab")
			}

		})
	}
}


class MainPagerAdapter(
	fragmentActivity: FragmentActivity,
	val tabCount: Int
) : FragmentStateAdapter(fragmentActivity) {


	override fun getItemCount(): Int {
		return tabCount;
	}

	override fun createFragment(position: Int): Fragment {
		return when (position) {
			0 -> FeedFragment()
			1 -> PostFragment()
			else -> ProfileFragment()
		}
	}

}