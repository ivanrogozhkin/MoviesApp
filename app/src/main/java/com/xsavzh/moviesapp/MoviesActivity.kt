package com.xsavzh.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MAIN_ACTIVITY"

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

        val apiInterface = ApiInterface.create().getMovies()
        apiInterface.enqueue(object : Callback<TestDataClass> {
            override fun onResponse(call: Call<TestDataClass>?, response: Response<TestDataClass>?) {
                Log.d(TAG, "onResponse success ${response?.body()?.data?.first_name}")
//                if (response?.body() != null)
//                   recyclerAdapter.setMovieListItem(response.body()!!)
            }

            override fun onFailure(call: Call<TestDataClass>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t?.message}")
            }
        })
    }
}