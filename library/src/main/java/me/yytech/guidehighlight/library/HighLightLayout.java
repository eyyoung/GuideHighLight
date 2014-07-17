package me.yytech.guidehighlight.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class HighLightLayout extends FrameLayout {

    private Paint mHightLightPaint;
    private Bitmap bitmap;
    private Canvas temp;

    private ArrayList<Target> mTargets = new ArrayList<Target>();
    private Bitmap mArrowUp;
    private Bitmap mArrowDown;

    /**
     * 构造器
     *
     * @param context 上下文
     */
    public HighLightLayout(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mHightLightPaint = new Paint();
        mHightLightPaint.setColor(Color.BLUE);
        mHightLightPaint.setAntiAlias(true);
        mHightLightPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mArrowUp = ((BitmapDrawable) getResources().getDrawable(R.drawable.name_test_guide_arrow)).getBitmap();
        mArrowDown = ((BitmapDrawable) getResources().getDrawable(R.drawable.name_test_guide_arrow_2)).getBitmap();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.TRANSPARENT);
        temp = new Canvas(bitmap);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        temp.drawARGB(230, 0, 0, 0);
        int size = mTargets.size();
        for (int i = 0; i < size; i++) {
            Target target = mTargets.get(i);
            RectF oval = new RectF(target.locationX, target.locationY, target.locationX + target.width, target.locationY + target.height);
            //高亮椭圆
            temp.drawOval(oval, mHightLightPaint);
            //箭头
            Matrix matrix = new Matrix();
            matrix.postRotate(45 + target.arrowAngle, 0, 0);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            switch (target.titlePosition) {
                case above:
                    int dy = target.locationY - mArrowDown.getHeight() - 20 + target.arrowYOffset;
                    int dx = target.locationX + 20 + target.arrowXOffset;
                    matrix.postTranslate(dx, dy);
                    temp.drawBitmap(mArrowDown, matrix, paint);
                    // 文字
                    temp.drawText(target.titleText, dx + target.titleXOffset, dy + target.titleYOffset, target.titlePaint);
                    break;
                case below:
                    int dy2 = target.locationY + target.height + mArrowDown.getHeight() + 20 + target.arrowYOffset;
                    int dx2 = target.locationX + 20 + target.arrowXOffset;
                    matrix.postTranslate(dx2, dy2);
                    temp.drawBitmap(mArrowUp, matrix, paint);
                    temp.drawText(target.titleText, dx2 + target.titleXOffset, dy2 + target.titleYOffset, target.titlePaint);
                    break;
            }
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
    }


    public void addTarget(final Target... targets) {
        for (int i = 0; i < targets.length; i++) {
            Target target = targets[i];
            if (target.view == null) {
                throw new IllegalArgumentException();
            }
            mTargets.add(target);
        }
    }

}
