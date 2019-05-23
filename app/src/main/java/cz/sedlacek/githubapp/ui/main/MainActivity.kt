package cz.sedlacek.githubapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.sedlacek.githubapp.ARG_REPO_NAME
import cz.sedlacek.githubapp.ARG_REPO_URL
import cz.sedlacek.githubapp.ClickListener
import cz.sedlacek.githubapp.R
import cz.sedlacek.githubapp.databinding.ActivityMainBinding
import cz.sedlacek.githubapp.databinding.ItemRepoBinding
import cz.sedlacek.githubapp.model.Repo
import cz.sedlacek.githubapp.presenter.MainPresenter
import cz.sedlacek.githubapp.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(), ClickListener<ItemRepoBinding>, MainPresenter.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RepoAdapter
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        initPresenter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_prev -> {
                presenter.onPrevClicked()
            }
            R.id.menu_next -> {
                presenter.onNextClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(binding: ItemRepoBinding) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ARG_REPO_NAME, binding.repo?.name)
        intent.putExtra(ARG_REPO_URL, binding.repo?.url)
        startActivity(intent)
    }

    override fun onSuccess(response: List<Repo>) {
        adapter.add(response)
    }

    override fun onPageChanged(page: Int) {
        adapter.updatePage(page)
        binding.repoList.scrollToPosition(0)
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.repoList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = RepoAdapter(this)
        binding.repoList.adapter = adapter
    }

    private fun initPresenter() {
        presenter = MainPresenterImpl(this)
        presenter.fetchRepos()
    }


}
