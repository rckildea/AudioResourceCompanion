package kildea.audioresourcecompanion;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SongViewFragment extends Fragment {

    AudioHandler ah = new AudioHandler();

    private ArrayList<Mp3File> songs = new ArrayList<>();
    MediaPlayer mediaPlayer = new MediaPlayer();

    public SongViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song_view, container, false);

        songs = MainActivity.library.collection;

        ListView song_listview = view.findViewById(R.id.song_list);

        SongListAdapter songListAdapter = new SongListAdapter();

        song_listview.setAdapter(songListAdapter);

        return view;

    }

    class SongListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return songs.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.song_entry, null);
            ID3v2 tag = songs.get(position).getId3v2Tag();
            TextView songTitleTextView = view.findViewById(R.id.song_title);
            TextView artistTextView = view.findViewById(R.id.artist_title);
            TextView albumTextView = view.findViewById(R.id.album_title);

            songTitleTextView.setText(tag.getTitle());
            artistTextView.setText(tag.getAlbumArtist());
            albumTextView.setText(tag.getAlbum());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ah.playTrack(songs.get(position));
                }
            });

            return view;
        }
    }
}
