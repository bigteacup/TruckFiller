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
    public int nombre80 = 0;
    public int p80RangComplet3;
    public int p100RangComplet3;
    public int p100RangComplet2;
    public int p80RangComplet2;
    public int p80Reste;
    public int p100Reste;
    public int rangBatard = 0;
    double resteA;
    double resteB;
    boolean decalerUne80SurRangB = false;

    boolean oldMode = true;
    float agrandissement = 0;
    Remorque remorque;

    ArrayList<Rang> listeP80RangComplet3;
    ArrayList<Rang> listeP80RangComplet2;
    ArrayList<Rang> listeP100RangComplet2;
    ArrayList<Rang> listeP100RangComplet3;
    ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets;
    ArrayList<Palette> listeP80;
    ArrayList<Palette> listeP100;
    ArrayList<Palette> listeDePalettesPosees;


    int longueurOccupéeParLesRangsComplets80Par3;
    int longueurOccupéeParLesRangsComplets80Par2;
    int longueurOccupéeParLesRangsComplets100Par3;
    int longueurOccupéeParLesRangsComplets100Par2;

    float finRang80 = 0;
    float finRang100 = 0;
    int longueurOccupée = 0;
    int positionDeFin = 0;
    float coef = agrandissement;
    int whiteBarsShift = 3;
    int marge10 = 10;

    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
    if(oldMode == false){

         finRang80 = 0;
         finRang100 = 0;
         longueurOccupée = 0;
        positionDeFin = 0;
         coef = agrandissement;
         whiteBarsShift = 3;
        marge10 = 10;
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, remorque.largeur / coef + 20, remorque.longueur / coef + marge10, paint);
        paint.setColor(Color.GRAY); //paint.setColor(Color.RED);
        canvas.drawRect(marge10, marge10, marge10 + (remorque.largeur / coef), (remorque.longueur / coef) + marge10, paint);


        longueurOccupée = positionDeFin;

        if (listeDePalettesPosees != null) {
            for (Palette pal : listeDePalettesPosees) {
                if (pal.largeur == 80) {
                    paint.setColor(getResources().getColor(R.color.cbrownp));
                } else {
                    paint.setColor(getResources().getColor(R.color.cgreen));
                }

                paint2.setColor(Color.WHITE);
                paint.setStrokeWidth(0);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb / coef), marge10 + (pal.positionYb) / coef, paint);
                paint.setColor(Color.WHITE);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionY + whiteBarsShift) / coef, paint); // horizontale haute
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionYb) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionYb - whiteBarsShift) / coef, paint);  // horizontale basse
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionX + whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint);   //verticale gauche
                canvas.drawRect(marge10 + (pal.positionXb / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb - whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint); //verticale droite
                //Numero de palette
                paint.setColor(Color.WHITE);
                paint.setTextSize(30);
                canvas.drawText(String.valueOf(pal.id), marge10 + 10 + (pal.positionX) / coef, marge10 + 35 + (pal.positionY) / coef, paint);

                positionDeFin = pal.positionYb;
            }
        }

        longueurOccupée = positionDeFin;

        paintDebordement.setColor(Color.RED);
        paintDebordement.setAlpha(100);
        paintDebordement.setStyle(Paint.Style.FILL_AND_STROKE);
        //paintDebordement.setStrokeJoin(Paint.Join.ROUND);
        //paintDebordement.setStrokeMiter(10);
        // paintDebordement.setStrokeWidth(12);
        canvas.drawRect(0, remorque.longueur / coef + marge10, 20 + whiteBarsShift + remorque.largeur / coef, remorque.longueur / coef + 330, paintDebordement);
    }












        /////////////////////////////ancienne version
        else  {

            finRang80 = 0;
            finRang100 = 0;
            longueurOccupée = 0;
            positionDeFin = 0;
            coef = agrandissement;
            whiteBarsShift = 3;
            marge10 = 10;
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, remorque.largeur / coef + 20, remorque.longueur / coef + marge10, paint);
            paint.setColor(Color.GRAY); //paint.setColor(Color.RED);
            canvas.drawRect(marge10, marge10, marge10 + (remorque.largeur / coef), (remorque.longueur / coef) + marge10, paint);

            for (int i = 0; i < listeDesListesDeRangComplets.size(); i++) {
                longueurOccupée = positionDeFin;
                for (int ia = 0; ia < listeDesListesDeRangComplets.get(i).size(); ia++) {

                    for (Palette pal : listeDesListesDeRangComplets.get(i).get(ia).listePaletteDuRang) {
                        if (pal.largeur == 80) {
                            paint.setColor(getResources().getColor(R.color.cbrownp));
                        } else {
                            paint.setColor(getResources().getColor(R.color.cgreen));
                        }

                        paint2.setColor(Color.WHITE);
                        paint.setStrokeWidth(0);
                        canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb / coef), marge10 + (pal.positionYb) / coef, paint);
                        paint.setColor(Color.WHITE);
                        canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionY + whiteBarsShift) / coef, paint); // horizontale haute
                        canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionYb) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionYb - whiteBarsShift) / coef, paint);  // horizontale basse
                        canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionX + whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint);   //verticale gauche
                        canvas.drawRect(marge10 + (pal.positionXb / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb - whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint); //verticale droite
                        //Numero de palette
                        paint.setColor(Color.WHITE);
                        paint.setTextSize(30);
                        canvas.drawText(String.valueOf(pal.id), marge10 + 10 + (pal.positionX) / coef, marge10 + 35 + (pal.positionY) / coef, paint);

                        positionDeFin = pal.positionYb;
                    }
                }
            }
            longueurOccupée = positionDeFin;
///en cours
            for (Palette pal : listeP80) {
                if (pal.largeur == 80) {
                    paint.setColor(getResources().getColor(R.color.cbrownp));
                } else {
                    paint.setColor(getResources().getColor(R.color.cgreen));
                }

                paint2.setColor(Color.WHITE);
                paint.setStrokeWidth(0);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb / coef), marge10 + (pal.positionYb) / coef, paint);
                paint.setColor(Color.WHITE);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionY + whiteBarsShift) / coef, paint); // horizontale haute
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionYb) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionYb - whiteBarsShift) / coef, paint);  // horizontale basse
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionX + whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint);   //verticale gauche
                canvas.drawRect(marge10 + (pal.positionXb / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb - whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint); //verticale droite
                //  positionDeFin =  pal.positionYb;
                //Numero de palette
                paint.setColor(Color.WHITE);
                paint.setTextSize(30);
                canvas.drawText(String.valueOf(pal.id), marge10 + 10 + (pal.positionX) / coef, marge10 + 35 + (pal.positionY) / coef, paint);
            }
            for (Palette pal : listeP100) {
                if (pal.largeur == 80) {
                    paint.setColor(getResources().getColor(R.color.cbrownp));
                } else {
                    paint.setColor(getResources().getColor(R.color.cgreen));
                }

                paint2.setColor(Color.WHITE);
                paint.setStrokeWidth(0);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb / coef), marge10 + (pal.positionYb) / coef, paint);
                paint.setColor(Color.WHITE);
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionY + whiteBarsShift) / coef, paint); // horizontale haute
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionYb) / coef, marge10 + (pal.positionXb) / coef, marge10 + (pal.positionYb - whiteBarsShift) / coef, paint);  // horizontale basse
                canvas.drawRect(marge10 + (pal.positionX / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionX + whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint);   //verticale gauche
                canvas.drawRect(marge10 + (pal.positionXb / coef), marge10 + (pal.positionY) / coef, marge10 + (pal.positionXb - whiteBarsShift) / coef, marge10 + (pal.positionYb) / coef, paint); //verticale droite
                //Numero de palette
                paint.setColor(Color.WHITE);
                paint.setTextSize(30);
                canvas.drawText(String.valueOf(pal.id), marge10 + 10 + (pal.positionX) / coef, marge10 + 35 + (pal.positionY) / coef, paint);


                //  positionDeFin =  pal.positionYb;
            }
            paintDebordement.setColor(Color.RED);
            paintDebordement.setAlpha(100);
            paintDebordement.setStyle(Paint.Style.FILL_AND_STROKE);
            //paintDebordement.setStrokeJoin(Paint.Join.ROUND);
            //paintDebordement.setStrokeMiter(10);
            // paintDebordement.setStrokeWidth(12);
            canvas.drawRect(0, remorque.longueur / coef + marge10, 20 + whiteBarsShift + remorque.largeur / coef, remorque.longueur / coef + 330, paintDebordement);
        }

    }




    public void testim() {


    }


}






