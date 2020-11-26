package com.example.wlf.jumper.graphics;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import com.example.wlf.jumper.R;

public class Cores {

    public static Paint getCorDoPassaro()
    {
        Paint vermelho = new Paint();
        vermelho.setColor( 0xFFFF0000 );

        return vermelho;
    }

    public static Paint getCorDoCano()
    {
        Paint verde = new Paint();
        verde.setColor( 0xFF00FF00 );

        return verde;
    }

    public static Paint getCorDaPontuacao()
    {
        Paint branco =  new Paint();
        branco.setColor( 0xFFFFFFFF );
        branco.setTextSize(80);
        branco.setTypeface( Typeface.DEFAULT_BOLD );
        branco.setTypeface( Typeface.create("font/noto_sans_bold", Typeface.BOLD) );

        return branco;
    }

    public static Paint getCorDoGameOver() {
        Paint vermelho = new Paint();
        vermelho.setColor(0xFFFF0000);
        vermelho.setTextSize(100);
        vermelho.setTypeface( Typeface.create("font/noto_sans_bold", Typeface.BOLD) );

        return vermelho;
    }

    public static Paint pntgameclear() {
        Paint vermelho = new Paint();
        vermelho.setColor(0xFF006600);
        vermelho.setTextSize(100);
        vermelho.setTypeface( Typeface.create("font/noto_sans_bold", Typeface.BOLD) );

        return vermelho;
    }

}
