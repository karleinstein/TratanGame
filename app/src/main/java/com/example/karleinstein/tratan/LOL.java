package com.example.karleinstein.tratan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LOL extends Activity {
    private TextView tvScore,Start;
    private ImageView Occho,blue,red,yellow,bomb;
    private int boxY;
    private Handler handler=new Handler();
    private Timer timer=new Timer();
    boolean trash=false;
    boolean start=false;
    private int framHeight;
    private int boxSize;
    int score;
    int redX,redY,yellowX,yellowY,blueX,blueY,screenWidth,screenHeight,bombX,bombY;
    //int boxSpeed,redSpeed,yellowSpeed,blueSpeed;
    int fuck=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        tvScore = findViewById(R.id.tvScore);
        Start = findViewById(R.id.Start);
        Occho = findViewById(R.id.Occho);
        blue = findViewById(R.id.blue);
        red = findViewById(R.id.red);
        bomb=findViewById(R.id.bomb);
        yellow = findViewById(R.id.yellow);
        red.setX(-80);
        red.setY(-80);
        blue.setX(-80);
        blue.setY(-80);
        yellow.setX(-80);
        yellow.setY(-80);
        bomb.setX(-120);
        bomb.setY(-120);
        //Start.setVisibility(View.INVISIBLE);
        //boxY = 500;
        Intent intent=getIntent();
        int gold1=intent.getIntExtra("ja",0);
        int gold2=intent.getIntExtra("ja1",0);
        int gold3=intent.getIntExtra("ja2",0);
        if(gold1==1)
        {
            Occho.setImageResource(R.drawable.trash);
        }
        if(gold2==2)
        {
            Occho.setImageResource(R.drawable.loz);
        }
        if(gold3==3)
        {
            Occho.setImageResource(R.drawable.vkl);
        }
        WindowManager wm=getWindowManager();
        Display disp=wm.getDefaultDisplay();
        Point point=new Point();
        disp.getSize(point);
        screenWidth=point.x;
        screenHeight=point.y;
    }
    public void lol() {
        checkHit();
        redX-=16;
        if (redX<0)
        {
            redX=screenWidth+10;
            redY= (int) Math.floor(Math.random()*(framHeight-red.getHeight()));
        }
        red.setX(redX);
        red.setY(redY);
        bombX-=16;
        if (bombX<0)
        {
            bombX=screenWidth+10;
            bombY= (int) Math.floor(Math.random()*(framHeight-bomb.getHeight()));
        }
        bomb.setX(bombX);
        bomb.setY(bombY);
        yellowX-=16;
        if (yellowX<0)
        {
            yellowX=screenWidth+10;
            yellowY= (int) Math.floor(Math.random()*(framHeight-yellow.getHeight()));
        }
        yellow.setX(yellowX);
        yellow.setY(yellowY);
        blueX-=20;
        if (blueX<0)
        {
            blueX=screenWidth+20;
            blueY=(int) Math.floor(Math.random()*(framHeight-blue.getHeight()));
        }
        blue.setX(blueX);
        blue.setY(blueY);
        if (trash==true)
        {
            boxY-=20;
        }
        if (trash==false){
            boxY+=20;
        }
        if (boxY<0) boxY=0;
        if (boxY>framHeight-boxSize) boxY=framHeight-boxSize;
        Occho.setY(boxY);
        tvScore.setText("Score:"+score);
    }
    public void checkHit()
    {
        int yellowcenterX=yellowX+yellow.getWidth()/2;
        int yellowcenterY=yellowY+yellow.getHeight()/2;
        if(0<=yellowcenterX && yellowcenterX<=boxSize && boxY<=yellowcenterY && yellowcenterY<=boxY+boxSize)
        {
            score+=10;
            yellowX=-10;
        }
        int bombcenterX=bombX+bomb.getWidth()/2;
        int bombcenterY=bombY+bomb.getHeight()/2;
        if(0<=bombcenterX && bombcenterX<=boxSize && boxY<=bombcenterY && bombcenterY<=boxY+boxSize)
        {
            timer.cancel();
            timer=null;
            Intent intent=new Intent(LOL.this,Result.class);
            intent.putExtra("fuck",score);
            startActivity(intent);
        }
        int bluecenterX=blueX+blue.getWidth()/2;
        int bluecenterY=blueY+blue.getHeight()/2;
        if(0<=bluecenterX && bluecenterX<=boxSize && boxY<=bluecenterY && bluecenterY<=boxY+boxSize)
        {
            score+=30;
            blueX=-10;
        }
        int redcenterX=redX+red.getWidth()/2;
        int redcenterY=redY+red.getHeight()/2;
        if(0<=redcenterX && redcenterX<=boxSize && boxY<=redcenterY && redcenterY<=boxY+boxSize)
        {
            score-=50;
            if (score<0) score=0;
            redX=-10;
            fuck++;
            if (fuck==3)
            {
                timer.cancel();
                timer=null;
                Intent intent=new Intent(LOL.this,Result.class);
                intent.putExtra("fuck",score);
                startActivity(intent);
            }
        }
    }
    @Override
    public boolean onTouchEvent ( final MotionEvent event){
        if(start==false) {
            start = true;
            FrameLayout frameLayout=findViewById(R.id.frame);
            framHeight=frameLayout.getHeight();
            boxY= (int) Occho.getY();
            boxSize=Occho.getHeight();

            Start.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lol();
                        }
                    });
                }
            }, 0, 20);
        }
        else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                trash=true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                trash=false;
            }
        }

        return true;
    }


}