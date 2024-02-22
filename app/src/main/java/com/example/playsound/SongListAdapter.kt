package com.example.playsound

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView

class SongListAdapter(context: Context, resource: Int, objects: Array<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentSongId: Int = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val textSongTitle: TextView = convertView!!.findViewById(R.id.textSongTitle)
        val buttonPlay: ImageButton = convertView.findViewById(R.id.buttonPlay)
        val buttonPause: ImageButton = convertView.findViewById(R.id.buttonPause)
        val buttonReplay: ImageButton = convertView.findViewById(R.id.buttonReplay)

        val songTitle = getItem(position)
        textSongTitle.text = songTitle

        buttonPlay.setOnClickListener {
            playSong(position)
        }

        buttonPause.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        buttonReplay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.seekTo(0)
            }
        }

        return convertView
    }

    private fun playSong(songId: Int) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }

        currentSongId = context.resources.getIdentifier("song${songId + 1}", "raw", context.packageName)
        mediaPlayer = MediaPlayer.create(context, currentSongId)
        mediaPlayer?.start()
    }
}