package com.example.iningke.myapplication.practice.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.iningke.myapplication.R;

/**
 * @author :  hxy
 * @since :  2018/9/2 23:13.
 */

public class MyTestPaintView extends View {
    public MyTestPaintView(Context context) {
        super(context);
        init();
    }


    public MyTestPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTestPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //当shader的区域小于 所要绘制的区域的时候，TileMode才能 展现出来其作用
    private Shader shader_linear_clamp = new LinearGradient(50, 50, 100, 100, Color.parseColor("#FFFFFF00"),
            Color.parseColor("#FF00FFFF"), Shader.TileMode.CLAMP);
    private Shader shader_linear_repeat = new LinearGradient(400, 50, 550, 100, Color.parseColor("#FFFFFF00"),
            Color.parseColor("#FF00FFFF"), Shader.TileMode.REPEAT);
    private Shader shader_linear_mirror = new LinearGradient(750, 50, 900, 100, Color.parseColor("#FFFFFF00"),
            Color.parseColor("#FF00FFFF"), Shader.TileMode.MIRROR);


    private Shader shader_radial = new RadialGradient(200, 550, 150, Color.parseColor("#FFFFFF00"),
            Color.parseColor("#FF00FFFF"), Shader.TileMode.CLAMP);

    private Shader shader_sweep = new SweepGradient(200, 900, Color.parseColor("#FFFFFF00"),
            Color.parseColor("#FF00FFFF"));
    private RectF rectF_clamp = new RectF(50, 50, 350, 350);
    private RectF rectF_repeat = new RectF(400, 50, 700, 350);
    private RectF rectF_mirror = new RectF(750, 50, 1050, 350);
    private Paint paint = new Paint();


    /*----------------------------------------BitmapShader--------------------------------------------*/
    private Bitmap bitmap_small;
    private Bitmap bitmap_big;

    private Shader shader_bitmap_repeat;
    private Shader shader_bitmap_clamp;
    private RectF rectF_bitmap;

    private Shader shader_compose_Over;
    private Shader shader_compose_Out;
    private RectF rectF_compose = new RectF(50, 1450, 350, 1750);
    private RectF rectF_compose_out = new RectF(400, 1450, 700, 1750);

    private void init() {
        bitmap_small = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmap_big = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_guide1);
        shader_bitmap_repeat = new BitmapShader(bitmap_small, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Matrix matrix = new Matrix();
        matrix.setScale(0.1F, 0.1F);
        bitmap_big =
                Bitmap.createBitmap(bitmap_big, 0, 0, bitmap_big.getWidth(), bitmap_big.getHeight(), matrix, true);
        shader_bitmap_clamp = new BitmapShader(bitmap_big, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        rectF_bitmap = new RectF(400, 1100, 700, 1400);
        Matrix matrix2 = new Matrix();
        matrix2.setScale(300.0F / bitmap_small.getWidth(), 300.0f / bitmap_small.getHeight());
        bitmap_small = Bitmap.createBitmap(bitmap_small, 0, 0, bitmap_small.getWidth(), bitmap_small.getHeight(), matrix2, true);
        shader_compose_Over = new ComposeShader(shader_bitmap_clamp, shader_bitmap_repeat, PorterDuff.Mode.SRC_OVER);
        rectF_compose_out = new RectF(400, 1450, 700, 1750);
        shader_compose_Out = new ComposeShader(shader_bitmap_clamp, shader_bitmap_repeat, PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGridLine(canvas);

        paint.setShader(shader_linear_clamp);
        canvas.drawOval(rectF_clamp, paint);

        paint.setShader(shader_linear_repeat);
        canvas.drawOval(rectF_repeat, paint);

        paint.setShader(shader_linear_mirror);
        canvas.drawOval(rectF_mirror, paint);

        paint.setShader(shader_radial);
        canvas.drawCircle(200, 550, 150, paint);

        paint.setShader(shader_sweep);
        canvas.drawCircle(200, 900, 150, paint);


        paint.setShader(shader_bitmap_repeat);
        canvas.drawCircle(200, 1250, 150, paint);

        paint.setShader(shader_bitmap_clamp);
        canvas.drawRect(rectF_bitmap, paint);

        paint.setShader(shader_compose_Over);
        canvas.drawRect(rectF_compose, paint);

        paint.setShader(shader_compose_Out);
        canvas.drawRect(rectF_compose_out, paint);
    }

    private void drawGridLine(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i += 50) {
            canvas.drawLine(i, 0, i, height, paint);
            canvas.drawText(i + "", i, 20, paint);
        }
        for (int i = 0; i < height; i += 50) {
            canvas.drawLine(0, i, width, i, paint);
            canvas.drawText(i + "", 0, i + 20, paint);
        }
    }
}
