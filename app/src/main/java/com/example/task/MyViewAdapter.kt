package com.example.task

import  android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyViewAdapter(private val quizDataList: List<Question>,private val viewModel: MainViewModel,private val recyclerview:RecyclerView):Adapter<MyViewAdapter.MyViewHolder>(){
    // declare a variable to keep track of the expanded item
    private var expandedPosition = RecyclerView.NO_POSITION
    private var prevPos:Int=-1
    var count:Int=0
    @SuppressLint("MissingInflatedId")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recview_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position:Int) {

            val question = quizDataList[position]
            holder.question.text = question.question
            holder.option1.text = question.options[0]
            holder.option2.text = question.options[1]
            holder.option3.text = question.options[2]
            holder.option4.text = question.options[3]
        // set onClickListener to toggle options visibility
        holder.question.setOnClickListener {
            if (expandedPosition == position) {
                // if the current view is already expanded, collapse it
                holder.option1.visibility = View.GONE
                holder.option2.visibility = View.GONE
                holder.option3.visibility = View.GONE
                holder.option4.visibility = View.GONE
                expandedPosition = RecyclerView.NO_POSITION
            } else {
                //firstly need to collapse the previous item and then expand
                if (prevPos >= 0) {
                    var prevView: MyViewHolder? = recyclerview.findViewHolderForAdapterPosition(prevPos) as? MyViewHolder
                    Log.i("test ", prevView.toString())
                    prevView?.option1?.visibility = View.GONE
                    prevView?.option2?.visibility = View.GONE
                    prevView?.option3?.visibility = View.GONE
                    prevView?.option4?.visibility = View.GONE
                }

                // expand the current view
                holder.option1.visibility = View.VISIBLE
                holder.option2.visibility = View.VISIBLE
                holder.option3.visibility = View.VISIBLE
                holder.option4.visibility = View.VISIBLE
                expandedPosition = position
                prevPos = position
            }
        }

        //for options clicked logic:
        //deleted this thing
        //checking user responses
        holder.option1.setOnClickListener{
            val selectedOption = holder.option1.text.toString()
            val correctOption = quizDataList[holder.adapterPosition].answer.toString()

            if(selectedOption == correctOption){
                viewModel.incrementCorrectAnsCount()
                holder.option1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            }
            else{
                holder.option1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
                when(correctOption){
                    holder.option2.text.toString() -> holder.option2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option3.text.toString() -> holder.option3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option4.text.toString() -> holder.option4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                }
            }

        }
        //option 2 selected:
        holder.option2.setOnClickListener{
            val selectedOption = holder.option2.text.toString()
            val correctOption = quizDataList[holder.adapterPosition].answer.toString()

            if(selectedOption == correctOption){
                viewModel.incrementCorrectAnsCount()
                holder.option2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            }
            else{
                holder.option2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
                when(correctOption){
                    holder.option1.text.toString() -> holder.option1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option3.text.toString() -> holder.option3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option4.text.toString() -> holder.option4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                }
            }
        }
        //option -3:
        holder.option3.setOnClickListener{
            val selectedOption = holder.option3.text.toString()
            val correctOption = quizDataList[holder.adapterPosition].answer.toString()

            if(selectedOption == correctOption){
                viewModel.incrementCorrectAnsCount()
                holder.option3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            }
            else{
                holder.option3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
                when(correctOption){
                    holder.option2.text.toString() -> holder.option2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option1.text.toString() -> holder.option1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option4.text.toString() -> holder.option4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                }
            }
        }
        //option -4:
        holder.option4.setOnClickListener{
            val selectedOption = holder.option4.text.toString()
            val correctOption = quizDataList[holder.adapterPosition].answer.toString()

            if(selectedOption == correctOption){
                viewModel.incrementCorrectAnsCount()
                holder.option4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            }
            else{
                holder.option4.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
                when(correctOption){
                    holder.option2.text.toString() -> holder.option2.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option3.text.toString() -> holder.option3.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    holder.option1.text.toString() -> holder.option1.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return quizDataList.size
    }
    class MyViewHolder(itemView: View):ViewHolder(itemView){
        val question: TextView = itemView.findViewById(R.id.question)
        val option1: TextView = itemView.findViewById(R.id.option1)
        val option2: TextView = itemView.findViewById(R.id.option2)
        val option3: TextView = itemView.findViewById(R.id.option3)
        val option4: TextView = itemView.findViewById(R.id.option4)
    }

}
