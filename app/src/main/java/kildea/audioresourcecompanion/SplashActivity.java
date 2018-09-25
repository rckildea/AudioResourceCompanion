package kildea.audioresourcecompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.i("abc", "abc");

        if (!isExternalStorageWritable()) {
            AlertDialog.Builder builder  = new AlertDialog.Builder(this);
            builder.setTitle("ERROR");
            builder.setMessage("Read/write permissions for this device are not enabled.  The application will not work as intended.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showSplashScreen();
                }
            });
            builder.show();
        } else {
            showSplashScreen();
        }
    }

    public void showSplashScreen() {
        final int SPLASH_TIMER = 1000; // in ms
        /* Splash screen */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this,  MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIMER);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
