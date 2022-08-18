package com.fastcampuskotlin.instragmapp

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


class UserToken(
	val token: String,
	val username: String,
	val userId: Int,
	val error: String
)


interface RetrofitService {

	@POST("user/login/")
	@FormUrlEncoded
	fun login(
		@FieldMap params: HashMap<String, String>
	): Call<UserToken>

	@POST("user/signup/")
	@FormUrlEncoded
	fun join(
		@FieldMap params: HashMap<String, String>
	): Call<UserToken>
}