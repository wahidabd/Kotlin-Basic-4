package com.udacoding.kotlinbasic4.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesItems(
    val title: String? = null,
    val content: String? = null,
    val urlToImage: String? = null,
    val url: String? = null
): Parcelable