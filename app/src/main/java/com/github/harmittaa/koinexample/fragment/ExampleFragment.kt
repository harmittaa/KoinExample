package com.github.harmittaa.koinexample.fragment

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
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { ExampleFragment() }
}

class ExampleFragment : Fragment() {
    private val exampleViewModel: ExampleViewModel by viewModel()

    private val observer = Observer<String> {
        Log.d("ExampleFragment", "Data: $it")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view, container, false)
        exampleViewModel.fragmentContent.observe(this, observer)
        return binding.root
    }
}