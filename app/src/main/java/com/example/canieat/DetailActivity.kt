package com.example.canieat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.canieat.model.SearchItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var curItem:SearchItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent=intent

        curItem= intent.getSerializableExtra("itemInfo") as SearchItem

        Glide.with(this).load(curItem.imgurl1).into(item_image_view)
        rawmtrl.text=curItem.rawmtrl
        allergiy.text=curItem.allergy
    }
}