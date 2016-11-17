package com.jzap.squareboard.squareboard;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String mTag = "JZAP: MainActivity";

    GameManager mGameManager;
   // GameBoard mGameBoard;
    FrameLayout mMainLayout;
//    private Handler mHandler;

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
