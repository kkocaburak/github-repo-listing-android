package com.adesso.ghrepolist.data.remote.model

import com.adesso.ghrepolist.data.remote.BaseResponseModel
import com.adesso.ghrepolist.data.remote.constants.ResponseModelConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoOwnerModel(
    @SerializedName(ResponseModelConstants.LOGIN)
    val userName: String,
    @SerializedName(ResponseModelConstants.AVATAR_URL)
    val avatarUrl: String,
) : BaseResponseModel()