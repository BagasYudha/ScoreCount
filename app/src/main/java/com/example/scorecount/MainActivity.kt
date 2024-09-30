package com.example.scorecount

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.scorecount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewModel
    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan View Binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengikat ViewModel ke UI
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Set observer untuk LiveData dari scoreA dan scoreB
        viewModel.scoreA.observe(this) { newValue ->
            binding.tvScoreA.text = newValue.toString()
        }

        viewModel.scoreB.observe(this) { newValue ->
            binding.tvScoreB.text = newValue.toString()
        }

        // Set click listener untuk tombol tim A
        binding.btnLeft1.setOnClickListener {
            viewModel.incrementScore(true, 1)
        }

        binding.btnLeft2.setOnClickListener {
            viewModel.incrementScore(true, 2)
        }

        binding.btnLeft3.setOnClickListener {
            viewModel.incrementScore(true, 3)
        }

        // Set click listener untuk tombol tim B
        binding.btnRight1.setOnClickListener {
            viewModel.incrementScore(false, 1)
        }

        binding.btnRight2.setOnClickListener {
            viewModel.incrementScore(false, 2)
        }

        binding.btnRight3.setOnClickListener {
            viewModel.incrementScore(false, 3)
        }

        // Logika untuk Input Team
        binding.btnRename.setOnClickListener{
            val teamA = binding.inputTeamA.text.toString()
            val teamB = binding.inputTeamB.text.toString()

            if (teamA.isNotEmpty()){
                binding.tvTeamA.text = teamA
            }

            if (teamB.isNotEmpty()){
                binding.tvTeamB.text = teamB
            }
        }

        // Reset skor
        binding.btnReset.setOnClickListener {
            viewModel.resetScores()
        }
    }
}
