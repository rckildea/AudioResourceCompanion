package kildea.audioresourcecompanion;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.mpatric.mp3agic.BaseException;
import com.mpatric.mp3agic.BufferTools;
import com.mpatric.mp3agic.ID3Wrapper;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.mpatric.mp3agic.Version;

import java.io.IOException;
import java.util.ArrayList;

public class ArtistViewFragment extends LibraryMenuFragment {

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

        customRecycleAdapter = new CustomRecycleAdapter(this.getContext(), artists, images);

        artistView.setAdapter(customRecycleAdapter);



        /*String path = Environment.getExternalStorageDirectory().toString()+"/Music/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        String blah;
        if (directory.isDirectory()) {
            blah = "true";
        } else {
            blah = "false";
        }
        Log.d("abcdefg", blah);
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }*/

        // Inflate the layout for this fragment
        return view;
    }

}
