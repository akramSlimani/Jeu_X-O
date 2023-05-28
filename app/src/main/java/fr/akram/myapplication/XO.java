package fr.akram.myapplication;

import android.annotation.SuppressLint;
import android.app.GameManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class XO extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int ligneGagnatColor;

    private boolean ligneGagnate =false;

    private LogiqueDuJeu jeu;

    private int cellSize = getWidth()/3;

    private Paint paint = new Paint();



    public XO(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        jeu = new LogiqueDuJeu();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.XO,0,0);

        try {
            boardColor=a.getInteger(R.styleable.XO_boardColor,0);
            XColor=a.getInteger(R.styleable.XO_XColor,0);
            OColor=a.getInteger(R.styleable.XO_OColor,0);
            ligneGagnatColor=a.getInteger(R.styleable.XO_ligneGagnatColor,0);


        }finally {
            a.recycle();
        }
    }

    //définir la taille de la vue en fonction des dimensions minimales.
    @Override
    protected void onMeasure(int width,int height){
        super.onMeasure(width,height);

        int dimension = Math.min(getMeasuredWidth(),getMeasuredHeight());

        cellSize = dimension/3;

        setMeasuredDimension(dimension,dimension);
    }

    //dessiner la grille de jeu et les marqueurs.
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);


        drawTablejeu(canvas);
        drawMarkers(canvas);

    }

    //gérer les événements quand le joueur touche la grille de jeu.
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action== MotionEvent.ACTION_DOWN){
            int row = (int) (Math.ceil(y/cellSize));
            int col = (int) (Math.ceil(x/cellSize));


            if(!ligneGagnate){
                if(jeu.miseAJourTab(row,col)){
                    invalidate();

                    if(jeu.gagnant()){
                        ligneGagnate=true;
                        invalidate();
                    }


                    //Miseajour du tour des joueurs
                    if(jeu.getPlayer() % 2 ==0){
                        jeu.setPlayer(jeu.getPlayer()-1);
                    }
                    else{
                        jeu.setPlayer(jeu.getPlayer()+1);
                    }
                }
            }




            invalidate();
            return true;
        }
        return false;
    }


    //dessiner la grille de jeu.
    private void drawTablejeu(Canvas canvas){

        paint.setColor(boardColor);
        paint.setStrokeWidth(16);


        for (int c=1;c<3;c++){
            canvas.drawLine(cellSize*c,0,cellSize*c,canvas.getWidth(),paint);

        }

        for (int r=1;r<3;r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r,paint);

        }

    }
    // Méthode pour dessiner les marqueurs X et O sur la grille
    private void drawMarkers(Canvas canvas){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(jeu.getTabJeu()[r][c]!= 0){
                    if(jeu.getTabJeu()[r][c]==1){
                        drawX(canvas,r,c);
                    }else{
                        drawO(canvas,r,c);
                    }


                }
            }
        }
    }
    //dessiner un marqueur X.
    private void drawX(Canvas canvas,int row,int col){
        paint.setColor(XColor);

        canvas.drawLine((float) ((col+1)*cellSize-cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float) (col*cellSize+cellSize*0.2),
                (float) ((row+1)*cellSize-cellSize*0.2),
                paint);

        canvas.drawLine((float) (col*cellSize+cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float) ((col+1)*cellSize-cellSize*0.2),
                (float) ((row+1)*cellSize-cellSize*0.2),
                paint);
    }

    //dessiner un marqueur O.
    private void drawO(Canvas canvas,int row,int col){
        paint.setColor(OColor);

        canvas.drawOval( (float)(col*cellSize + cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float)(((col*cellSize)+cellSize)-cellSize*0.2),
                (float)(((row*cellSize)+cellSize)-cellSize*0.2),
                paint);

    }
    // Méthode pour configurer le jeu avec les boutons de contrôle et les noms des joueurs.
    public void setUpJeu(Button rejouer, Button accueil, TextView PlayerDisplay,String[] noms){
        jeu.setRejouerBTN(rejouer);
        jeu.setAccueilBTN(accueil);
        jeu.setTourJoueur(PlayerDisplay);
        jeu.setNoms(noms);

    }
    //réinitialiser le jeu.
    public void resetJeu(){
        jeu.resetJeu();
        ligneGagnate=false;
    }
}
