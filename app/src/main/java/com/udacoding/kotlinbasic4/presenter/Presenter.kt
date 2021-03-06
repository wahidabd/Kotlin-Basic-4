package com.udacoding.kotlinbasic4.presenter

import android.util.Log
import com.udacoding.kotlinbasic4.data.RetrofitClient
import com.udacoding.kotlinbasic4.model.ArticlesItems
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Presenter (private val presenterView: PresenterView){

    fun getOlahraga(){
        RetrofitClient.instance.getOlahraga()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       presenterView.onSuccess(it.articles as ArrayList<ArticlesItems>)
            }, {
               Log.d("Failure", it.localizedMessage)
            })
    }

    fun getTeknologi(){
        RetrofitClient.instance.getTeknologi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenterView.onSuccess(it.articles as ArrayList<ArticlesItems>)
            }, {
                Log.d("Failure", it.localizedMessage)
            })
    }

    fun getTrending(){
        RetrofitClient.instance.getTrending()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenterView.onSuccess(it.articles as ArrayList<ArticlesItems>)
            }, {
                Log.d("Failure", it.localizedMessage)
            })
    }

    fun getArtis(){
        RetrofitClient.instance.getArtis()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenterView.onSuccess(it.articles as ArrayList<ArticlesItems>)
            }, {
                Log.d("Failure", it.localizedMessage)
            })
    }

}