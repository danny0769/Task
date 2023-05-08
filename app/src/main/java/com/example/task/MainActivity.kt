package com.example.task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recyclerv:RecyclerView?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Hide the action bar
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        recyclerv=findViewById(R.id.rvId)
        val layoutManager = LinearLayoutManager(this)
        recyclerv?.layoutManager = layoutManager
        var viewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        var quizData = viewModel.quizdata // assuming quizData is a property in your view model
        //val adapter = quizData?.let { MyViewAdapter(it.questions,viewModel) }
        if (quizData != null) {
            recyclerv?.adapter = MyViewAdapter(quizData.questions,viewModel,recyclerv!!)
        }

        //Toast.makeText(this, "correct ans - "+correct_ans, Toast.LENGTH_LONG).show();
        var ButtonView:Button=findViewById(R.id.ButtonV)
        ButtonView.setOnClickListener {

            val correct_ans = viewModel.correctAnsCount
            val resultFragment = BlankFragment.newInstance(correct_ans)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)
                .commit()
        }

    }


}