package com.example.a2dgraphicinandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;
import java.util.Vector;

public class DefaultScreenView extends View {

    private Paint paint;
    private final int HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    private final int WIDTH  = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int x = 500;
    private int y = 500;
    private int radius = 100;
    private int ball_color = 0xffff0000;
    private V2 speed = new V2(1,1);
    private Context c;
    private boolean isTouch = false;
    private long last_color_update = System.currentTimeMillis();
    private V2 start = new V2(0,0);
    private V2 end = new V2(0,0);
    private V2 diff;
    private boolean isCollision = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //super.onTouchEvent(event);
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                //Toast.makeText(c, "ACTION_DOWN AT COORDS "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                isTouch = true;
                start = new V2(X,Y);
                end = start;           // Initially end is start
                break;

            case MotionEvent.ACTION_MOVE:
                //Toast.makeText(c, "MOVE "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_UP:
                //Toast.makeText(c, "ACTION_UP "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                isTouch = false;
                end = new V2(X,Y);
                break;
        }
        return true;
    }

    private MediaPlayer pft;
    private Random random = new Random();

    public DefaultScreenView(Context context) {
        super(context);
        c = context;
        paint = new Paint();
        pft = MediaPlayer.create(c,R.raw.pft);
    }

    private void update() {

        if(isTouch && (System.currentTimeMillis() - last_color_update) > 250){
            last_color_update = System.currentTimeMillis();
            ball_color = Color.argb(0xff, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            isTouch = false;
        }

        if (x + radius >= WIDTH ) {
            x = WIDTH - radius - 1;
            speed.x = -speed.x;
            pft.seekTo(0);
            pft.start();
            isCollision = true;
        }
        if(x - radius <= 0){
            x = radius + 1;
            speed.x = -speed.x;
            pft.seekTo(0);
            pft.start();
            isCollision = true;
        }
        if (y + radius >= HEIGHT  ) {
            y = HEIGHT - radius - 1;
            speed.y = -speed.y;
            pft.seekTo(0);
            pft.start();
            isCollision = true;
        }
        if(y - radius <= 0){
            y = radius + 1;
            speed.y = -speed.y;
            pft.seekTo(0);
            pft.start();
            isCollision = true;
        }

        // If there was a collision then we will not add the extra velocity.
        if(isCollision){
            isCollision = false;
            x += speed.x;
            y += speed.y;
            ball_color = Color.argb(0xff, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            return;
        }

        diff = end.sub(start).scale(0.10f);
        start = end;

        if((diff.x + speed.x < radius && diff.x > 0) || (diff.x < speed.x && diff.x < 0)){
            speed.x += diff.x;
        }
        if((diff.y + speed.y < radius && diff.y > 0) || (diff.y < speed.y && diff.y < 0)) {
            speed.y += diff.y;
        }

        x += speed.x;
        y += speed.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        update();
        paint.setColor(ball_color);
        paint.setTextSize(40);
        canvas.drawCircle(x,y,radius,paint);

        invalidate();
    }


}
