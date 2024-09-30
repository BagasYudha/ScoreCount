package com.example.scorecount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _scoreA = MutableLiveData(0)
    val scoreA: LiveData<Int> get() = _scoreA

    private val _scoreB = MutableLiveData(0)
    val scoreB: LiveData<Int> get() = _scoreB

    // Method untuk menambah skor
    fun incrementScore(isTeamA: Boolean, points: Int) {
        if (isTeamA) {
            _scoreA.value = (_scoreA.value ?: 0) + points
        } else {
            _scoreB.value = (_scoreB.value ?: 0) + points
        }
    }

    // Method untuk mereset skor
    fun resetScores() {
        _scoreA.value = 0
        _scoreB.value = 0
    }
}