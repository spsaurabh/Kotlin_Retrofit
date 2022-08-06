package com.example.kotlin_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responceBody = response.body()!!
                val myStringBuilder = StringBuilder()
                for (myData in responceBody)
                {
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
//                    myStringBuilder.append(myData.body)
//                    myStringBuilder.append(myData.id)
//                    myStringBuilder.append(myData.id)

                }

            }
            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {


            }
        })
    }
}