package com.example.karleinstein.tratan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Result extends Activity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    Button btnExit;
    TextView tvShow,tvhighScore;
    FileOutputStream fileOutputStream;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        btnExit=findViewById(R.id.btnExit);
        tvShow=findViewById(R.id.tvShow);
        tvhighScore=findViewById(R.id.tvhighScore);
        btnExit.setOnClickListener(this);
        Intent intent=getIntent();
        int a=intent.getIntExtra("fuck",0);
        tvShow.setText(""+a);
        sharedPreferences=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore=sharedPreferences.getInt("HIGH_SCORE",0);
        if (a>highScore)
        {
            tvhighScore.setText("High Score:"+a);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("HIGH_SCORE",a);
            editor.commit();
            Log.d("yo fuck you",getFilesDir().getAbsolutePath());
            save(String.valueOf(a));
        }
        else {
            tvhighScore.setText("High Score:"+highScore);
        }
    }

    private void save(String dulieu) {
        try {
            fileOutputStream=openFileOutput("high_score.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(dulieu.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setResource(R.drawable.triet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AppExit();
    }
    public void AppExit()
    {
        System.exit(0);
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    /*int pid = android.os.Process.myPid();=====> use this if you want to kill your activity. But its not a good one to do.
    android.os.Process.killProcess(pid);*/

    }
}
