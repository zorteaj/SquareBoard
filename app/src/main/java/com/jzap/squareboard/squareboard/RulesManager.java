package com.jzap.squareboard.squareboard;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by JZ_W541 on 11/16/2016.
 */

public class RulesManager {

    private static final String mTag = "JZAP: Rules Manager";

    MainActivity mMainActivity;
    // TODO: It would probably be better to separate the UI logic from the rules management logic
    FrameLayout mMainLayout;
    CardView mMainCard;
    LinearLayout mMainLinearLayout;

    public RulesManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        setUp();
    }

    private void setUp() {
        mMainLayout = (FrameLayout) (mMainActivity.findViewById(R.id.activity_main));

        mMainCard = new CardView(mMainActivity);
        FrameLayout.LayoutParams l = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMainCard.setLayoutParams(l);
        mMainCard.setElevation(20.0f);

        mMainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        mMainLinearLayout = new LinearLayout(mMainActivity);
        ViewGroup.LayoutParams lpp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMainLinearLayout.setLayoutParams(lpp);
        mMainCard.addView(mMainLinearLayout);
    }

    public void show() {
        mMainLayout.addView(mMainCard);
    }

    public void hide() {
        mMainLayout.removeView(mMainCard);
    }

    public RuleProperty addRuleProperty(Handler handler) {
        return new RuleProperty(this, handler);
    }

    public MainActivity getMainActivity() {
        return mMainActivity;
    }

    public LinearLayout getMainLayout() {
        return mMainLinearLayout;
    }
}
