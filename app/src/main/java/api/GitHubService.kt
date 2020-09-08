package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gabriel Magalhaes on 04/09/2020.
 * @author Gabriel dos S. Magalhães
 */
interface GitHubService {

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ) : Call<GitHubRepositoriesResult>
}