package com.example.retrofitkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {

     var  repository: Repository? =null
     private var comments=MutableLiveData<List<Comments>>()

    init {

        repository= Repository().getInstance()!!
        comments= repository!!.getComments()

    }
    fun getComments():MutableLiveData<List<Comments>>{
        return comments

    }

}