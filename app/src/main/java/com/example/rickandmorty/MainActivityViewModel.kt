package com.example.rickandmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.paging.RickNMortyAPIPagingSource
import com.example.rickandmorty.retrofit.RAndMAPISERVICE
import com.example.rickandmorty.retrofit.RetrofitClass

class MainActivityViewModel:ViewModel() {
    val services: RAndMAPISERVICE = RetrofitClass.getRetrofit()
            .create(RAndMAPISERVICE::class.java)

    val characters = Pager(PagingConfig(pageSize = 20)){
        RickNMortyAPIPagingSource(services)
    }.flow.cachedIn(viewModelScope)

}