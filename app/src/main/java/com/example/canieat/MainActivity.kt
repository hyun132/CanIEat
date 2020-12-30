package com.example.canieat

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.canieat.repository.Repository
import com.example.canieat.utils.Constants.Companion.SERVICE_KEY
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder
import java.util.*

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
            intent.putExtra("query","")
            startActivity(intent)
        }

        vioce_imave_view.setOnClickListener {
            // Get the Intent action
            val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            // Language model defines the purpose, there are special models for other use cases, like search.
            sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            // Adding an extra language, you can use any language from the Locale class.
            sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            // Text that shows up on the Speech input prompt.
            sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
            try {
                // Start the intent for a result, and pass in our request code.
                startActivityForResult(sttIntent, REQUEST_CODE_STT)
            } catch (e: ActivityNotFoundException) {
                // Handling error when the service is not available.
                e.printStackTrace()
                Toast.makeText(this, "Your device does not support STT.", Toast.LENGTH_LONG).show()
            }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Handle the result for our request code.
            REQUEST_CODE_STT -> {
                // Safety checks to ensure data is available.
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Retrieve the result array.
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    // Ensure result array is not null or empty to avoid errors.
                    if (!result.isNullOrEmpty()) {
                        // Recognized text is in the first position.
                        val recognizedText = result[0]
                        // Do what you want with the recognized text.
//                        et_text_input.setText(recognizedText)
                        var intent=Intent(this,SearchActivity::class.java)
                        intent.putExtra("query",recognizedText)
                        startActivity(intent)
                    }
                }
            }
        }
    }
    companion object{
        private const val REQUEST_CODE_STT = 1
    }
}