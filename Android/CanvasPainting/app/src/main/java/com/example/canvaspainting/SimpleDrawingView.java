package com.example.canvaspainting;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {
    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    public Paint drawPaint;
    // Store circles to draw each time the user touches down
    private List<Point> circlePoints;
    //Creates a new path variable
    Path path;
    Canvas canvas;
    public Bitmap bitmap;
    private Paint bitmapPaint;
    ArrayList<Path> pathList = new ArrayList<>();
    private int stateToSave;

    MainActivity mainClass = new MainActivity();

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        setFocusable(true);
        setFocusableInTouchMode(true);
        //circlePoints = new ArrayList<Point>();
        setupPaint();
    }

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);// change to fill
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //View given size
        super.onSizeChanged(w, h, oldw, oldh);
        BitmapFactory.Options decode_options = new BitmapFactory.Options();
        decode_options.inMutable = true;
        if(bitmap == null){
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            invalidate();
        }else{
            Bitmap temporary = Bitmap.createScaledBitmap(bitmap, w, h, true);
            bitmap = temporary;
            invalidate();
        }
        canvas = new Canvas(bitmap);
    }

    //Draws on the screen
    @Override
    protected void onDraw(Canvas canvas)  {
        //for (Point p : circlePoints) {canvas.drawCircle(p.x, p.y, 5, drawPaint);} // Draws the circle to draw on screen
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        //for (Path path: pathList) {
            canvas.drawPath(path, drawPaint);
        //}
    }

    // Append new circle each time user presses on screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX;
        float pointY;
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Starts a new line in the path
                pointX = event.getX();
                pointY = event.getY();
                path.reset();
                path.moveTo(pointX, pointY);
                //pathList.add(path);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                // Draws line between last point and this point
                pointX = event.getX();
                pointY = event.getY();
                path.lineTo(pointX, pointY);
                canvas.drawPath(path, drawPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
            default:
                return false;
        }
      //circlePoints.add(new Point(Math.round(pointX), Math.round(pointY)));
        postInvalidate();   // indicate view should be redrawn
        return true;        // Indicate we've consumed the touch
    }

    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);

        return bmp;
    }

}
