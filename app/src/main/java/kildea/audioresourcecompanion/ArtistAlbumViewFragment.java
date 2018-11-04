package kildea.audioresourcecompanion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAlbumViewFragment extends LibraryMenuFragment {

    private Artist artist;
    private ArrayList<Album> albums = new ArrayList<Album>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artist_album_view, container, false);

        artist = CustomArtistAdapter.fragment_artist;
        albums.addAll(artist.getAlbums());

        ImageButton back_button = view.findViewById(R.id.back_arrow);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });



        return view;
    }

    class AlbumListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return artist.getAlbums().size();
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
            view = getLayoutInflater().inflate(R.layout.album_entry, null, false);

            ImageView albumImageView = view.findViewById(R.id.album_image);
            TextView albumTextView = view.findViewById(R.id.album_title);

            albumTextView.setText(albums.get(position).getTitle());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //MainActivity.ah.playTrack();
                }
            });

            return view;
        }
    }

}
