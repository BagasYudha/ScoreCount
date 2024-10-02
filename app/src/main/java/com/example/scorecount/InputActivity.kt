package com.example.scorecount

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.scorecount.databinding.ActivityInputBinding
import com.google.android.material.snackbar.Snackbar

class InputActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengikat ViewModel ke UI
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Logika untuk Input Team dan mengirimkan ke MainActivity
        binding.btnRename.setOnClickListener{
            val teamA = binding.inputTeamA.text.toString()
            val teamB = binding.inputTeamB.text.toString()

            if (teamA.isNotEmpty() && teamB.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("TEAM_A_NAME", teamA)
                intent.putExtra("TEAM_B_NAME", teamB)

                startActivity(intent)
            } else {
                // Menampilkan Snackbar jika nama tim kosong
                val warningMessage = when {
                    teamA.isEmpty() && teamB.isEmpty() -> "Nama Tim A dan Tim B tidak boleh kosong!"
                    teamA.isEmpty() -> "Nama Tim A tidak boleh kosong!"
                    else -> "Nama Tim B tidak boleh kosong!"
                }

                Snackbar.make(binding.root, warningMessage, Snackbar.LENGTH_LONG)
                    .setAction("Tutup") {
                    }
                    .setTextColor(resources.getColor(android.R.color.white, null))
                    .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                    .show()
            }
        }

    }
}