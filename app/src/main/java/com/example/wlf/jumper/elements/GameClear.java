package com.example.wlf.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.wlf.jumper.graphics.Cores;
import com.example.wlf.jumper.graphics.Tela;

public class GameClear {
    private final Tela tela;
    private static final Paint VERMELHO = Cores.pntgameclear();
    public GameClear( Tela tela )
    {
        this.tela = tela;
    }

    public void drawClear( Canvas canvas )
    {
        String gameClear = "Game Clear";
        int centHorizontal = centertxt( gameClear );
        canvas.drawText( gameClear, centHorizontal,tela.getAltura() / 2, VERMELHO );
    }

    private int centertxt( String texto )
    {
        Rect limiteDoTexto = new Rect();
        VERMELHO.getTextBounds(texto, 0, texto.length(), limiteDoTexto);
        int centerHorizontal = tela.getLargura()/2 - ( limiteDoTexto.right - limiteDoTexto.left ) /2;
        return centerHorizontal;
    }
}
