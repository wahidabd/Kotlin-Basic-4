package com.udacoding.kotlinbasic4.presenter

import com.udacoding.kotlinbasic4.model.ArticlesItems

interface PresenterView {
    fun onSuccess(article: ArrayList<ArticlesItems>)
    fun onError(msg: String)
}