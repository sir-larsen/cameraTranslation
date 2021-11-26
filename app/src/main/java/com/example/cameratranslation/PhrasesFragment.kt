package com.example.cameratranslation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cameratranslation.data.PhrasesDataSource
import com.example.cameratranslation.databinding.FragmentCameraBinding
import com.example.cameratranslation.databinding.FragmentPhrasesBinding
import com.example.cameratranslation.viewmodels.DetailViewModel


class PhrasesFragment : Fragment() {
    private val detViewModel : DetailViewModel by activityViewModels()
    private var binding: FragmentPhrasesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPhrasesBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_camera, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = detViewModel
            lifecycleOwner = viewLifecycleOwner
            phrasesFragment = this@PhrasesFragment
        }
        val act = activity as MainActivity
        //Set click listeners
        binding?.transit?.setOnClickListener {
            var phrases = context?.let { it1 -> PhrasesDataSource.loadTransitPhrases(it1) }
            if (phrases != null) {
                detViewModel.getTransit(phrases)
            }
            for (trans in detViewModel.phrases!!) {
                trans.translatedText = act.translate(trans.originalText).toString()
            }
            goToDetails()
        }
        binding?.Health?.setOnClickListener {
            var phrases = context?.let { it1 -> PhrasesDataSource.loadHealthPhrases(it1) }
            if (phrases != null) {
                detViewModel.getHealth(phrases)
            }
            for (trans in detViewModel.phrases!!) {
                trans.translatedText = act.translate(trans.originalText).toString()
            }
            goToDetails()
        }
        binding?.Dining?.setOnClickListener {
            var phrases = context?.let { it1 -> PhrasesDataSource.loadDiningPhrases(it1) }
            if (phrases != null) {
                detViewModel.getDining(phrases)
            }
            for (trans in detViewModel.phrases!!) {
                trans.translatedText = act.translate(trans.originalText).toString()
            }
            goToDetails()
        }
    }

    fun goToDetails() {
        findNavController().navigate(R.id.action_phrasesFragment_to_listFragment)
    }
}