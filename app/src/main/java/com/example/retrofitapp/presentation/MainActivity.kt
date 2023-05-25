package com.example.retrofitapp.presentation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: WordInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.buttonWordDefinitions.setOnClickListener{
            val query = binding.editTextWord.text
            if (query.isNullOrEmpty()) return@setOnClickListener
            viewModel.getDefinitions(query.toString())
        }

        viewModel.showSnackBarEventValue.observe(this) { messageEvent ->
            messageEvent.getContentIfNotHandled()?.let { message ->
                Snackbar.make(
                    binding.root, message, Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
    }
