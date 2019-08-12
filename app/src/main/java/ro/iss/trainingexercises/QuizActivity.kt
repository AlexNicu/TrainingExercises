package ro.iss.trainingexercises

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var quizAdapter : QuizAdapter
    private lateinit var observableGetQuestions: Observable<ArrayList<Question>>
    private lateinit var apiCallDisposableGetQuestions : Disposable
    private lateinit var loadingDialog : ProgressDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        createLoadingDialog()

        configurationToolbar()
        getQuestionList()


    }

    private fun createLoadingDialog(){
        loadingDialog=ProgressDialog(this)
        loadingDialog.setTitle("Loading...")
        //.Builder(this).setMessage("Loading...").create()

    }

    private fun showLoadingDialog(){
       loadingDialog.show()
    }

    private fun dismissLoadingDialog(){
        loadingDialog.dismiss()
    }

    private fun configurationToolbar(){
        quiz_toolbar.title="Sir, would you please respond"
        setSupportActionBar(quiz_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun getQuestionList(){

        showLoadingDialog()
        observableGetQuestions=RetrofitCall.getQuestions()
        apiCallDisposableGetQuestions = observableGetQuestions.subscribe(
            { response -> setQuestionList(response) },
            { setErrorMessage() },
            { showLoadingDialog()
                apiCallDisposableGetQuestions.dispose() }

        )


    }

    private fun setQuestionList( questions: ArrayList<Question>){
        views_list.visibility=View.VISIBLE
        error_text_message.visibility=View.GONE


        quizAdapter = QuizAdapter(questions,this)
        views_list.adapter=quizAdapter
        views_list.layoutManager= LinearLayoutManager(this)


    }

    private fun setErrorMessage(){
        views_list.visibility= View.GONE
        error_text_message.visibility=View.VISIBLE

    }

}
