package kildea.audioresourcecompanion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements CustomFragmentManager {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;

    protected static Library library;
    protected static AudioHandler ah = new AudioHandler();
    protected SpotifyHandler spotifyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = new Library();
        mainNav = findViewById(R.id.main_nav);
        mainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        setActiveFragment(homeFragment, "Home"); // Set initial screen to home screen

        spotifyHandler = new SpotifyHandler(this);

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_home:
                        setActiveFragment(homeFragment, "Home");
                        return true;
                    case R.id.nav_library:
                        setActiveFragment(new LibraryFragment(), "Library");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        spotifyHandler.connect(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        spotifyHandler.disconnect();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        spotifyHandler.getRequestResults(requestCode, resultCode, intent);
    }

    public FrameLayout getMainFrame() {
        return mainFrame;
    }

    public void setActiveFragment(Fragment fragment, String fragment_tag) {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = sfm.beginTransaction();
        Fragment check_fragment = sfm.findFragmentByTag(fragment_tag);

        if (check_fragment == null) {
            fragmentTransaction.replace(R.id.main_frame, fragment, fragment_tag);
        } else {
            fragmentTransaction.replace(R.id.main_frame, check_fragment);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
