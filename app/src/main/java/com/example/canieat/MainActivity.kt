package com.example.canieat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.canieat.repository.Repository
import com.example.canieat.utils.Constants.Companion.SERVICE_KEY
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel=ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)

        search_edit_text.setOnClickListener {
            var intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
//        search_btn.setOnClickListener {
//            viewModel.getItem("과자")
//            viewModel.searchResponse.observe(this, Observer { response ->
//                if (response!=null) {
//                    response.forEach {
//                        Log.d("Response:", it.toString())
//                    }
//                } else {
//                    Log.d("Response: ", response.toString())
//                }
//            })
//        }
    }
}