package me.chancehalo.musicfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.albumlist_item.*


class AlbumActivity : AppCompatActivity() {

    private var selectedAlbum: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.album_detail)

        selectedAlbum = intent.getSerializableExtra(ALBUM_KEY) as Album


        albumTitle?.text = selectedAlbum?.albumTitle

    }

    companion object {
        private val ALBUM_KEY = "ALBUM"
    }
}