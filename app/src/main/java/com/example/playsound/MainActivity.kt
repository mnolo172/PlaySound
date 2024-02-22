import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.playsound.R
import com.example.playsound.SongListAdapter

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var currentSongId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtenir la referència al ListView
        val listView: ListView = findViewById(R.id.listView)

        // Definir la llista de noms de cançons
        val songNames = arrayOf("song1", "song2", "song3") // Reemplaçar amb els noms reals

        // Utilitza un adaptador personalitzat
        val adapter = SongListAdapter(this, R.layout.list_item, songNames)
        listView.adapter = adapter

        mediaPlayer = MediaPlayer()

        listView.setOnItemClickListener { _, _, position, _ ->
            playSong(position)
        }
    }

    private fun playSong(songId: Int) {
        // Pausar la reproducció actual si n'hi ha una
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }

        // Obre i configura el nou arxiu MP3
        currentSongId = resources.getIdentifier("song${songId + 1}", "raw", packageName)
        mediaPlayer = MediaPlayer.create(this, currentSongId)

        // Iniciar la reproducció
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

