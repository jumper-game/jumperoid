package com.example.wlf.jumper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.wlf.jumper.engine.BackgroundSoundService;
import com.example.wlf.jumper.engine.Game;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Game game;
    Button retry;
    Button start;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout container = (FrameLayout) findViewById(R.id.container);

        game = new Game(this, mainHandler);
        PlayBackgroundSound(this);
        retry = findViewById(R.id.retry);
        start = findViewById(R.id.start);
        retry.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);

        final ImageView how = (ImageView) findViewById(R.id.btn_how_to_play);
        ImageView how_to_play = (ImageView) findViewById(R.id.image_how);
        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setWindowHeight(900);
        imagePopup.setBackgroundColor(Color.parseColor("#ffd3d4"));
        imagePopup.setWindowWidth(900);
        imagePopup.setHideCloseIcon(true);
        imagePopup.setImageOnClickClose(true);
        imagePopup.initiatePopup(how_to_play.getDrawable());

        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePopup.viewPopup();
            }
        });

        final Thread thread = new Thread(game);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
		        container.addView(game);
                thread.start();
                start.setVisibility(View.GONE);
                how.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    Handler mainHandler =  new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0){

                retry.setVisibility(View.VISIBLE);
                retry.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        game.resetGame();
                        new Thread(game).start();
                        retry.setVisibility(View.GONE);
                    }
                });
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        game.inicia();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.cancela();
    }

    public void onBackPressed(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    public void PlayBackgroundSound(MainActivity view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }
}
