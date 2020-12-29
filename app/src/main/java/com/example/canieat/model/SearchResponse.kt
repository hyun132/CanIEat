package com.example.canieat.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val list: List<SearchItem>,
    val numOfRows: String,
    val pageNo: String,
    val resultCode: String,
    val resultMessage: String,
    val totalCount: String
)