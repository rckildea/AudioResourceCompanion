package kildea.audioresourcecompanion;

import android.media.MediaPlayer;

import com.mpatric.mp3agic.Mp3File;

import java.io.IOException;

public class AudioHandler {

    private MediaPlayer mediaPlayer;

    public void playTrack(Mp3File mp3) {
        stopAudio();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mp3.getFilename());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void stopAudio() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
