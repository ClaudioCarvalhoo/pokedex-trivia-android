package com.example.pokequizz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokequizz.R
import kotlinx.android.synthetic.main.activity_submit.name_text
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.pokequizz.apiHelper.RetrofitFacade
import com.example.pokequizz.apiHelper.entities.Answer
import com.example.pokequizz.apiHelper.entities.SubmitRequest
import kotlinx.android.synthetic.main.activity_submit.confirm_submit_bttn
import kotlinx.android.synthetic.main.activity_submit.find_another_room_bttn
import kotlinx.android.synthetic.main.activity_submit.submit_content
import kotlinx.android.synthetic.main.activity_submit.submit_loading
import kotlinx.android.synthetic.main.activity_submit.submitted_text
import kotlinx.android.synthetic.main.questions_activity.submit_bttn
import okhttp3.ResponseBody
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Submit : AppCompatActivity() {

    private lateinit var roomId : String
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        val bundle = intent.extras
        val answers = bundle?.getSerializable("answers") as List<Answer>
        roomId = bundle?.getString("roomId").toString()

        setTextListener()
        setButtonListener(answers)
    }

    private fun setTextListener() {
        name_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                confirm_submit_bttn.isEnabled = true
                if (name_text.text.isEmpty()) {
                    confirm_submit_bttn.isEnabled = false
                }
            }
        })
    }

    private fun setButtonListener(answers: List<Answer>) {
        confirm_submit_bttn.setOnClickListener {
            submit_loading.visibility = View.VISIBLE

            username = name_text.text.toString()
            val submitRequest = SubmitRequest(username, answers)

            val call = RetrofitFacade.retrofit.submitAnswers(roomId, submitRequest)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>?,
                    response: Response<ResponseBody>?
                ) {
                    response?.body()?.let {
                        submit_loading.visibility = View.INVISIBLE
                        setEmptyStateView()
                        goToRoomInfoActivity()
                    }
                }

                override fun onFailure(
                    call: Call<ResponseBody>?,
                    t: Throwable
                ) {
                    submit_content.visibility = View.VISIBLE
                    submit_loading.visibility = View.INVISIBLE
                    longToast(t.message.toString())
                }
            })
        }
    }

    private fun goToRoomInfoActivity() {
        val intent = Intent(this, RoomInfo::class.java)
        val bundle = Bundle()
        bundle.putString("roomId", roomId)
        bundle.putString("message", "Nice, ${username}! Check the leaderboard to see your score!")
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun setEmptyStateView() {
        submitted_text.visibility = View.VISIBLE
        find_another_room_bttn.visibility = View.VISIBLE
        confirm_submit_bttn?.visibility = View.GONE

        find_another_room_bttn.setOnClickListener {
            val intent = Intent(this, FindRoom::class.java)
            startActivity(intent)
        }
    }
}
