package cz.sedlacek.githubapp.model

import org.json.JSONObject

/**
 * Data class representing a Contributor
 */
class Contributor(json: String): JSONObject(json) {

    val login: String = optString("login")
    val avatar: String = optString("avatar_url")

}