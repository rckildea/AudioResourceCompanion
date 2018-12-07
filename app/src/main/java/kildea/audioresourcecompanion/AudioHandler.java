package kildea.audioresourcecompanion;

import android.media.MediaPlayer;

import com.mpatric.mp3agic.Mp3File;

import java.io.IOException;

public class AudioHandler {

    private MediaPlayer mediaPlayer;
    private Mp3File current_track = null;

    public void setTrack(Mp3File mp3) {
        current_track = mp3;
        playMusic();
    }

    public void playMusic() {
        stopMusic();
        try {
            mediaPlayer.setDataSource(current_track.getFilename());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
