package com.jzap.squareboard.squareboard;

import android.view.MotionEvent;

/**
 * Created by JZ_W541 on 11/5/2016.
 */

public class Utils {

    public static class Gestures {

        private static final String mTag = "JZAP: Gestures";

        public Gestures() {}

        // returns angle in degrees (angles based on unit circle - right is 0 degrees)
        public static float flipAngle(MotionEvent start, MotionEvent event) {
            float uX = Math.abs(start.getX() - event.getX());
            float uY = Math.abs(start.getY() - event.getY());

            float vY = 0.0f;
            float vX = 1.0f;

            if (start.getX() > event.getX()) {
                uX = uX * -1.0f;
            }

            if (start.getY() < event.getY()) {
                uY = uY * -1.0f;
            }

            float angle = (float) Math.toDegrees(Math.acos((uX * vX + uY * vY) / (Math.sqrt((uX * uX) + (uY * uY)) * Math.sqrt((vX * vX) + (vY * vY)))));

            if (uY < 0) {
                angle = 360 - angle;
            }

            return angle;
        }

        // returns whether the angle is within the range defined by limit1 and limit2 (inclusive)
        // limit1 is the start of the angle, where start to end traverses the circle counter-clockwise
        // TODO: This might choke on different uses of 0 vs. 360
        public static boolean angleInRange(float angle, float limit1, float limit2) {
            float small = limit1;
            float large = limit2;

            // if angle crosses 0 degrees, it needs to be handled as a special case
            if(limit1 > limit2) {
                if(angle >= limit1 || angle <= limit2) {
                    return true;
                }
                return false;
            } else {
                if(angle >= limit1 && angle <= limit2) {
                    return true;
                }
                return false;
            }

        }


    }

}