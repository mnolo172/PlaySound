import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.playsound.R

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

        // Crear un adaptador per al ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songNames)

        // Connectar l'adaptador al ListView
        listView.adapter = adapter

        // Inicialitzar el reproductor de mitjans de comunicació
        mediaPlayer = MediaPlayer()

        // Configurar la reproducció al fer clic a un element de la llista
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
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

