package com.example.rickandmorty.models

import com.google.gson.annotations.SerializedName

class Info {
    @SerializedName("count")
    var count:Long? = null

    @SerializedName("pages")
    var pages:Long? = null

    @SerializedName("next")
    var nexts:String? = null

    @SerializedName("prev")
    var prev:String? = null
}