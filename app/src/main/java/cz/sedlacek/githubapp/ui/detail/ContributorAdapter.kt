package cz.sedlacek.githubapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cz.sedlacek.githubapp.R
import cz.sedlacek.githubapp.databinding.ItemContributorBinding
import cz.sedlacek.githubapp.model.Contributor

/**
 * Contributors adapter for a recycler view
 */
class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ViewHolder>() {

    private lateinit var contributorList: List<Contributor>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemContributorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_contributor,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::contributorList.isInitialized) contributorList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contributorList[position])
    }

    /**
     * Updates list of contributors
     */
    fun update(contributorList: List<Contributor>) {
        this.contributorList = contributorList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemContributorBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(contributor: Contributor) {
            binding.contributor = contributor
            Glide.with(binding.root.context)
                .load(contributor.avatar)
                .into(binding.contributorAvatar)
        }
    }

}