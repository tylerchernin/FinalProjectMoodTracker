package com.example.finalprojectmoodtracker

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalprojectmoodtracker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonLogMood.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_logMoodFragment)
        }
        binding.buttonViewTrends.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moodHistoryFragment)
        }
        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }
        binding.buttonPlayMusic.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.soothing)
            }
            mediaPlayer?.start()
            }

        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
