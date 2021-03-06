package com.udacoding.kotlinbasic4.view.fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.udacoding.kotlinbasic4.adapter.ArticleListAdapter
import com.udacoding.kotlinbasic4.databinding.FragmentTeknologiBinding
import com.udacoding.kotlinbasic4.model.ArticlesItems
import com.udacoding.kotlinbasic4.presenter.Presenter
import com.udacoding.kotlinbasic4.presenter.PresenterView
import com.udacoding.kotlinbasic4.view.DetailActivity

class TeknologiFragment : Fragment(), PresenterView {

    private var presenter: Presenter? = null
    private var _binding: FragmentTeknologiBinding? = null
    private val binding get() = _binding!!
    private lateinit var pd: ProgressDialog

    lateinit var adapter: ArticleListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTeknologiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = Presenter(this)
        presenter?.getTeknologi()

        binding.rvTeknologi.layoutManager = LinearLayoutManager(context)

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        pd = ProgressDialog(context)
        pd.setMessage("Sedang memuat data...")
        pd.show()

        if (!isConnect()){
            Snackbar.make(binding.root, "Tidak ada koneksi internet", Snackbar.LENGTH_LONG).show()
            pd.dismiss()
        }
    }

    override fun onSuccess(article: ArrayList<ArticlesItems>) {
        adapter = ArticleListAdapter(article)
        binding.rvTeknologi.adapter = adapter
        adapter.setOnItemClickCallback(object : ArticleListAdapter.OnItemClickCallback{
            override fun itemClicked(data: ArticlesItems) {
                val intent = Intent(activity?.baseContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                activity?.startActivity(intent)
            }
        })
        pd.dismiss()
    }

    override fun onError(msg: String) {
        Log.d("Failure", msg)
    }

    private fun isConnect(): Boolean{
        val connect: ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}