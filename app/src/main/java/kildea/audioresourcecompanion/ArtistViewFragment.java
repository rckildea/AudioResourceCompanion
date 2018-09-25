package kildea.audioresourcecompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ArtistViewFragment extends Fragment {

    private ArrayList<String> artists = new ArrayList<String>();
    private ArrayList<Integer> images = new ArrayList<Integer>();

    private RecyclerView artistView;
    private GridLayoutManager gridLayoutManager;
    private CustomRecycleAdapter customRecycleAdapter;


    public ArtistViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        artists.add("La Dispute");
        artists.add("The Wonder Years");
        images.add(R.drawable.testband1);
        images.add(R.drawable.testband2);

        View view = inflater.inflate(R.layout.fragment_artist_view, container, false);

        gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);

        artistView = view.findViewById(R.id.artist_list);
        artistView.setLayoutManager(gridLayoutManager);

        customRecycleAdapter = new CustomRecycleAdapter(this.getContext(), artists, images);

        artistView.setAdapter(customRecycleAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
