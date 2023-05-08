package com.example.task

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context:Context):ViewModel() {
    var quizdata:QuizData?=null
    init {
       quizdata=loadDataFromJson()
    }
    var correctAnsCount:Int = 0 // add this property to keep track of the correct answer count

    fun incrementCorrectAnsCount() {
        correctAnsCount++
    }
    private fun loadDataFromJson(): QuizData? {
        val resourceId = R.raw.data
        val inputStream = context.resources.openRawResource(resourceId)
        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer,Charsets.UTF_8)
        val gson= Gson()
        return gson.fromJson(json, QuizData::class.java)
    }
}