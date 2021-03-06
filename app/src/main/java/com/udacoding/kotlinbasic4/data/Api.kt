package com.udacoding.kotlinbasic4.data

import com.udacoding.kotlinbasic4.model.ArticleResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api {

    @GET("everything?q=sepakbola&apiKey=5b451eae6c2e4deebf2840f83664a528")
    fun getOlahraga(): Observable<ArticleResponse>

    @GET("everything?q=teknologi&apiKey=5b451eae6c2e4deebf2840f83664a528")
    fun getTeknologi(): Observable<ArticleResponse>

    @GET("everything?q=trending&apiKey=5b451eae6c2e4deebf2840f83664a528")
    fun getTrending(): Observable<ArticleResponse>

    @GET("everything?q=artis&apiKey=5b451eae6c2e4deebf2840f83664a528")
    fun getArtis(): Observable<ArticleResponse>
}