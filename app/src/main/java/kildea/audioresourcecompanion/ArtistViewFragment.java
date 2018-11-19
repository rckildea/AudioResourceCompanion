package kildea.audioresourcecompanion;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ArtistViewFragment extends LibraryMenuFragment {

    private RecyclerView artistView;
    private GridLayoutManager gridLayoutManager;
    private CustomArtistAdapter customArtistAdapter;
    private Artist current_artist = null;


    public ArtistViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artist_view, container, false);


        ImageButton back_button = view.findViewById(R.id.back_arrow);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);

        artistView = view.findViewById(R.id.artist_list);
        artistView.setLayoutManager(gridLayoutManager);

        customArtistAdapter = new CustomArtistAdapter(this.getContext(), MainActivity.library.getLibrary());

        artistView.setAdapter(customArtistAdapter);

        return view;
    }

}
