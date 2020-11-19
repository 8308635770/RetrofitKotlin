package com.example.retrofitkotlin

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceholderApi {


    @GET("/posts")
    fun getPosts(@Query("userId") userid:Int,
                @Query("_sort") sort:String,
                @Query("_order") order:String):Call<List<Posts>>

    @GET("/posts")
    fun getPosts(@QueryMap para:Map<String,String>):Call<List<Posts>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId:Int):Call<List<Comments>>

    @POST("/posts")
    fun createPost(@Body post:Posts):Call<Posts>

    @FormUrlEncoded
    @POST("/posts")
    fun createPost(@FieldMap para: Map<String, String>):Call<Posts>




}