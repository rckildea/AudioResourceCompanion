package kildea.audioresourcecompanion;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Album implements Comparable<Album> {

    private String title;
    private String artist;
    private Date release_date;
    private Set<Song> track_list = new TreeSet<Song>();
    private File album_image;
    private static String image_path = Environment.getExternalStorageDirectory().toString()+"/ARC/artist_album_images/";
    private static File image_folder = new File(image_path);


    public Album(String album_title, String artist_name) {
        title = album_title;
        artist = artist_name;
        album_image = this.findAlbumImage();
        release_date = new Date(2018,10,30);
        track_list.add(new Song("Untitled Song"));
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public File getAlbumImage() { return album_image; }


    public Set<Song> getTrackList() { return track_list; }

    private File findAlbumImage() {
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
        return image_path + this.getArtist() + "/" + this.getTitle().toLowerCase().replace(" ", "_") + ".png";
    }

    @Override
    public int compareTo(@NonNull Album new_album) {
        Date current = this.release_date;
        Date comparison = new_album.release_date;
        return (current == comparison) ? 0 : current.compareTo(comparison);
    }
}
