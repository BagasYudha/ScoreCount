package com.example.scorecount

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.scorecount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil nama tim dari intent dan menampilkannya di TextView
        val teamAName = intent.getStringExtra("TEAM_A_NAME")
        val teamBName = intent.getStringExtra("TEAM_B_NAME")

        binding.tvTeamA.text = teamAName
        binding.tvTeamB.text = teamBName

        viewModel.namaTeamA = teamAName ?: "Team A"
        viewModel.namaTeamB = teamBName ?: "Team B"

        // Observe resultA untuk menampilkan pesan kemenangan
        viewModel.resultA.observe(this) { resultMessageA ->
            if (resultMessageA.isNotEmpty()) {
                binding.appName.text = resultMessageA
                binding.appName.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                binding.tvScoreB.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                binding.tvScoreA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 160f)
                disableButtons(binding)
            }
        }

        viewModel.resultB.observe(this) { resultMessageB ->
            if (resultMessageB.isNotEmpty()) {
                binding.appName.text = resultMessageB
                binding.appName.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
                binding.tvScoreA.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                binding.tvScoreB.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 160f)
                disableButtons(binding)
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
            enableButtons(binding)
            binding.appName.text = "SCORECOUNT"
            binding.appName.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    // Fungsi untuk disable semua tombol
    private fun disableButtons(binding: ActivityMainBinding) {
        binding.btnLeft1.visibility = View.GONE
        binding.btnLeft2.visibility = View.GONE
        binding.btnLeft3.visibility = View.GONE
        binding.btnRight1.visibility = View.GONE
        binding.btnRight2.visibility = View.GONE
        binding.btnRight3.visibility = View.GONE
    }

    // Fungsi untuk enable semua tombol
    private fun enableButtons(binding: ActivityMainBinding) {
        binding.btnLeft1.visibility = View.VISIBLE
        binding.btnLeft2.visibility = View.VISIBLE
        binding.btnLeft3.visibility = View.VISIBLE
        binding.btnRight1.visibility = View.VISIBLE
        binding.btnRight2.visibility = View.VISIBLE
        binding.btnRight3.visibility = View.VISIBLE
    }
}
