package com.adesso.ghrepolist.data.remote.model

import com.adesso.ghrepolist.base.ListAdapterItem
import com.adesso.ghrepolist.data.remote.BaseResponseModel
import com.adesso.ghrepolist.data.remote.constants.ResponseModelConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepoModelItem(
    @SerializedName(ResponseModelConstants.NAME)
    val repoName: String,
    @SerializedName(ResponseModelConstants.OWNER)
    val repoOwner: RepoOwnerModel,
    @SerializedName(ResponseModelConstants.STAR_GAZERS_COUNT)
    val repoStarCount: Long,
    @SerializedName(ResponseModelConstants.OPEN_ISSUES_COUNT)
    val openIssuesCount: Long,
    var isFavorite: Boolean? = false
) : BaseResponseModel(), ListAdapterItem {

    fun getStarCountText(): String {
        return ResponseModelConstants.STARS_TEXT + repoStarCount
    }

    fun getOpenIssuesText(): String {
        return ResponseModelConstants.OPEN_ISSUES_TEXT + openIssuesCount
    }

}