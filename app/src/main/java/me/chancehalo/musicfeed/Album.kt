package me.chancehalo.musicfeed

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


class Album(albumJSON: JSONObject): Serializable {
    private lateinit var artistName: String
    lateinit var albumTitle: String
    private set

    init {
        try {
            artistName = albumJSON.getString("artistName")
            albumTitle = albumJSON.getString("name")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}