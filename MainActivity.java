package jogo.velha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;

public class MainActivity extends Activity
{
    Tela t;
    int[][] tabuleiro={{0,0,0},{0,0,0},{0,0,0}};
    boolean vezdox=true;
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        t=new Tela(this);
        setContentView(t);
    }
    class Tela extends View
    {
        Tela(Context c){super(c);}
        @Override public boolean onTouchEvent(MotionEvent me)
        {
            if(me.getAction()==MotionEvent.ACTION_UP)
            {    float x=me.getX();
                float y=me.getY();
                for(int n=0;n<3;n++)for(int m=0;m<3;m++)if(x>m*100&&x<(m+1)*100&&y>n*100&&y<(n+1)*100)if(tabuleiro[m][n]==0)
                {    tabuleiro[m][n]=(vezdox?1:2);
                    vezdox=!vezdox;
                }
                t.invalidate();
            }
            return true;
        }
        @Override protected void onDraw(Canvas c)
        {    super.onDraw(c);
            Paint p=new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setColor(new Color().rgb(30,30,30));
            c.drawLine(100,0,100,300,p);
            c.drawLine(200,0,200,300,p);
            c.drawLine(0,100,300,100,p);
            c.drawLine(0,200,300,200,p);

            p.setColor(new Color().rgb(30,230,30));
            for(int n=0;n<3;n++)for(int m=0;m<3;m++)if(tabuleiro[m][n]==2)c.drawCircle(50+(m*100),50+(n*100),50,p);

            p.setColor(new Color().rgb(30,30,230));
            for(int n=0;n<3;n++)for(int m=0;m<3;m++)if(tabuleiro[m][n]==1)
            {
                c.drawLine(m*100,n*100,(m+1)*100,(n+1)*100,p);
                c.drawLine(m*100,(n+1)*100,(m+1)*100,n*100,p);
            }
        }
    }
}
