package com.example.task

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class BlankFragment : Fragment() {
    private lateinit var scoreTextView: TextView
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        scoreTextView = view.findViewById(R.id.fragmentTV_id)
        backButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correctScore = arguments?.getInt("CORRECT_SCORE", 0) ?: 0
        scoreTextView.text = "Your answered $correctScore questions correctly"
    }

    companion object {
        fun newInstance(correctScore: Int): BlankFragment {
            val fragment = BlankFragment()
            val args = Bundle()
            args.putInt("CORRECT_SCORE", correctScore)
            fragment.arguments = args
            return fragment
        }
    }
}
