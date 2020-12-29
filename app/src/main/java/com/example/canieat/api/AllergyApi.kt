package com.example.canieat.api

import com.example.canieat.model.SearchItem
import com.example.canieat.model.SearchResponse
import com.example.canieat.utils.Constants.Companion.BASE_URL
import com.example.canieat.utils.Constants.Companion.SEARCH_ITEM
import com.example.canieat.utils.Constants.Companion.SERVICE_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URLEncoder

interface AllergyApi {

    @GET("getCertImgListService")
    suspend fun getItem(
        @Query("serviceKey",encoded = true) serviceKey:String="lfEeqXGixAkw5T3ayDXJAh7IxSRht3gDWrIHhL5V7fRDn8ODOz9fYd%2Bpe4cmGh6qf5U4hW%2Bh51MK%2FaL4ngwetQ%3D%3D",
        @Query("prdlstNm")prdlstNm:String="과자",
        @Query("returnType")returnType: String="json"
    ): Response<SearchResponse>


}