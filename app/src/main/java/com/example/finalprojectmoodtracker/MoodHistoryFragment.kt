package com.example.finalprojectmoodtracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectmoodtracker.databinding.FragmentMoodHistoryBinding

class MoodHistoryFragment : Fragment() {

    private var _binding: FragmentMoodHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoodHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoodHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoodHistoryAdapter(emptyList())
        binding.recyclerViewMoodHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMoodHistory.adapter = adapter

        loadMoodHistory()
    }

    private fun loadMoodHistory() {
        val moodEntries = SharedPreferencesHelper.getAllMoodEntries(requireContext())
        adapter.updateEntries(moodEntries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
