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
                val json = JSONObject(body)
                val feed = json.getJSONObject("feed")
                val results = feed.getJSONArray("results")

                for (i in 0 until results.length()) {
                    val album = results.getJSONObject(i)
                    addToList(Album(album))
                }
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
