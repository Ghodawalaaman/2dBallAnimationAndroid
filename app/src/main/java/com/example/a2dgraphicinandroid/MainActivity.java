package com.example.a2dgraphicinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DefaultScreenView view = new DefaultScreenView(this);
        setContentView(view);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int X = (int) event.getX();
//        int Y = (int) event.getY();
//        int eventaction = event.getAction();
//
//        switch (eventaction) {
//            case MotionEvent.ACTION_DOWN:
//                Toast.makeText(this, "ACTION_DOWN AT COORDS "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
//                isTouch = true;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                Toast.makeText(this, "MOVE "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
//                break;
//
//            case MotionEvent.ACTION_UP:
//                Toast.makeText(this, "ACTION_UP "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }
}