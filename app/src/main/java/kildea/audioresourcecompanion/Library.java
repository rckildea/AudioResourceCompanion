package kildea.audioresourcecompanion;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Library {

    private Set<Artist> artists = new TreeSet<>();

    protected ArrayList<Mp3File> collection = new ArrayList<>();

    public Library() {
        String music_path = Environment.getExternalStorageDirectory().toString()+"/Music/";
        File music_folder = new File(music_path);

        if (!music_folder.exists()) {
            music_folder.mkdirs();
        }

        File[] music_files = music_folder.listFiles();

        for (File f : music_files) {
            if (FilenameUtils.getExtension(f.getPath()).equalsIgnoreCase("mp3")) {
                try {
                    String path = f.getPath();
                    Mp3File temp = new Mp3File(path);
                    ID3v2 new_tag = createId3v2Tag(temp);
                    temp.setId3v2Tag(new_tag);
                    saveMP3(temp, path);
                    collection.add(temp);
                    artists.add(new Artist(temp.getId3v2Tag().getArtist()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("mp3s", "number of files: "+collection.size());
    }

    // mp3agic does not allow you to save an MP3 file with its original file name, so this is the crummy workaround
    private void saveMP3(Mp3File temp, String path) {
        try {
            temp.save(path+"temp");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
        File file2 = new File(path+"temp");
        boolean success = file2.renameTo(new File(path));
        File f2 = new File(path+"temp");
        boolean success2 = f2.delete();
    }

    private ID3v2 createId3v2Tag(Mp3File mp3File) {
        ID3v2 id3v2Tag;
        if (!mp3File.hasId3v2Tag()) {
            ID3v1 id3v1Tag;
            if (mp3File.hasId3v1Tag()) {
                id3v1Tag = mp3File.getId3v1Tag();
            } else {
                id3v1Tag = null;
            }
            id3v2Tag = createId3v2Tag(id3v1Tag);
        } else {
            id3v2Tag = mp3File.getId3v2Tag();
            id3v2Tag = verifyId3v2Tag(id3v2Tag);
        }
        return id3v2Tag;
    }

    private ID3v2 createId3v2Tag(ID3v1 tag) {
        ID3v2 id3v2Tag = new ID3v24Tag();
        if (tag == null) {
            id3v2Tag.setTrack("Unknown");
            id3v2Tag.setArtist("Unknown");
            id3v2Tag.setTitle("Unknown");
            id3v2Tag.setAlbum("Unknown");
            id3v2Tag.setYear("Unknown");
            id3v2Tag.setGenre(12);
            id3v2Tag.setComment("");
            id3v2Tag.setLyrics("");
            id3v2Tag.setUrl("");
        } else {
            id3v2Tag.setTrack((tag.getTrack() == null) ? "Unknown" : tag.getTrack());
            id3v2Tag.setArtist((tag.getArtist() == null) ? "Unknown" : tag.getArtist());
            id3v2Tag.setTitle((tag.getTitle() == null) ? "Unknown" : tag.getTitle());
            id3v2Tag.setAlbum((tag.getAlbum() == null) ? "Unknown" : tag.getAlbum());
            id3v2Tag.setYear((tag.getYear() == null) ? "Unknown" : tag.getYear());
            id3v2Tag.setGenre((tag.getGenre() < 0 && tag.getGenre() > 79) ? 12 : tag.getGenre());
            id3v2Tag.setComment((tag.getComment() == null) ? "" : tag.getComment());
            id3v2Tag.setLyrics("");
            id3v2Tag.setUrl("");
        }
        return id3v2Tag;
    }

    private ID3v2 verifyId3v2Tag(ID3v2 tag) {
        if (tag.getTrack() == null) tag.setTrack("Unknown");
        if (tag.getArtist() == null) {
            tag.setArtist((tag.getAlbumArtist() == null) ? "Unknown" : tag.getAlbumArtist());
        }
        if (tag.getTitle() == null) tag.setTitle("Unknown");
        if (tag.getAlbum() == null) tag.setAlbum("Unknown");
        if (tag.getYear() == null) tag.setYear("Unknown");
        if (tag.getGenre() < 0 && tag.getGenre() > 79) tag.setGenre(12);
        if (tag.getComment() == null) tag.setComment("");
        if (tag.getLyrics() == null) tag.setLyrics("");
        if (tag.getUrl() == null) tag.setUrl("");

        return tag;
    }

    public ArrayList<Artist> getLibrary() {
        return new ArrayList<Artist>();
    }
}
