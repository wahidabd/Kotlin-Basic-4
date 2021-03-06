package com.udacoding.kotlinbasic4.view

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.udacoding.kotlinbasic4.databinding.ActivityDetailBinding
import com.udacoding.kotlinbasic4.model.ArticlesItems

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<ArticlesItems>(EXTRA_DATA)

        if (isConnect()){
            binding.webView.loadUrl(data?.url.toString())
        }else{
            Snackbar.make(binding.root, "Tidak ada koneksi internet", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun isConnect(): Boolean{
        val connect: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}