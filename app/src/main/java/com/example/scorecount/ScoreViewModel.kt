package com.example.scorecount

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class ScoreViewModel : ViewModel() {
    private val _mutableScoreA = MutableLiveData(0)
    val scoreA: LiveData<Int>
    get() = _mutableScoreA

    private val _mutableScoreB = MutableLiveData(0)
    val scoreB: LiveData<Int>
    get() = _mutableScoreB

    var namaTeamA: String = ""
    var namaTeamB: String = ""

    private val maxScore = 10  // Nilai maksimal untuk menang

    // LiveData untuk menyimpan pesan kemenangan
    val resultA: LiveData<String> = _mutableScoreA.map { scoreA ->
        if (scoreA > _mutableScoreB.value!!) {
            if (scoreA >= maxScore) {
                if ((scoreA - _mutableScoreB.value!!) >= 2){
                    "Team $namaTeamA menang"
                }  else ""
            } else ""
        } else ""
    }

    // LiveData untuk menyimpan pesan kemenangan
    val resultB: LiveData<String> = _mutableScoreB.map { scoreB ->
        if (scoreB > _mutableScoreA.value!!) {
            if (scoreB >= maxScore) {
                if ((scoreB - _mutableScoreA.value!!) >= 2){
                    "Team $namaTeamB menang"
                }  else ""
            } else ""
        } else ""
    }

    // Method untuk menambah skor
    fun incrementScore(isTeamA: Boolean, points: Int) {
        if (scoreA.value!! < maxScore && scoreB.value!! < maxScore) {  // Cek jika belum ada yang menang
            if (isTeamA) {
                _mutableScoreA.value = (_mutableScoreA.value ?: 0) + points
            } else {
                _mutableScoreB.value = (_mutableScoreB.value ?: 0) + points
            }
        }
    }

    // Method untuk mereset skor
    fun resetScores() {
        _mutableScoreA.value = 0
        _mutableScoreB.value = 0
    }
}
