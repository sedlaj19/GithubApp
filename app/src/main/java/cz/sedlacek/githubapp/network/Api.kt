package cz.sedlacek.githubapp.network

import cz.sedlacek.githubapp.model.Contributor
import cz.sedlacek.githubapp.model.Repo

/**
 * Interface to fetch data from github
 */
interface Api {

    /**
     * Fetches all public repositories
     */
    fun repositories(since: Long): List<Repo>

    /**
     * Fetches repository from given url
     *
     * @param url to a specific repository
     */
    fun repository(url: String): Repo

    /**
     * Fetches list of contributors from given url
     *
     * @param url used to fetch all contributors
     */
    fun contributors(url: String): List<Contributor>

}