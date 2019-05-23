package cz.sedlacek.githubapp.presenter

import cz.sedlacek.githubapp.model.Contributor
import cz.sedlacek.githubapp.model.Repo

/**
 * DetailActivity presenter
 */
interface DetailPresenter {
    interface View : BaseView {

        /**
         * Callback with fetched repository
         */
        fun onRepoFetched(response: Repo)

        /**
         * Callback with list of contributors
         */
        fun onContributorsFetched(response: List<Contributor>)
    }

    /**
     * Fetches repository from given url
     */
    fun fetchRepo(url: String)

    /**
     * Fetches all contributors to the given repository
     */
    fun fetchContributors(url: String)
}