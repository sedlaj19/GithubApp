package cz.sedlacek.githubapp.ui.detail

import cz.sedlacek.githubapp.network.MyApi
import cz.sedlacek.githubapp.presenter.DetailPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

/**
 * DetailPresenter implementation to fetch data
 */
class DetailPresenterImpl(private val view: DetailPresenter.View) : DetailPresenter {

    private val api = MyApi()

    override fun fetchRepo(url: String) {
        view.showProgress()
        doAsync {
            try {
                val response = api.repository(url)
                onComplete {
                    view.hideProgress()
                    view.onRepoFetched(response)
                }
            } catch (e: Exception) {
                onComplete {
                    view.hideProgress()
                    view.showError(e)
                }
            }
        }
    }

    override fun fetchContributors(url: String) {
        doAsync {
            try {
                val response = api.contributors(url)
                onComplete {
                    view.hideProgress()
                    view.onContributorsFetched(response)
                }
            } catch (e: Exception) {
                onComplete {
                    view.hideProgress()
                    view.showError(e)
                }
            }
        }
    }

}