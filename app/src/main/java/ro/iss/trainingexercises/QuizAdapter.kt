package ro.iss.trainingexercises

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.okdroid.checkablechipview.CheckableChipView
import kotlinx.android.synthetic.main.question_item.view.*

class QuizAdapter (private val questionList : ArrayList<Question>, private val context:Context): RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.question_item,parent,false))

    }

    override fun getItemCount(): Int {
        return questionList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionName.text=questionList[position].questionText
        holder.response1.text= questionList[position].answer?.get(0) ?: ""
        holder.response2.text= questionList[position].answer?.get(1) ?: ""
        holder.response3.text= questionList[position].answer?.get(2) ?: ""
        holder.response4.text= questionList[position].answer?.get(3) ?: ""


    }

    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val questionName : TextView = view.question_name
        val response1 : CheckableChipView=view.response1
        val response2 : CheckableChipView=view.response2
        val response3 : CheckableChipView=view.response3
        val response4 : CheckableChipView=view.response4



    }




}