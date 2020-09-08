package br.com.gabriel.primeiroprojetoemkotlin.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import api.GitHubRepositoriesResult
import api.RepositoryRetriever
import br.com.gabriel.primeiroprojetoemkotlin.R
import br.com.gabriel.primeiroprojetoemkotlin.adapter.ProgrammingLanguageAdapter
import br.com.gabriel.primeiroprojetoemkotlin.adapter.RepositoryAdapter
import br.com.gabriel.primeiroprojetoemkotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val callback = object : Callback<GitHubRepositoriesResult> {
        /**
         * Invoked for a received HTTP response.
         *
         *
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call [Response.isSuccessful] to determine if the response indicates success.
         */
        override fun onResponse(
            call: Call<GitHubRepositoriesResult>,
            response: Response<GitHubRepositoriesResult>
        ) {
            longToast("Load finished.")

            if (response.isSuccessful) {
                response.body()?.repositories?.let {
                    recyclerView.adapter = RepositoryAdapter(it) { repository ->
                        longToast("Clicked item: ${repository.fullName}")
                    }
                }
            }
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected exception
         * occurred creating the request or processing the response.
         */
        override fun onFailure(call: Call<GitHubRepositoriesResult>, t: Throwable) {
            longToast("Fail loading repositories.")

            Log.e("MainActivity", "Problem calling GitHub API", t)
        }
    }
    private val repositoryRetriever = RepositoryRetriever()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Em vez de findViewById<TextView>(R.id.textView)
        // setText()
        //textView.text = "Hello, world!"

        // getText()
        //val name: String = textView.text as String

        // ou
        //val name: String = textView.text.toString()

        // declarar o Adapter
        recyclerView.adapter = ProgrammingLanguageAdapter(recyclerViewItems()) {
            // declaração possível pela biblioteca Anko, que reduz a sua chamada.
            longToast("Clicked item: ${it.title}")
            repositoryRetriever.getLanguageRepositories(callback, it.title)
        }

        // Grid
//        val layoutManager = GridLayoutManager(this, 2)

        // Grid Escalonável
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Lista na Horizontal
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Lista na Vertical
        val layoutManager = LinearLayoutManager(this)

        // Por último definimos o Layout Manager:
        recyclerView.layoutManager = layoutManager
    }

    private fun recyclerViewItems(): List<ProgrammingLanguage> {
        val kotlin = ProgrammingLanguage(
            R.drawable.kotlin,
            "Kotlin",
            2010,
            "Kotlin é uma Linguagem de programação que compila para a Máquina virtual " +
                    "Java e que também pode ser traduzida para JavaScript e compilada para código" +
                    " nativo. É desenvolvida pela JetBrains, seu nome é baseado na ilha de Kotlin" +
                    " onde se situa a cidade russa de Kronstadt, próximo à São Petersburgo. " +
                    "Apesar de a sintaxe de Kotlin diferir da de Java, Kotlin é projetada para ter" +
                    " uma interoperabilidade total com código Java. Foi considerada pelo público" +
                    " a 2a linguagem 'mais amada', de acordo com uma pesquisa conduzida pelo site" +
                    "Stack Overflow em 2018."
        )
        return listOf(kotlin)
    }
}
