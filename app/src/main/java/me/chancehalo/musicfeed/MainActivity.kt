package me.chancehalo.musicfeed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private val albumList = ArrayList<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(albumList)
        recyclerView.adapter = adapter
//        setRecyclerViewScrollListener()
//        gridLayoutManager = GridLayoutManager(this, 2)
//        setRecyclerViewItemTouchListener()

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
                val first = results.getJSONObject(0)
                Log.i("dev", first.toString())

                addToList(Album(first))
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("dev", "it failed")
            }
        })
    }

    private fun addToList(album: Album) {
        runOnUiThread {
            albumList.add(album)
            adapter.notifyItemInserted(albumList.size - 1)
        }
    }
}

//class SongFeed(val songs: List<Song>)
//
//class Song(val feed: Feed)
//
//class Feed(val title: String)