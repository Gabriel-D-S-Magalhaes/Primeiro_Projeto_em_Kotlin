package api

import com.google.gson.annotations.SerializedName

data class GitHubRepositoriesResult(
    @SerializedName("items") val repositories: List<Repository>
)

data class Repository(
    val id: Long?,
    val name: String?,
    @SerializedName("full_name") val fullName: String?,
    val owner: RepositoryOwner?,
    @SerializedName("html_url") val htmlUrl: String?,
    val description: String?
)

data class RepositoryOwner(
    val id: Long?,
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?
)
