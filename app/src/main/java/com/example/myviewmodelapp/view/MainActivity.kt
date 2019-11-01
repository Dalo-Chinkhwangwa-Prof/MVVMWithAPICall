package com.example.myviewmodelapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.myviewmodelapp.R
import com.example.myviewmodelapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        main_view_pager.adapter = CustomFragmentPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        main_view_pager.addOnPageChangeListener(this)

        bottom_nav_view.setOnNavigationItemSelectedListener { viewItem ->

            when (viewItem.itemId) {
                R.id.person_item -> {
                    this.onPageSelected(0)
                }
                else -> {
                    this.onPageSelected(1)
                }
            }
            true
        }

        search_button.setOnClickListener { _ ->
            search_edittext.text.let { searchName ->
//                viewModel.getGitRepositories(searchName.toString())
//                viewModel.getGitUser(searchName.toString())
                (supportFragmentManager.fragments.get(0) as GitFragment).makeCalls(searchName.toString())
                (supportFragmentManager.fragments.get(1) as GitFragment).makeCalls(searchName.toString())
                search_edittext.text.clear()
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        bottom_nav_view.selectedItemId = position
    }

    override fun onPageSelected(position: Int) {
        bottom_nav_view.selectedItemId = position
    }

}
