package com.mfamstory.rxmvvmtest.data.network.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id") val id: String, // Int
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String
)