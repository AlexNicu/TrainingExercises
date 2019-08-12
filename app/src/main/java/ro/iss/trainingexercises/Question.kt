package ro.iss.trainingexercises

import com.google.gson.annotations.SerializedName

class Question(
    @SerializedName("question") var questionText : String?,
    @SerializedName("answers") var answer : ArrayList<String>?,
    @SerializedName("questionId") var questionId : Int?) {

}