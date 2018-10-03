package kildea.audioresourcecompanion;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;

    protected static Library library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        library = new Library();
        mainNav = findViewById(R.id.main_nav);
        mainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        setActiveFragment(homeFragment); // Set initial screen to home screen
        libraryFragment = new LibraryFragment();

        //save_test();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_home:
                        setActiveFragment(homeFragment);
                        return true;
                    case R.id.nav_library:
                        setActiveFragment(libraryFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setActiveFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    /*public void save_test() {
        String text = "abcdefgh";
        FileOutputStream fos = null;
        File abc = new File("abc.txt");
        if (!abc.exists()) {
            try {
                abc.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fos = openFileOutput("abc.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
