package me.chancehalo.musicfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {


    private val mSongList = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchMusicData()
    }

    private fun fetchMusicData() {
        Log.i("dev", "attempting to fetch data")
        val url =
            "https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/25/explicit.json"

        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
//                Log.i("dev", body)
                val json = JSONObject(body)
                val feed = json.getJSONObject("feed")
//                println(feed)
                val results = feed.getJSONArray("results")
                println(results[0])
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("dev", "it failed")
            }
        })
    }
}

class SongFeed(val songs: List<Song>)

class Song(val feed: Feed)

class Feed(val title: String)