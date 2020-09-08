package api

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Gabriel Magalhaes on 04/09/2020.
 * @author Gabriel dos S. Magalhães
 *
 * Responsável por receber as solicitações externas e se comunicar com o Retrofit para retornar os
 * dados corretamente.
 */
class RepositoryRetriever {
    private val baseUrl = "https://api.github.com/"
    private val service: GitHubService

    init {
        val retrofit = Retrofit.Builder()
            // retrofit builder information
            .baseUrl(baseUrl)// completar os endpoints corretamente
            .addConverterFactory(GsonConverterFactory.create())// desserializar
            .build()

        service = retrofit.create(GitHubService::class.java)// inicializar o service
    }

    fun getLanguageRepositories(callback: Callback<GitHubRepositoriesResult>, language: String) {
        val call = service.searchRepositories("language:$language")
        call.enqueue(callback)
    }
}