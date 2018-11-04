package kildea.audioresourcecompanion;

import android.support.annotation.NonNull;

public class Song implements Comparable<Song> {

    private String title;
    private int track_number;

    public Song(String the_title) {
        title = the_title;
        track_number = 1;
    }

    public String getTitle() { return title; }

    @Override
    public int compareTo(@NonNull Song new_song) {
        int current = this.track_number;
        int comparison = new_song.track_number;
        return (current == comparison) ? 0 :
                (current < comparison) ? -1 : 1; //TODO: Verify this is right
    }
}
