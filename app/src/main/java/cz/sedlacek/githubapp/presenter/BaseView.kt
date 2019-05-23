package cz.sedlacek.githubapp.presenter

/**
 * Base interface to have a connection to a View from a presenter with basic functions
 */
interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: Throwable)

}