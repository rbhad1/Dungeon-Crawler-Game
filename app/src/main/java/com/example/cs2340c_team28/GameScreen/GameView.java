package com.example.cs2340c_team28.GameScreen;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
//    private MainThread thread;
    private CharacterSprite characterSprite;
    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        //thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.avdgreen));


//        thread.setRunning(true);
//        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event ) { return super.onTouchEvent(event); }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
//        while (retry) {
//            try {
//                thread.setRunning(false);
//                thread.join();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            retry = false;
//        }
    }

    public void update() {
        characterSprite.update();
    }
    @Override
    public void draw(Canvas canvas)
    {

        super.draw(canvas);
        if(canvas!=null) {
            characterSprite.draw(canvas);

        }
    }
}
