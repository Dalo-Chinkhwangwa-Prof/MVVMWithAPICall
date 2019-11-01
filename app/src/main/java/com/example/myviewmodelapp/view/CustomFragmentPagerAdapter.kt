package com.example.myviewmodelapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomFragmentPagerAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fragmentManager, behavior) {
    override fun getItem(position: Int): Fragment {
        return GitFragment.provideFragment(position)
    }

    override fun getCount(): Int {
        return GitFragment.fragmentCount
    }

}