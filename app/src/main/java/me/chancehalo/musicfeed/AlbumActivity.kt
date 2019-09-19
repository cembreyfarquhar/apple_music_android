package me.chancehalo.musicfeed

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_detail.*
import kotlinx.android.synthetic.main.albumlist_item.*
import kotlinx.android.synthetic.main.albumlist_item.albumTitle
import kotlinx.android.synthetic.main.albumlist_item.artistName


class AlbumActivity : AppCompatActivity() {

    private var selectedAlbum: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.album_detail)

        selectedAlbum = intent.getSerializableExtra(ALBUM_KEY) as Album


        albumTitle?.text = selectedAlbum?.albumTitle
        artistName?.text = selectedAlbum?.artistName
        Picasso.get().load(selectedAlbum?.artworkUrl).into(albumArtworkView)
    }

    companion object {
        private const val ALBUM_KEY = "ALBUM"
    }
}