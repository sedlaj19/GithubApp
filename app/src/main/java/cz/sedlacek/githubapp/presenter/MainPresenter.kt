package cz.sedlacek.githubapp.presenter

import cz.sedlacek.githubapp.model.Repo


/**
 * Main activity presenter
 */
interface MainPresenter {

    interface View : BaseView {

        /**
         * Callback with list of retrieved repositories
         */
        fun onSuccess(response: List<Repo>)

        /**
         * Callback with page change after click on next or prev
         */
        fun onPageChanged(page: Int)

    }

    /**
     * Fetches repositories from github
     */
    fun fetchRepos()

    /**
     * Handles click on next
     */
    fun onNextClicked()

    /**
     * Handles click on prev
     */
    fun onPrevClicked()
}