package com.example.retrofitkotlin

import android.graphics.Color
import android.os.Bundle
import android.util.AndroidException
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView;
    lateinit var jsonPlaceholderApi: JsonPlaceholderApi
    lateinit var mainActivityViewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel=ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        textView=findViewById(R.id.text_view_result)


         jsonPlaceholderApi=RetrofitInstance.getRetrofit().create(JsonPlaceholderApi::class.java);

//        getPosts()
//        getComments()
//        getCommentsViewModel()
//        createPost()

    }

    private fun getCommentsViewModel() {
        mainActivityViewModel.getComments().observe(this, Observer {

            val comments=it
            var responseString:String=""
            for (comment in comments!!) {

                var content:String=""
                content += "ID: "+comment.id+"\n"
                content += "Post Id: "+comment.postId+"\n"
                content += "Name: "+comment.name+"\n"
                content += "Email: "+comment.email+"\n"
                content += "Body: "+comment.body+"\n\n"

                responseString=responseString+content
            }
            textView.setText(responseString)
        })

    }

    private fun createPost() {
//        val post=Posts(23,101,"New Post","New Post")
        val parameters=HashMap<String,String>()
        parameters.put("userId","1000")
        parameters.put("title","New post title")

        val call=jsonPlaceholderApi.createPost(parameters)

        call.enqueue(object :Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {

                val post=response.body()
                var content:String=response.code().toString()+"\n";
                content += "ID: "+ post!!.id+"\n"
                content += "User Id: "+post.userId+"\n"
                content += "Title: "+post.title+"\n"
                content += "Body: "+post.body+"\n\n"
                textView.setText(content)

            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
            }
        })
    }

    private fun getPosts() {
        val para=HashMap<String,String>()
        para.put("userId","4")
        para.put("_sort","id")
        para.put("_order","desc")

//        val call = jsonPlaceholderApi.getPosts(2,"id","desc")
          val call=jsonPlaceholderApi.getPosts(para)

        call.enqueue(object : Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                textView.setText(response.code().toString())
                var responseString:String=response.code().toString()+"\n"

                val posts = response.body()
                for (post in posts!!) {

                    var content:String=""
                    content += "ID: "+post.id+"\n"
                    content += "User Id: "+post.userId+"\n"
                    content += "Title: "+post.title+"\n"
                    content += "Body: "+post.body+"\n\n"

                    responseString=responseString+content
                }
                textView.setText(responseString)
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                textView.setText(t.message)
            }
        })
    }

    private fun getComments() {

        val call=jsonPlaceholderApi.getComments(100)

        call.enqueue(object :Callback<List<Comments>>{
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                var responseString:String=response.code().toString()+"\n"

                val comments = response.body()
                for (comment in comments!!) {

                    var content:String=""
                    content += "ID: "+comment.id+"\n"
                    content += "Post Id: "+comment.postId+"\n"
                    content += "Name: "+comment.name+"\n"
                    content += "Email: "+comment.email+"\n"
                    content += "Body: "+comment.body+"\n\n"

                    responseString=responseString+content
                }
                textView.setText(responseString)
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
            }
        })
    }
}