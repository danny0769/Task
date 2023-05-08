package com.example.task

data class QuizData(val questions: List<Question>)

data class Question(val id: Int, val question: String, val options: List<String>, val answer: String
)