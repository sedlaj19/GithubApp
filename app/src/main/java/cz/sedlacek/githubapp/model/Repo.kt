package cz.sedlacek.githubapp.model

import org.json.JSONObject

/**
 * Data class representing a repository
 */
class Repo(json: String) : JSONObject(json) {

    val id: Long = optLong("id")
    val name: String = optString("name")
    val fullName: String = optString("full_name")
    val url: String = optString("url")
    val stargazers: String = optInt("stargazers_count").toString()
    val size: String = optLong("size").toString()
    val forks: String = optInt("forks_count").toString()
    val contributorsUrl: String = optString("contributors_url")
}