import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.playsound.R

class SongListAdapter(
    context: Context,
    private val songTitles: Array<String>,
    private val songRawIds: Array<Int>
): ArrayAdapter<String>(context, R.layout.list_item, songTitles) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentSongId: Int = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

            viewHolder = ViewHolder()
            viewHolder.textSongTitle = convertView.findViewById(R.id.textSongTitle)
            viewHolder.buttonPlay = convertView.findViewById(R.id.buttonPlay)
            viewHolder.buttonPause = convertView.findViewById(R.id.buttonPause)
            viewHolder.buttonReplay = convertView.findViewById(R.id.buttonReplay)

            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val songTitle = getItem(position)
        viewHolder.textSongTitle.text = songTitle

        viewHolder.buttonPlay.setOnClickListener {
            playSong(position)
        }

        viewHolder.buttonPause.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        viewHolder.buttonReplay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.seekTo(0)
            }
        }

        return convertView!!
    }

    private fun playSong(songId: Int) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }

        currentSongId = songRawIds[songId]
        mediaPlayer = MediaPlayer.create(context, currentSongId)
        mediaPlayer?.start()
    }

    private class ViewHolder {
        lateinit var textSongTitle: TextView
        lateinit var buttonPlay: ImageButton
        lateinit var buttonPause: ImageButton
        lateinit var buttonReplay: ImageButton
    }
}
