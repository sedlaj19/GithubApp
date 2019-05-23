package cz.sedlacek.githubapp.ui.main

import cz.sedlacek.githubapp.ITEMS_PER_PAGE
import cz.sedlacek.githubapp.network.MyApi
import cz.sedlacek.githubapp.presenter.MainPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

/**
 * Main activity presenter implementation
 */
class MainPresenterImpl(private val view: MainPresenter.View) :
    MainPresenter {

    private val api = MyApi()
    private var page = 0
    private var itemLoaded = 0
    private var lastId = 0L

    override fun fetchRepos() {
        if (itemLoaded == 0) {
            view.showProgress()
        }
        doAsync {
            try {
                val response = api.repositories(lastId)
                onComplete {
                    itemLoaded += response.size
                    lastId = response.last().id
                    view.hideProgress()
                    println(response.size)
                    view.onSuccess(response)
                }
            } catch (e: Exception) {
                onComplete {
                    view.showError(e)
                    view.hideProgress()
                }
            }
        }
    }

    override fun onNextClicked() {
        if (page * ITEMS_PER_PAGE + ITEMS_PER_PAGE >= itemLoaded) {
            return
        }
        page++
        view.onPageChanged(page)
        if (page * ITEMS_PER_PAGE + 2 * ITEMS_PER_PAGE >= itemLoaded) {
            fetchRepos()
        }
    }

    override fun onPrevClicked() {
        if (page > 0) {
            page--
            view.onPageChanged(page)
        }
    }

}