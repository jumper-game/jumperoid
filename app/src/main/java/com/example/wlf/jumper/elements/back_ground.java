package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.*;
import com.example.wlf.jumper.R;
import android.graphics.Bitmap;
import com.example.wlf.jumper.graphics.Tela;

public class back_ground {
    static final int Scroll_Speed=-6;
    private float x_scroll=0;
    private int x=0,y=0;
    private int displaywidth;
    private int bgwidth;

    public Bitmap BG;

    public back_ground(Tela tela,Context context){
        this.displaywidth=tela.getLargura();
         Bitmap bg =BitmapFactory.decodeResource(context.getResources(),R.drawable.background4 );
         this.bgwidth=bg.getWidth();
         this.BG = Bitmap.createScaledBitmap( bg, bg.getWidth(), tela.getAltura(), false );
}

    public void drawbg( Canvas canvas )
    {
        canvas.drawBitmap( this.BG,x, y, null );
    }
    public void update(){

        if(this.x>-(this.bgwidth-this.displaywidth)){
            this.x=this.x+Scroll_Speed;
        }
    }
    public void setX(int x){
        this.x = x;
    }
}
