package com.jzap.squareboard.squareboard;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by JZ_W541 on 11/16/2016.
 */

public class RuleProperty {

    //View mView;
    private RulesManager mRulesManager;
    private RadioButton mButton;
    Handler mHandler;

    public RuleProperty(RulesManager rulesManager, Handler handler) {
        mRulesManager = rulesManager;
        mHandler = handler;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mButton = new RadioButton(mRulesManager.getMainActivity());
        mButton.setLayoutParams(lp);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.obtainMessage().sendToTarget();
            }
        });

        mRulesManager.getMainLayout().addView(mButton);
    }

    public int getValue() {
        if(mButton.isChecked()) {
            return Color.RED;
        } else {
            return Color.BLUE;
        }
    }
}
