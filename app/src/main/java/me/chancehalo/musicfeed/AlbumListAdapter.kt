package me.chancehalo.musicfeed

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.albumlist_item.view.*

class RecyclerAdapter(private val albums: ArrayList<Album>) :
    RecyclerView.Adapter<RecyclerAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        // return an inflated view
        val inflatedView = parent.inflate(R.layout.albumlist_item, false)
        return AlbumViewHolder(inflatedView)
    }

    override fun getItemCount() = albums.size


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val itemAlbum = albums[position]
        holder.bindAlbum(itemAlbum)
    }

    class AlbumViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var album: Album? = null

        // Initialize the View.OnClickListener
        init {
            view.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val context = view.context
            val showAlbumItent = Intent(context, AlbumActivity::class.java).apply {
                putExtra(ALBUM_KEY, album)
            }
            context?.startActivity(showAlbumItent)
        }

        // So we can use "ALBUM" for easy reference to the item launching the RecyclerView
        companion object {
            private const val ALBUM_KEY = "ALBUM"
        }

        fun bindAlbum(album: Album) {
            this.album = album
            view.albumTitle.text = album.albumTitle
            view.artistName.text = album.artistName
            Picasso.get().load(album?.artworkUrl).into(view.albumArtworkListView)
            view.albumArtworkListView.clipToOutline
        }

    }

}
