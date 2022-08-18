package com.fastcampuskotlin.instragmapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fastcampuskotlin.instragmapp.R

/**
 * 런처 액티비티.
 * 로그인이 되어있으면 인스타 피드가 나오고, 안되어있으면 로그인 화면
 */
class InstaSplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_insta_splash)


		val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
		val token = sharedPreferences.getString("token", "")

		Log.d("InstaSplashActivity", "onCreate: $token")

		if (token == null || token.isEmpty()) {
			startActivity(Intent(this@InstaSplashActivity, LoginActivity::class.java))
		} else {
			startActivity(Intent(this@InstaSplashActivity, MainActivity::class.java))

		}


	}
}