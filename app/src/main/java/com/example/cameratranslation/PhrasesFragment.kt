package com.example.cameratranslation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cameratranslation.adapters.PhrasesRecyclerAdapter
import com.example.cameratranslation.data.PhrasesDataSource
import com.example.cameratranslation.databinding.FragmentPhrasesBinding

/**
 * Fragment class for [PhrasesFragment]
 */
class PhrasesFragment : Fragment(), PhrasesRecyclerAdapter.OnThemeClickListener {
    private val dataset = PhrasesDataSource(requireContext()).loadPhrase()
    private var adapter = context?.let { PhrasesRecyclerAdapter(it, dataset, this) }
    private var binding: FragmentPhrasesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPhrasesBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            themesRecyclerView.setHasFixedSize(true)
            themesRecyclerView.layoutManager = LinearLayoutManager(view.context)
            themesRecyclerView.adapter = adapter
        }
    }

/*//ViewBinding
    private var _binding: FragmentPhrasesBinding? = null
    private val binding get() = _binding!!

    //Datasource
    private lateinit var phraseData : Map<String, Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Fetch data
        phraseData = PhrasesDataSource(requireContext()).loadPhrase()

        // Inflate the layout for this fragment
        _binding = FragmentPhrasesBinding.inflate(inflater, container, false)
        binding.themesRecyclerView.setHasFixedSize(true)
        binding.themesRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        binding.themesRecyclerView.adapter = PhrasesRecyclerAdapter(phraseData, this)



        return binding.root
    }*/

    override fun onThemeClick(position: Int) {
        val clicked = dataset.keys.elementAt(position)

        Toast.makeText(context, dataset[clicked]?.get(0), Toast.LENGTH_SHORT).show()
    }
}