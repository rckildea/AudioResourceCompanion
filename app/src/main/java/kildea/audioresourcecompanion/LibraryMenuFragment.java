package kildea.audioresourcecompanion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public abstract class LibraryMenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song_view, container, false);
        Toolbar toolbar = view.findViewById(R.id.library_toolbar);
        ImageButton import_button = view.findViewById(R.id.import_item);
        ImageButton back_button = view.findViewById(R.id.back_arrow);
        import_button.setOnClickListener(this);
        back_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.import_item:
                System.out.print("abcdefg");
                break;

            case R.id.back_arrow:
                getFragmentManager().popBackStack();
                break;

            default:
                break;
        }
    }
}
