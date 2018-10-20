package kildea.audioresourcecompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {

    private int[] IMAGES = {R.drawable.person, R.drawable.album, R.drawable.music_note};
    private String[] CATEGORIES = {"Artists", "Albums", "Songs"};
    private ArrayList<Fragment>  category_fragments = new ArrayList<>();

    public LibraryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_library, container, false);

        ArtistViewFragment artist_view = new ArtistViewFragment();
        SongViewFragment song_view = new SongViewFragment();
        category_fragments.add(artist_view);
        category_fragments.add(song_view);
        category_fragments.add(song_view);

        ListView libraryActionList = view.findViewById(R.id.library_action_list);
        CustomAdapter customAdapter = new CustomAdapter();

        libraryActionList.setAdapter(customAdapter);

        // Inflate the layout for this fragment
        return view;
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
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
            view = getLayoutInflater().inflate(R.layout.library_menu, null);
            ImageView imageView = view.findViewById(R.id.library_symbol);
            TextView textView = view.findViewById(R.id.library_categories);

            imageView.setImageResource(IMAGES[position]);
            textView.setText(CATEGORIES[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, category_fragments.get(position));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            return view;
        }
    }
}
