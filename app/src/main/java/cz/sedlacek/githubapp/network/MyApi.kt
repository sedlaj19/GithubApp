package cz.sedlacek.githubapp.network

import cz.sedlacek.githubapp.*
import cz.sedlacek.githubapp.model.Contributor
import cz.sedlacek.githubapp.model.ContributorsResponse
import cz.sedlacek.githubapp.model.Repo
import cz.sedlacek.githubapp.model.RepoResponse
import java.lang.Exception
import java.net.URL

/**
 * Implementation of Api interface to fetch data from github
 */
class MyApi : Api {

    override fun repositories(since: Long): List<Repo> {
        val response = URL("$BASE_URL$ENDPOINT_ALL_REPO$PARAM_SINCE$since").readText()
        return RepoResponse(response).let { it ->
            0.until(it.repositories.length()).map { i -> it.repositories.optJSONObject(i) }
                .map { Repo(it.toString()) }
        }
    }

    override fun repository(url: String): Repo {
        val response = URL(url).readText()
        return Repo(response)
    }

    override fun contributors(url: String): List<Contributor> {
        val response = URL(url).readText()
        return ContributorsResponse(response).let { it ->
            0.until(it.contributors.length()).map { i -> it.contributors.optJSONObject(i) }
                .map { Contributor(it.toString()) }
        }
    }

}