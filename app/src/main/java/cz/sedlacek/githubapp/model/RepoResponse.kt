package cz.sedlacek.githubapp.model

import org.json.JSONArray

/**
 * Data class representing a repositories array
 */
class RepoResponse(json: String) {

    val repositories = JSONArray(json)

}