package com.example.truckfiller;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint paintDebordement = new Paint();
    public int nombre80 = 0 ;
    public int p80RangComplet3;
    public int p100RangComplet2;
    public int p80RangComplet2;
    public int p80Reste;
    public int p100Reste;
    public int rangBatard = 0;
    double resteA;
    double resteB;
    boolean decalerUne80SurRangB = false;

    float agrandissement = 0;
    Remorque remorque;

    ArrayList<Rang> listeP80RangComplet3;
    ArrayList<Rang> listeP80RangComplet2;
    ArrayList<Rang> listeP100RangComplet2;
    ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets;
    ArrayList<Palette> listeP80;
    ArrayList<Palette> listeP100;


    int longueurOccupéeParLesRangsComplets80Par3;
    int longueurOccupéeParLesRangsComplets80Par2;
    int longueurOccupéeParLesRangsComplets100Par2;



    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {

        float finRang80 = 0;
        float finRang100 = 0;
        int longueurOccupée = 0;
        int positionDeFin = 0;
        float coef = agrandissement;
        int margeGauche = 10;
        paint.setColor(Color.GRAY); //paint.setColor(Color.RED);
        canvas.drawRect(margeGauche, 0, margeGauche + remorque.largeur / coef, remorque.longueur / coef, paint);

        for(int i =0 ; i < listeDesListesDeRangComplets.size(); i++) {
            longueurOccupée = positionDeFin;
            for (int ia = 0; ia < listeDesListesDeRangComplets.get(i).size(); ia++) {

                for (Palette pal : listeDesListesDeRangComplets.get(i).get(ia).listePaletteDuRang) {
                    if(pal.largeur == 80){
                    paint.setColor(getResources().getColor(R.color.cbrown));
                    }else{
                        paint.setColor(Color.BLUE);
                    }

                    paint2.setColor(Color.WHITE);
                    paint.setStrokeWidth(0);
                    canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb / coef), (longueurOccupée + pal.positionYb) / coef, paint);
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb) / coef, (3 + longueurOccupée + pal.positionY) / coef, paint); // horizontale haute
                    canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (3 + pal.positionX) / coef, (longueurOccupée + pal.positionYb) / coef, paint);   //verticale gauche
                    //Numero de palette
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(30);
                    canvas.drawText(String.valueOf(pal.id), margeGauche +10 + (pal.positionX ) / coef   , 35 + (longueurOccupée + pal.positionY) / coef , paint);

                    positionDeFin = longueurOccupée + pal.positionYb;
                }
            }
        }
        longueurOccupée = positionDeFin;
///en cours
        for (Palette pal : listeP80) {
            if(pal.largeur == 80){
                paint.setColor(getResources().getColor(R.color.cbrown));
            }else{
                paint.setColor(Color.BLUE);
            }

            paint2.setColor(Color.WHITE);
            paint.setStrokeWidth(0);
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb / coef), (longueurOccupée + pal.positionYb) / coef, paint);
            paint.setColor(Color.WHITE);
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb) / coef, (3 + longueurOccupée + pal.positionY) / coef, paint); // horizontale haute
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (3 + pal.positionX) / coef, (longueurOccupée + pal.positionYb) / coef, paint);   //verticale gauche
          //  positionDeFin = longueurOccupée + pal.positionYb;
            //Numero de palette
            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(String.valueOf(pal.id), margeGauche +10+ (pal.positionX ) / coef   ,  35+ (longueurOccupée + pal.positionY) / coef , paint);
        }
        for (Palette pal : listeP100) {
            if(pal.largeur == 80){
                paint.setColor(getResources().getColor(R.color.cbrown));
            }else{
                paint.setColor(Color.BLUE);
            }

            paint2.setColor(Color.WHITE);
            paint.setStrokeWidth(0);
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb / coef), (longueurOccupée + pal.positionYb) / coef, paint);
            paint.setColor(Color.WHITE);
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (pal.positionXb) / coef, (3 + longueurOccupée + pal.positionY) / coef, paint); // horizontale haute
            canvas.drawRect(margeGauche + (pal.positionX / coef), (longueurOccupée + pal.positionY) / coef, margeGauche + (3 + pal.positionX) / coef, (longueurOccupée + pal.positionYb) / coef, paint);   //verticale gauche
            //Numero de palette
            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(String.valueOf(pal.id), margeGauche+10 + (pal.positionX ) / coef   , 35 + (longueurOccupée + pal.positionY) / coef , paint);


          //  positionDeFin = longueurOccupée + pal.positionYb;
        }
        paintDebordement.setColor(Color.RED);
        paintDebordement.setAlpha(100);
        paintDebordement.setStyle(Paint.Style.FILL_AND_STROKE);
        //paintDebordement.setStrokeJoin(Paint.Join.ROUND);
        //paintDebordement.setStrokeMiter(10);
       // paintDebordement.setStrokeWidth(12);
        canvas.drawRect(margeGauche -10 , remorque.longueur/ coef, margeGauche + 10 + remorque.largeur/ coef, remorque.longueur/ coef + 330, paintDebordement);
    }
public void testim(){


}
}