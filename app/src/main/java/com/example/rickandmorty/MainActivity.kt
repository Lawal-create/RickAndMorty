package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.adapters.CharacterAdapter
import com.example.rickandmorty.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val context = this
        val pagingViewAdapter = CharacterAdapter(context)

        binding.recyclerList.let {
            it.adapter = pagingViewAdapter
            it.layoutManager = GridLayoutManager(context,2)
            it.setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.characters.collectLatest { pagedData ->
                pagingViewAdapter.submitData(pagedData)
            }
        }


    }
}