package com.example.retrofitkotlin

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var repository: Repository? = null
    private var api: JsonPlaceholderApi


    fun getInstance(): Repository? {
        if (repository == null) {
            repository = Repository()
        }
        return repository
    }

    init {
        api=RetrofitInstance.getRetrofit().create(JsonPlaceholderApi::class.java)
    }

    fun getComments():MutableLiveData<List<Comments>> {
        val comments=MutableLiveData<List<Comments>>()
        val call = api.getComments(2)


        call.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                comments.value=response.body();
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                comments.value=null
            }

        })

        return comments;
    }





}