package kildea.audioresourcecompanion;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity testActivity = null;

    @Before
    public void setUp() throws Exception {
        testActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View v = testActivity.findViewById(R.id.main_nav);
        assertNotNull(v);
    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }
}