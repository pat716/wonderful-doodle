package com.example.psweeney.wonderfuldoodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by psweeney on 3/22/16.
 */
public class DoodleView extends View {
    private Paint _paintDoodle = new Paint();
    private Path _path = new Path();
    ArrayList<Map.Entry> _pathList = new ArrayList<>();
    ArrayList<Map.Entry> _redoList = new ArrayList<>();

    public DoodleView(Context context){
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        _paintDoodle.setColor(Color.BLACK);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeWidth(5);
    }

    public int getColor(){
        return _paintDoodle.getColor();
    }

    public void setColor(int color){
        _paintDoodle.setColor(color);
        invalidate();
    }

    public int getStrokeAlpha(){
        return Color.alpha(_paintDoodle.getColor());
    }

    public void setStrokeAlpha(int alpha){
        int red = Color.red(_paintDoodle.getColor());
        int green = Color.green(_paintDoodle.getColor());
        int blue = Color.blue(_paintDoodle.getColor());
        setColor(Color.argb(alpha, red, green, blue));
    }

    public int getStrokeRed(){
        return Color.red(_paintDoodle.getColor());
    }

    public void setStrokeRed(int red){
        int alpha = Color.alpha(_paintDoodle.getColor());
        int green = Color.green(_paintDoodle.getColor());
        int blue = Color.blue(_paintDoodle.getColor());
        setColor(Color.argb(alpha, red, green, blue));
    }

    public int getStrokeGreen(){
        return Color.green(_paintDoodle.getColor());
    }

    public void setStrokeGreen(int green){
        int alpha = Color.alpha(_paintDoodle.getColor());
        int red = Color.red(_paintDoodle.getColor());
        int blue = Color.blue(_paintDoodle.getColor());
        setColor(Color.argb(alpha, red, green, blue));
    }

    public int getStrokeBlue(){
        return Color.blue(_paintDoodle.getColor());
    }

    public void setStrokeBlue(int blue){
        int alpha = Color.alpha(_paintDoodle.getColor());
        int red = Color.red(_paintDoodle.getColor());
        int green = Color.green(_paintDoodle.getColor());
        setColor(Color.argb(alpha, red, green, blue));
    }

    public float getStrokeWidth(){
        return _paintDoodle.getStrokeWidth();
    }

    public void setStrokeWidth(float strokeWidth){
        _paintDoodle.setStrokeWidth(strokeWidth);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(Map.Entry<Path, Paint> entry : _pathList){
            canvas.drawPath(entry.getKey(), entry.getValue());
        }
        canvas.drawPath(_path, _paintDoodle);

    }

    public boolean undoPath(){
        if(_pathList.size() > 0){
            Map.Entry<Path, Paint> entry = _pathList.remove(_pathList.size() - 1);
            _redoList.add(entry);
            invalidate();
        }

        if(_pathList.size() <= 0){
            return false;
        }

        return true;
    }

    public boolean redoPath(){
        if(_redoList.size() > 0){
            Map.Entry<Path, Paint> entry = _redoList.remove(_redoList.size() - 1);
            _pathList.add(entry);
            invalidate();
        }

        if(_redoList.size() <= 0){
            return false;
        }

        return true;
    }

    public void clearPaint(){
        _pathList.clear();
        _path = new Path();
        _pathList.add(new PathPaintEntry(_path, new Paint(_paintDoodle)));
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        if(motionEvent == null){
            return true;
        }
        float touchX = motionEvent.getX(), touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                _path = new Path();
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                _pathList.add(new PathPaintEntry(_path, new Paint(_paintDoodle)));
                _redoList.clear();
                _path = new Path();
                break;
            default:
                break;
        }

        /*
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        */
        invalidate();
        return true;
    }

    private static class PathPaintEntry implements Map.Entry{
        private Path path;
        private Paint paint;

        public PathPaintEntry(Path path, Paint paint){
            this.path = path;
            this.paint = paint;
        }

        @Override
        public Path getKey() {
            return path;
        }

        @Override
        public Paint getValue() {
            return paint;
        }

        @Override
        public Object setValue(Object object) {
            if(object == null || !(object instanceof Paint)){
                return paint;
            }
            Object prev = paint;
            this.paint = (Paint) object;
            return prev;
        }
    }
}
