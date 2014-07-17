package me.yytech.guidehighlight.library;

import android.graphics.Paint;
import android.view.View;

/**
 * Created by Young on 2014/7/17.
 */
public class Target {
    View view;
    int locationX;
    int locationY;
    int width;
    int height;
    Paint titlePaint;
    String titleText;
    TitlePosition titlePosition;
    float arrowAngle;//是否翻转
    public int titleYOffset;
    public int titleXOffset;
    public int arrowXOffset;
    public int arrowYOffset;

    private Target() {

    }

    public enum TitlePosition {
        above,
        below
    }

    public static class Builder {

        Target target;

        public Builder() {
            target = new Target();
        }

        public Target build() {
            target.view.post(new Runnable() {
                @Override
                public void run() {
                    View targetView = target.view;
                    int location[] = new int[2];
                    targetView.getLocationInWindow(location);
                    target.locationX = location[0];
                    target.locationY = location[1];
                    target.width = targetView.getWidth();
                    target.height = targetView.getHeight();
                }
            });
            return target;
        }

        public Builder setArrowAngle(float angle) {
            target.arrowAngle = angle;
            return this;
        }

        public Builder setTitleYOffset(int offset) {
            target.titleYOffset = offset;
            return this;
        }

        public Builder setTitleXOffset(int offset) {
            target.titleXOffset = offset;
            return this;
        }

        public Builder setArrowYOffset(int offset) {
            target.arrowYOffset = offset;
            return this;
        }

        public Builder setArrowXOffset(int offset) {
            target.arrowXOffset = offset;
            return this;
        }

        public Builder setView(View targetView) {
            target.view = targetView;
            return this;
        }

        public Builder setTitle(String title) {
            target.titleText = title;
            return this;
        }

        public Builder setTitlePosition(TitlePosition position) {
            target.titlePosition = position;
            return this;
        }

        public Builder setTitlePaint(Paint paint) {
            target.titlePaint = paint;
            return this;
        }
    }
}
