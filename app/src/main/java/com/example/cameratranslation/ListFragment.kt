package com.example.cameratranslation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cameratranslation.adapter.ItemAdapter
import com.example.cameratranslation.databinding.FragmentListBinding
import com.example.cameratranslation.viewmodels.DetailViewModel

class ListFragment : Fragment() {
    private val detViewModel: DetailViewModel by activityViewModels()
    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentListBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = detViewModel.phrases?.let { ItemAdapter(it) }
        }
    }
}