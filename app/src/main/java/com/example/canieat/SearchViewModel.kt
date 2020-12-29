package com.example.canieat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canieat.model.SearchItem
import com.example.canieat.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private val repository: Repository):ViewModel() {
    val searchResponse:MutableLiveData<List<SearchItem>> = MutableLiveData()

    fun getItem(searchKeyword:String){
        viewModelScope.launch {
            val response = repository.getItem(searchKeyword,returnType = "json")
            if(response.isSuccessful){
                searchResponse.postValue(response.body()!!.list)
            }
        }
    }
}