package com.example.scorecount

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.scorecount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)  // Inisialisasi binding dengan benar
        setContentView(binding.root)

        // Mengambil nama tim dari intent dan menampilkannya di TextView
        val teamAName = intent.getStringExtra("TEAM_A_NAME")
        val teamBName = intent.getStringExtra("TEAM_B_NAME")

        binding.tvTeamA.text = teamAName
        binding.tvTeamB.text = teamBName

        viewModel.namaTeamA = teamAName ?: "Team A"
        viewModel.namaTeamB = teamBName ?: "Team B"

        // Observe resultA dan resultB untuk menampilkan pesan kemenangan
        viewModel.resultA.observe(this) { resultMessageA ->
            if (resultMessageA.isNotEmpty()) {
                binding.tvWinner.text = resultMessageA
                disableButtons(binding)  // Disable tombol ketika ada yang menang
            }
        }

        viewModel.resultB.observe(this) { resultMessageB ->
            if (resultMessageB.isNotEmpty()) {
                binding.tvWinner.text = resultMessageB
                disableButtons(binding)  // Disable tombol ketika ada yang menang
            }
        }

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

        // Reset skor
        binding.btnReset.setOnClickListener {
            viewModel.resetScores()
            enableButtons(binding)  // Enable tombol kembali setelah reset
            binding.tvWinner.text = ""  // Hapus pesan kemenangan
        }
    }

    // Fungsi untuk disable semua tombol
    private fun disableButtons(binding: ActivityMainBinding) {
        binding.btnLeft1.isEnabled = false
        binding.btnLeft2.isEnabled = false
        binding.btnLeft3.isEnabled = false
        binding.btnRight1.isEnabled = false
        binding.btnRight2.isEnabled = false
        binding.btnRight3.isEnabled = false
    }

    // Fungsi untuk enable semua tombol
    private fun enableButtons(binding: ActivityMainBinding) {
        binding.btnLeft1.isEnabled = true
        binding.btnLeft2.isEnabled = true
        binding.btnLeft3.isEnabled = true
        binding.btnRight1.isEnabled = true
        binding.btnRight2.isEnabled = true
        binding.btnRight3.isEnabled = true
    }
}
