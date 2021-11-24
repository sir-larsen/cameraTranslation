package com.example.cameratranslation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cameratranslation.databinding.FragmentCameraBinding
import com.example.cameratranslation.viewmodels.CameraViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : Fragment() {
    private val camViewModel: CameraViewModel by activityViewModels()
    private var binding: FragmentCameraBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCameraBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_camera, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = camViewModel
            lifecycleOwner = viewLifecycleOwner
            cameraFragment = this@CameraFragment
        }
        //Set click listeners here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}