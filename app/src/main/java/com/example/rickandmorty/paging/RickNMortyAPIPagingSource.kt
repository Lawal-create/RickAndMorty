package com.example.rickandmorty.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.*
import androidx.paging.PagingState
import com.example.rickandmorty.models.ApiResponse
import com.example.rickandmorty.models.Results
import com.example.rickandmorty.retrofit.RAndMAPISERVICE
import java.lang.Error

class RickNMortyAPIPagingSource(val services:RAndMAPISERVICE):PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val position = params.key?: 1
            val response: ApiResponse = services.getResponseForPage(position).body()!!
            LoadResult.Page(
                    data = response.results,
                    prevKey = if(position>1) position-1 else null,
                    nextKey = if(position<response.info.pages!!) position + 1 else null
            )
        }
        catch (e: Error)
        {
            LoadResult.Error(e)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}