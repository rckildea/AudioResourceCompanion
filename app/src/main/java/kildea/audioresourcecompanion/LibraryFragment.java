package kildea.audioresourcecompanion;


import android.app.Activity;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {

    int[] IMAGES = {R.drawable.person, R.drawable.album, R.drawable.music_note};
    String[] CATEGORIES = {"Artists", "Albums", "Songs"};
    Fragment[] CATEGORY_FRAGMENTS = {new ArtistViewFragment(), new ArtistViewFragment(), new ArtistViewFragment()};
    Library library = new Library();

    public LibraryFragment() {
        getUserLibrary();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        ListView libraryActionList = view.findViewById(R.id.library_action_list);
        CustomAdapter customAdapter = new CustomAdapter();

        libraryActionList.setAdapter(customAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    public void getUserLibrary() {

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
                    fragmentTransaction.replace(R.id.main_frame, CATEGORY_FRAGMENTS[position]);
                    fragmentTransaction.commit();
                }
            });

            return view;
        }
    }
}
