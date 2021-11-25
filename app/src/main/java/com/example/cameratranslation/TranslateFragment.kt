package com.example.cameratranslation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cameratranslation.databinding.FragmentCameraBinding
import com.example.cameratranslation.databinding.FragmentTranslateBinding
import com.example.cameratranslation.viewmodels.CameraViewModel

/**
 * A simple [Fragment] subclass.
 */
class TranslateFragment : Fragment() {
    private val transViewModel: CameraViewModel by activityViewModels() //ViewModel for translations
    private var binding: FragmentTranslateBinding? = null //Databinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentTranslateBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_translate, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = transViewModel
            lifecycleOwner = viewLifecycleOwner
            translateFragment = this@TranslateFragment
        }
        //Set click listeners
    }

    fun translate() {
        val act = activity as MainActivity
        val translatedText = transViewModel.stringToTrans2.value?.let { it1 -> act.translate(it1) }
        transViewModel.translatedText2.value = translatedText
    }
}