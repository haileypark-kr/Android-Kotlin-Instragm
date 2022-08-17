package com.fastcampuskotlin.instragmapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.fastcampuskotlin.instragmapp.R
import com.fastcampuskotlin.instragmapp.RetrofitService
import com.fastcampuskotlin.instragmapp.UserToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

	var userName: String = ""
	var password: String = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)


		val retrofit = Retrofit.Builder()
			.baseUrl("http://mellowcode.org/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		val retrofitService = retrofit.create(RetrofitService::class.java)


		findViewById<EditText>(R.id.id_input).doAfterTextChanged {
			userName = it.toString()
		}

		findViewById<EditText>(R.id.pw_input).doAfterTextChanged {
			password = it.toString()
		}

		findViewById<TextView>(R.id.join_tv).setOnClickListener {
			startActivity(Intent(this@LoginActivity, JoinActivity::class.java))
		}

		findViewById<TextView>(R.id.login_tv).setOnClickListener {
			val requestMap = HashMap<String, String>()
			requestMap.put("username", userName)
			requestMap.put("password", password)

			retrofitService.login(requestMap).enqueue(object : Callback<UserToken> {

				override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
					Log.d("login", "onResponse: $response")
					if (response.isSuccessful && response.body() != null) {
						val token: UserToken = response.body()!!

						Log.d("login", "token: $token")

						if (token.token.isNotEmpty()) {
							// shared preference에 토큰 저장 -> 후에 토큰 여부 확인
							val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
							val editor: SharedPreferences.Editor = sharedPreferences.edit()
							editor.putString("token", token.token)
							editor.commit()
							Toast.makeText(this@LoginActivity, "로그인되었습니다.", Toast.LENGTH_LONG)
								.show()

						}
					}
				}

				override fun onFailure(call: Call<UserToken>, t: Throwable) {
					Log.e("login", "onResponse: $t")
				}

			})

		}


	}
}