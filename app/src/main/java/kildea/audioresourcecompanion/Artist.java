package kildea.audioresourcecompanion;

import android.support.annotation.NonNull;

import java.util.Set;

public class Artist implements Comparable<Artist> {

    private String name;
    private Set<Album> albums;

    public Artist(String artist_name) {
        name = artist_name;
    }

    @Override
    public int compareTo(Artist new_artist) {
        String current = this.name.toLowerCase();
        String comparison = new_artist.name.toLowerCase();
        return (current == comparison) ? 0 : current.compareTo(comparison);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
