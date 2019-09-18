package me.chancehalo.musicfeed

import org.json.JSONException
import org.json.JSONObject


class Album(albumJSON: JSONObject) {
    private lateinit var artistName: String
    lateinit var albumTitle: String

    init {
        try {
            artistName = albumJSON.getString("artistName")
            albumTitle = albumJSON.getString("name")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}