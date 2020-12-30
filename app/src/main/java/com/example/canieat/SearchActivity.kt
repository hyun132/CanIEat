package com.example.canieat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canieat.adapter.MainAdapter
import com.example.canieat.model.SearchItem
import com.example.canieat.repository.Repository
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var viewModel:SearchViewModel
    lateinit var repository: Repository

    lateinit var speak_to_Text:String

    val adapter=MainAdapter()
//    var currentList= listOf<SearchItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar=toolbar
        setSupportActionBar(toolbar)

        repository= Repository()
        viewModel= SearchViewModel(repository)

        search_recycler_view.adapter=adapter
        search_recycler_view.layoutManager=LinearLayoutManager(this)
        search_recycler_view.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        var intent=intent
        speak_to_Text=intent.getStringExtra("query")!!.toString()

        toolbar_search_view.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (speak_to_Text!=""){
                    viewModel.getItem(speak_to_Text)
                    speak_to_Text=""
                }else viewModel.getItem(newText.toString().trim())
                return true
            }

        })

        viewModel.searchResponse.observe(this, { list->
            adapter.differ.submitList(list)
//            currentList=list
        })


//        viewModel.getItem()

        adapter.setOnItemClickListener {
            val intent= Intent(this@SearchActivity,DetailActivity::class.java)
            intent.putExtra("itemInfo",it)
            startActivity(intent)
        }
    }

//    override fun onResume() {
//        super.onResume()
////        toolbar_search_view.setQuery(speak_to_Text,true)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu,menu)

        toolbar_search_view.onActionViewExpanded()
        toolbar_search_view.clearFocus()

        return super.onCreateOptionsMenu(menu)
    }
}