package com.jzap.squareboard.squareboard;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by JZ_W541 on 11/10/2016.
 */

public class LinearLayoutNoClip extends LinearLayout {
    public LinearLayoutNoClip(Context context) {
        super(context);
        setClipToPadding(false);
        setClipChildren(false);
        setClipToOutline(false);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
    }
}
