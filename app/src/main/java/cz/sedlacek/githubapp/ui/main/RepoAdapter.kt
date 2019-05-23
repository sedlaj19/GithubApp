package cz.sedlacek.githubapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cz.sedlacek.githubapp.ClickListener
import cz.sedlacek.githubapp.ITEMS_PER_PAGE
import cz.sedlacek.githubapp.R
import cz.sedlacek.githubapp.databinding.ItemRepoBinding
import cz.sedlacek.githubapp.model.Repo

/**
 * Repository adapter for a recycler view
 */
class RepoAdapter(
    private val listener: ClickListener<ItemRepoBinding>
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    private var repoList: ArrayList<Repo> = ArrayList()
    private var pagedList: ArrayList<Repo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_repo,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pagedList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pagedList[position])
    }

    /**
     * Adds list of repositories to the existing one
     */
    fun add(repoList: List<Repo>) {
        this.repoList.addAll(repoList)
        if (pagedList.isEmpty()) {
            updatePage(0)
        }
    }

    /**
     * Updates data based on given page number
     *
     * @param page based on this number it will show sublist of the repositories
     */
    fun updatePage(page: Int) {
        pagedList.clear()
        pagedList.addAll(repoList.subList(ITEMS_PER_PAGE * page, ITEMS_PER_PAGE * page + ITEMS_PER_PAGE))
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(repo: Repo) {
            binding.repo = repo
            binding.root.setOnClickListener { listener.onClick(binding) }
        }
    }

}