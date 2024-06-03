package com.example.finalprojectmoodtracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalprojectmoodtracker.databinding.FragmentLogMoodBinding
import java.util.Calendar


class LogMoodFragment : Fragment() {

    private var _binding: FragmentLogMoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            val mood = binding.editTextMood.text.toString()
            val note = binding.editTextNote.text.toString()
            val timestamp = System.currentTimeMillis()

            if (mood.isNotEmpty()) {
                val moodEntry = SharedPreferencesHelper.MoodEntry(mood, note, timestamp)
                SharedPreferencesHelper.saveMoodEntry(requireContext(), moodEntry)

                Toast.makeText(requireContext(), "Mood logged successfully!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Please enter a mood.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formatDate(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
