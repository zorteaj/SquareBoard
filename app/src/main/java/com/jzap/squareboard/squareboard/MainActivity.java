package com.jzap.squareboard.squareboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    private static final String mTag = "JZAP: MainActivity";

    private GameManager mGameManager;
    private FrameLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainLayout = (FrameLayout) findViewById(R.id.activity_main);
        mMainLayout.setClipToOutline(false);

        mGameManager = new GameManager(this);
    }

    public ViewGroup getMainLayout() {
        return mMainLayout;
    }

}
