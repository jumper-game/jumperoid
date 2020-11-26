package com.example.wlf.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.example.wlf.jumper.elements.back_ground;
import com.example.wlf.jumper.elements.GameClear;
import com.example.wlf.jumper.elements.Pipes;
import com.example.wlf.jumper.elements.Passaro;
import com.example.wlf.jumper.elements.Pontuacao;
import com.example.wlf.jumper.R;
import com.example.wlf.jumper.elements.GameOver;
import com.example.wlf.jumper.graphics.Tela;


public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Tela tela;
    //private Bitmap background;
    private back_ground BG;
    private Pipes canos;
    private Canvas canvas;
    private Passaro passaro;
    private Pontuacao pontuacao;
    private Som som;
    private Context context;
    private boolean cktouch=false;
    private Handler mainHandler;

    public Game( Context context, Handler mainHandler  )
    {
        super( context );
        this.context = context;
        tela = new Tela( context );
        this.BG =new back_ground(tela,context);
        inicializaElementos();
        setOnTouchListener( this );
        this.mainHandler = mainHandler;
    }

    private void inicializaElementos()
    {
        this.passaro = new Passaro(tela, context);
        this.pontuacao = new Pontuacao();
        this.canos = new Pipes( tela, pontuacao, context );
        BG.setX(0);
        som = new Som(context);
    }

    @Override
    public void run() {
        while ( isRunning ){

            if ( ! holder.getSurface().isValid() )
            {
                continue;
            }

            canvas = holder.lockCanvas();
            BG.update();
            BG.drawbg(canvas);

            if(!cktouch)passaro.cai2();
            else{
                passaro.cai();
            }

            if(pontuacao.passhurdlenum()>=40&&passaro.getxspot()<tela.getLargura()){
                passaro.xmove_end();//게임 클리어
                canos.move();
                canos.desenhaNo(canvas);
            }else{
                passaro.xmove();
                canos.move();
                canos.desenhaNo(canvas);
            }
            passaro.desenhaNo(canvas);
            pontuacao.desenhaNo(canvas);

            if(pontuacao.passhurdlenum()>=40&&passaro.getxspot()>tela.getLargura()){
                new GameClear(tela).drawClear(canvas);
                isRunning=false;
                Message msg = Message.obtain();
                msg.what = 0;
                mainHandler.sendMessage(msg);
            }

            if ( new VerificadorDeColisao(passaro, canos).temColisao() ) //게임 오버
            {
                som.tocaSom(Som.COLISAO);
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
                Message msg = Message.obtain();
                msg.what = 0;
                mainHandler.sendMessage(msg);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void inicia()
    {
        this.isRunning = true;
    }
    public void cancela()
    {
        this.isRunning = false;
    }

    @Override
    public boolean onTouch( View view, MotionEvent motionEvent ) {
        passaro.pula();
        cktouch=true;
        return false;
    }
    public void resetGame(){
        tela = new Tela( context );
        inicializaElementos();  //게임 시작하고 초기화하고 초기화시 필요한 객체 생성
        isRunning = true;
    }
}
