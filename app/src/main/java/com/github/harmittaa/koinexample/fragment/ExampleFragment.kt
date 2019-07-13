package com.github.harmittaa.koinexample.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.harmittaa.koinexample.R
import com.github.harmittaa.koinexample.databinding.FragmentViewBinding
import com.github.harmittaa.koinexample.model.Weather
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { ExampleFragment() }
}

class ExampleFragment : Fragment() {
    private val exampleViewModel: ExampleViewModel by viewModel()
    private lateinit var binding: FragmentViewBinding

    @SuppressLint("SetTextI18n")
    private val observer = Observer<Weather> {
        Log.d("ExampleFragment", "Data: $it")
        binding.fragmentInfo.text = "Temperature is ${it.temp.temp}"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view, container, false)
        exampleViewModel.weather.observe(this, observer)
        return binding.root
    }
}