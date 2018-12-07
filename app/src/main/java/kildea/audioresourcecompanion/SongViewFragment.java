package kildea.audioresourcecompanion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import java.util.ArrayList;

public class SongViewFragment extends LibraryMenuFragment {

    private ArrayList<Mp3File> songs = new ArrayList<>();

    public SongViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = super.onCreateView(inflater, container, savedInstanceState);

        songs = MainActivity.library.collection;

        ListView column_listview = view.findViewById(R.id.column_headers);
        ListView song_listview = view.findViewById(R.id.song_list);

        column_listview.setAdapter(new ColumnHeaderAdapter());

        song_listview.setAdapter(new SongListAdapter());

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
                    MainActivity.ah.setTrack(songs.get(position));
                }
            });

            return view;
        }
    }

    class ColumnHeaderAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
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
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.song_entry, null);
            TextView songTitleTextView = view.findViewById(R.id.song_title);
            TextView artistTextView = view.findViewById(R.id.artist_title);
            TextView albumTextView = view.findViewById(R.id.album_title);

            songTitleTextView.setText("Title");
            artistTextView.setText("Artist");
            albumTextView.setText("Album");

            return view;
        }
    }
}
