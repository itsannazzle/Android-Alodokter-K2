package com.nextint.alodokterbykelompok2.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentViewPagerOnBoardingBinding


class ViewPagerOnBoardingFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView(){
        val fragmentList = arrayListOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
        )
        val onBoardingAdapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding.vpOnBoarding.adapter = onBoardingAdapter
        binding.dotsIndicator.setViewPager(binding.vpOnBoarding)
    }

}