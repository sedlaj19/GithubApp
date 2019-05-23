package cz.sedlacek.githubapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.sedlacek.githubapp.ARG_REPO_NAME
import cz.sedlacek.githubapp.ARG_REPO_URL
import cz.sedlacek.githubapp.R
import cz.sedlacek.githubapp.databinding.ActivityDetailBinding
import cz.sedlacek.githubapp.model.Contributor
import cz.sedlacek.githubapp.model.Repo
import cz.sedlacek.githubapp.presenter.DetailPresenter

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: ContributorAdapter
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initData()
        initPresenter()
    }

    override fun onContributorsFetched(response: List<Contributor>) {
        adapter.update(response)
    }

    override fun showProgress() {
        // Could implement some progress view and show it here
    }

    override fun hideProgress() {
        // Could implement some progress view and hide it here
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onRepoFetched(response: Repo) {
        presenter.fetchContributors(response.contributorsUrl)
        binding.repo = response
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.repoContributorsList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = ContributorAdapter()
        binding.repoContributorsList.adapter = adapter
    }

    private fun initData() {
        title = intent.getStringExtra(ARG_REPO_NAME)
        println(intent.getStringExtra(ARG_REPO_URL))
    }

    private fun initPresenter() {
        presenter = DetailPresenterImpl(this)
        presenter.fetchRepo(intent.getStringExtra(ARG_REPO_URL))
    }
}
