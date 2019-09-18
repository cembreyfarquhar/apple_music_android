package me.chancehalo.musicfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

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
                Log.i("dev", "got em!")
                Log.i("dev", response?.body?.string())
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("dev", "it failed")
            }
        })

    }
}
