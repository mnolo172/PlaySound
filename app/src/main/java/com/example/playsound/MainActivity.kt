import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.playsound.R

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var currentSongId: Int = 0
    private lateinit var songRawIds: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)

        val songTitles = arrayOf("Atrevete", "Gasolina", "Suavemente") // Nombres de las canciones
        val songRawIds = arrayOf(R.raw.atrevete, R.raw.gasolina, R.raw.suavemente) // Identificadores de recursos de las canciones

        val adapter = SongListAdapter(this, songTitles, songRawIds)
        listView.adapter = adapter

        mediaPlayer = MediaPlayer()

        listView.setOnItemClickListener { _, _, position, _ ->
            playSong(position)
        }
    }

    private fun playSong(songId: Int) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }

        currentSongId = songRawIds[songId]
        mediaPlayer = MediaPlayer.create(this, currentSongId)
        mediaPlayer?.start()
    }

    fun pauseSong(view: View) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun stopSong(view: View) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer()
        }
    }
}

