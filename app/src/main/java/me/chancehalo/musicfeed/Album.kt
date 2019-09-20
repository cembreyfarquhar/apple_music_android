package me.chancehalo.musicfeed

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


class Album(albumJSON: JSONObject) : Serializable {
    lateinit var artistName: String
    lateinit var albumTitle: String
    var id = 0
    lateinit var artworkUrl: String

    init {
        try {
            artistName = albumJSON.getString("artistName")
            albumTitle = albumJSON.getString("name")
            id = albumJSON.getInt("id")
            artworkUrl = albumJSON.getString("artworkUrl100")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}