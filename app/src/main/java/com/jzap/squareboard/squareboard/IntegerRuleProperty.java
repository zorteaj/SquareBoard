package com.jzap.squareboard.squareboard;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by JZ_W541 on 11/19/2016.
 */

public class IntegerRuleProperty extends RuleProperty {

    private RadioButton mButton;
    private int mValue;

    public IntegerRuleProperty(RulesManager rulesManager, boolean publishUpdates, Handler handler) {
        super(rulesManager, publishUpdates, handler);
        mValue = Color.BLUE;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mButton = new RadioButton(mRulesManager.getMainActivity());
        mButton.setLayoutParams(lp);

        if(mPublishUpdates || (mHandler != null)) {
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    swapValue();
                    mHandler.obtainMessage(MessageTypes.OTHER, IntegerRuleProperty.this).sendToTarget();
                }
            });
        }

        mRulesManager.getMainLayout().addView(mButton);
    }

    public IntegerRuleProperty(RulesManager rulesManager, boolean publishUpdates) {
        this(rulesManager, publishUpdates, null);
    }

    public int getValue() {
       return mValue;
    }

    private void swapValue() {
        if(mValue == Color.BLUE) {
            mValue = Color.RED;
        } else {
            mValue = Color.BLUE;
        }
    }
}
