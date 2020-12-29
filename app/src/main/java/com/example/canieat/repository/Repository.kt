package com.example.canieat.repository

import com.example.canieat.api.AllergyApi
import com.example.canieat.api.RetrofitInstance
import com.example.canieat.model.SearchItem
import com.example.canieat.model.SearchResponse
import retrofit2.Response

class Repository {
    suspend fun getItem(prdlstNm:String,returnType:String):Response<SearchResponse>{
        return RetrofitInstance.api.getItem(prdlstNm = prdlstNm,returnType = "json")
    }
}