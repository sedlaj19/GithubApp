package cz.sedlacek.githubapp.model

import org.json.JSONArray

/**
 * Data class representing a Contributors array
 */
class ContributorsResponse(json: String) {

    val contributors = JSONArray(json)

}