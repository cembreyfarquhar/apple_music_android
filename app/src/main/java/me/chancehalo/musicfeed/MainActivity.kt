package me.chancehalo.musicfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AlbumListAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private val albumList = ArrayList<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = AlbumListAdapter(albumList)


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

//class SongFeed(val songs: List<Song>)
//
//class Song(val feed: Feed)
//
//class Feed(val title: String)