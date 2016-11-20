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

    protected RulesManager mRulesManager;
    // Whether or not to message this rule's user in real-time when the rule is updated
    protected boolean mPublishUpdates;
    protected Handler mHandler;

    public RuleProperty(RulesManager rulesManager, boolean publishUpdates) {
        this(rulesManager, publishUpdates, null);
    }

    public RuleProperty(RulesManager rulesManager, boolean publishUpdates, Handler handler) {
        mRulesManager = rulesManager;
        mPublishUpdates = publishUpdates;
        mHandler = handler;
    }

}
