package cz.sedlacek.githubapp

import androidx.databinding.ViewDataBinding

/**
 * Interface to handle clicks on repository items
 */
interface ClickListener<T : ViewDataBinding> {
	fun onClick(binding: T)
}