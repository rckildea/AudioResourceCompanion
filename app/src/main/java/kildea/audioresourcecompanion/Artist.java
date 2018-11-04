package kildea.audioresourcecompanion;

import android.os.Environment;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class Artist implements Comparable<Artist> {

    private String name;
    private Set<Album> albums = new TreeSet<Album>();
    private File artist_image;
    private static String image_path = Environment.getExternalStorageDirectory().toString()+"/ARC/artist_images/";
    private static File image_folder = new File(image_path);

    public Artist(String artist_name) {
        name = artist_name;
        albums.add(new Album("Unknown", name));
        artist_image = findArtistImage();
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

    public File getArtistImage() { return artist_image; }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() { return albums; }

    private File findArtistImage() {
        String path_name = format_path_name();
        File image_file = new File(path_name);

        if (!image_folder.exists()) {
            image_folder.mkdirs();
        }

        if (image_file.exists()) {
            return image_file;
        } else {
            return new File("drawable/unknown_artist_icon.png");
        }
    }

    private String format_path_name() {
        return image_path + this.getName().toLowerCase().replace(" ", "_") + ".png";
    }

}
