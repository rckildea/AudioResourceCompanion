package kildea.audioresourcecompanion;

import android.os.Environment;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class LibraryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMusicFolder() {

        String music_path = Environment.getExternalStorageDirectory().toString()+"/Music/";
        File music_folder = new File(music_path);

        if (!music_folder.exists()) {
            music_folder.mkdirs();
        }
        assertTrue(music_folder.exists());
    }

    @Test
    public void testId3v2Tag() {
        File song_file = new File(Environment.getExternalStorageDirectory().toString()+"/Music/The Wonder Years - There, There.mp3");
        Mp3File temp = null;
        try {
            temp = new Mp3File(song_file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

        assert temp != null;
        assertTrue(temp.getId3v2Tag() != null);
    }

    @After
    public void tearDown() throws Exception {
    }
}