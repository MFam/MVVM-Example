package com.mfamstory.rxmvvmtest.data.network.model

import com.google.gson.annotations.SerializedName

data class ResRepository (
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: ArrayList<Repository>
)