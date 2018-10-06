package kildea.audioresourcecompanion;

import android.os.Environment;
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

public class Library {

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
                    Mp3File temp = new Mp3File(f.getPath());
                    createId3v2Tag(temp);
                    collection.add(temp);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<Artist> artists;
        //Log.d("mp3s", "number of files: "+collection.size());
    }

    private void createId3v2Tag(Mp3File mp3File) {
        if (!mp3File.hasId3v2Tag()) {
            ID3v1 id3v1Tag;
            ID3v2 id3v2Tag;
            if (mp3File.hasId3v1Tag()) {
                id3v1Tag = mp3File.getId3v1Tag();
            } else {
                id3v1Tag = null;
            }
            id3v2Tag = setId3v2Tags(id3v1Tag);
            mp3File.setId3v2Tag(id3v2Tag);
            try {
                mp3File.save(mp3File.getFilename());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            }
        }

    }

    private ID3v2 setId3v2Tags(ID3v1 tag) {
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
            id3v2Tag.setComposer("");
            id3v2Tag.setPublisher("");
            id3v2Tag.setOriginalArtist("");
            id3v2Tag.setAlbumArtist("Unknown");
            id3v2Tag.setCopyright("");
            id3v2Tag.setUrl("");
            id3v2Tag.setEncoder("");
        } else {
            id3v2Tag.setTrack((tag.getTrack() == null) ? "Unknown" : tag.getTrack());
            id3v2Tag.setArtist((tag.getArtist() == null) ? "Unknown" : tag.getArtist());
            id3v2Tag.setTitle((tag.getTitle() == null) ? "Unknown" : tag.getTitle());
            id3v2Tag.setAlbum((tag.getAlbum() == null) ? "Unknown" : tag.getAlbum());
            id3v2Tag.setYear((tag.getYear() == null) ? "Unknown" : tag.getYear());
            id3v2Tag.setGenre((tag.getGenre() < 0 && tag.getGenre() > 79) ? 12 : tag.getGenre());
            id3v2Tag.setComment((tag.getComment() == null) ? "" : tag.getComment());
            id3v2Tag.setLyrics("");
            id3v2Tag.setComposer("");
            id3v2Tag.setPublisher("");
            id3v2Tag.setOriginalArtist("");
            id3v2Tag.setAlbumArtist((tag.getArtist() == null) ? "Unknown" : tag.getArtist());
            id3v2Tag.setCopyright("");
            id3v2Tag.setUrl("");
            id3v2Tag.setEncoder("");
        }
        return id3v2Tag;
    }

    public ArrayList<Artist> getLibrary() {
        return new ArrayList<Artist>();
    }
}
