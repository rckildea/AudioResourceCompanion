package kildea.audioresourcecompanion;
import android.content.Intent;
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
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public abstract class LibraryMenuFragment extends Fragment implements View.OnClickListener {

    private final int REQUEST_CODE_PICK_DIR = 1;
    private final int REQUEST_CODE_PICK_FILE = 2;

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
                Intent fileExploreIntent = new Intent(
                        FileBrowserActivity.INTENT_ACTION_SELECT_DIR,
                        null,
                        this.getActivity(),
                        FileBrowserActivity.class
                );
//  fileExploreIntent.putExtra(
//  	ua.com.vassiliev.androidfilebrowser.FileBrowserActivity.startDirectoryParameter,
//      "/sdcard"
//  );//Here you can add optional start directory parameter, and file browser will start from that directory.
                startActivityForResult(
                        fileExploreIntent,
                        REQUEST_CODE_PICK_DIR
                );
                break;

            case R.id.back_arrow:
                getFragmentManager().popBackStack();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_DIR) {
            if (resultCode == RESULT_OK) {
                Log.d("abc", "Okay");

            } else {
                Log.d("abc", "No");
            }
        }

        if (requestCode == REQUEST_CODE_PICK_FILE) {
            if(resultCode == RESULT_OK) {
                Log.d("abc", "Okay");
            } else {//if(resultCode == this.RESULT_OK) {
                Log.d("abc", "No");
            }
        }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
