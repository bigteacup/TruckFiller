package com.example.truckfiller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutA;
    private TextInputEditText nombre80120;
    private TextInputEditText nombre100120;
    private TextInputEditText poidPalet;
    private TextInputEditText piecesParColis;
    private EditText resultat;
    //palettes hors rangs complet
    private ToggleButton switchForcerCasserUnRang80;
    private ToggleButton switchAutoriserRotation100Seulle;
    private boolean autorisationForcerCasserUnRang80;
    private boolean autorisationRotation100Seulle = false;

    //
    private ToggleButton switchAutorisationRangBatard80;
    private ToggleButton switchAutoriserRotation100SiPossibleDeFaireUnRang;
    private boolean autorisationRangBatard80;
    private boolean autorisationRotation100SiPossibleDeFaireUnRang = false;

    private View drawlocation;
    private LinearLayout drawPosRemorque;
    private ScrollView scrollViewRemorque;
    private LinearLayout linearLayoutContenu;
    private ScrollView scrollViewContenu;
    boolean gaucheOuBas = false;
    DrawView drawView;
    float agrandissement = 1.5f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        nombre80120 = findViewById(R.id.nombre80120);
        nombre80120.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                calculer();
            }
        });

        nombre100120 = findViewById(R.id.nombre100120);
        nombre100120.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculer();
            }
        });

        switchForcerCasserUnRang80 = findViewById(R.id.forcerCasserUnRang80);
        switchForcerCasserUnRang80.setChecked(false);
        switchForcerCasserUnRang80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   switcherEtat(switchAutoriserRotation80.isChecked());
                if (autorisationForcerCasserUnRang80 == true) {
                    autorisationForcerCasserUnRang80 = false;
                    switchForcerCasserUnRang80.setChecked(false);
                } else {
                    autorisationForcerCasserUnRang80 = true;
                    switchForcerCasserUnRang80.setChecked(true);
                }
                calculer();
            }
        });

        switchAutoriserRotation100Seulle = findViewById(R.id.autoriserRotation100Seulle);
        switchAutoriserRotation100Seulle.setChecked(true);
        switchAutoriserRotation100Seulle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rien
            }
        });


        //   SiPossibleDeFaireUnRangbatard
        switchAutorisationRangBatard80 = findViewById(R.id.autorisationRangBatard80);
        switchAutorisationRangBatard80.setChecked(false);
        switchAutorisationRangBatard80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   switcherEtat(switchAutoriserRotation80.isChecked());
                if (autorisationRangBatard80 == true) {
                    autorisationRangBatard80 = false;
                    switchAutorisationRangBatard80.setChecked(false);
                } else {
                    autorisationRangBatard80 = true;
                    switchAutorisationRangBatard80.setChecked(true);
                }
                calculer();
            }
        });

        switchAutoriserRotation100SiPossibleDeFaireUnRang = findViewById(R.id.autoriserRotation100SiPossibleDeFaireUnRang);
        switchAutoriserRotation100SiPossibleDeFaireUnRang.setChecked(true);
        switchAutoriserRotation100SiPossibleDeFaireUnRang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rien
            }
        });


    }

    private void switcherEtat(boolean etat) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int faireRang(ArrayList<Palette> listePalette, int nombreDeRangsPossible, int NombreDEmplacements,  ArrayList<Rang> listeDeRangComplet, int orientation) {
        int longeurDuGroupeDeRangs = 0;
        int iY = 0;
        int longueurLargeurSelonOrientation = 0;
        while( listeDeRangComplet.size() <nombreDeRangsPossible)  {
            Rang rang = new Rang();
            rang.idDuRang = rang.idDuRang + 1;
            rang.NombreDEmplacements = NombreDEmplacements;
            int iX =0;
            while (rang.listePaletteDuRang.size() < rang.NombreDEmplacements) {
                Palette pal = listePalette.get(0);
                pal.orientation = orientation;
                if(pal.orientation == 0) {
                    pal.positionX = iX * pal.largeur;
                    pal.positionY = iY * pal.longueur;
                    pal.positionXb = pal.positionX + pal.largeur;
                    pal.positionYb = pal.positionY + pal.longueur;
                    longueurLargeurSelonOrientation = pal.longueur;
                }else {
                    pal.positionX = iX * pal.longueur ;
                    pal.positionY = iY * pal.largeur;
                    pal.positionXb = pal.positionX + pal.longueur;
                    pal.positionYb = pal.positionY + pal.largeur;
                    longueurLargeurSelonOrientation = pal.largeur;
                }
                rang.listePaletteDuRang.add(pal);
                listePalette.remove(pal);
                iX++;
            }
            listeDeRangComplet.add(rang);

            iY++;
        }

        longeurDuGroupeDeRangs = listeDeRangComplet.size() * longueurLargeurSelonOrientation;
        return longeurDuGroupeDeRangs;
    }





    public ArrayList<Integer> placerLesPalettesRestantes(ArrayList<Palette> listeP80, ArrayList<Palette> listeP100, int longueurOccupée, int longueurRemorque){
    ArrayList<Integer> resultat = new ArrayList<Integer>();
    int a = 0;
    int b = 0;
    if(listeP80.size() > 0){

            listeP80.get(0).positionX = 0 ;
            listeP80.get(0).positionY = 0 ; //longueurOccupée
            listeP80.get(0).positionXb = listeP80.get(0).longueur;
            listeP80.get(0).positionYb = listeP80.get(0).largeur;
            a = a + listeP80.get(0).positionYb;

            if(listeP80.size() > 1) {
                listeP80.get(1).positionX = 0;
                listeP80.get(1).positionY = listeP80.get(1).largeur; //longueurOccupée
                listeP80.get(1).positionXb = listeP80.get(1).longueur;
                listeP80.get(1).positionYb = listeP80.get(1).largeur + listeP80.get(0).largeur;
                a = a + listeP80.get(1).positionYb - listeP80.get(0).largeur;
            }
        }
        if( listeP100.size() > 0 && listeP80.size() <= 2){
            listeP100.get(0).positionX = 120 ;
            listeP100.get(0).positionY = 0 ; //longueurOccupée
            listeP100.get(0).positionXb = listeP100.get(0).longueur + listeP100.get(0).positionX  ;
            listeP100.get(0).positionYb = listeP100.get(0).largeur;
            b = b + listeP100.get(0).positionYb;
        }else if( listeP100.size() > 0) {
            listeP100.get(0).positionX = 120 ;
            listeP100.get(0).positionY = 0 ; //longueurOccupée
            listeP100.get(0).positionXb = listeP100.get(0).longueur + listeP100.get(0).positionX  ;
            listeP100.get(0).positionYb = listeP100.get(0).largeur ;
            b = b + listeP100.get(0).positionYb;
        }
        if(listeP80.size() > 2) {
            listeP80.get(2).positionX = 120;
            listeP80.get(2).positionY = b ; //longueurOccupée
            listeP80.get(2).positionXb = listeP80.get(2).longueur *2;
            listeP80.get(2).positionYb = listeP80.get(2).largeur + b ;
            b = b + listeP80.get(2).largeur;
        }
        resultat.add(0,a);
        resultat.add(1,b);
        return resultat;

    }




    private void calculer(){    ///  à factoriser etc
        drawPosRemorque = findViewById(R.id. drawPosRemorque);



        Remorque remorque = new Remorque();
        remorque.hauteur = 300;
        remorque.largeur = 400;
        remorque.longueur = 1320;
/*
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        */
        if (drawView == null ) {
            drawView = new DrawView(this);
            int sizeX =  (int) (remorque.largeur / agrandissement) +25;
            int sizeY =  (int) (remorque.longueur / agrandissement) +25;
            drawView.setLayoutParams(new LinearLayout.LayoutParams( sizeX, sizeY)); //     ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
            drawView.setBackgroundColor(Color.WHITE);
        }


        ArrayList<Palette> listeP80 = new ArrayList<>();
        ArrayList<Palette> listeP100 = new ArrayList<>();
        listeP80.clear();
        listeP100.clear();



        int p80 = 0;
        /*
        int p80Rang3 = 3;
        int p80Rang2 = 2;
*/
        int p100 = 0;
        /*
        int p100Rang2 = 2;
*/



        try {
            p80 = Integer.parseInt(nombre80120.getText().toString());
        }catch(Exception e){p80=0;}
        try {
            p100 = Integer.parseInt(nombre100120.getText().toString());
        }catch(Exception e){p100=0;}

        for(int i = 0; i < p80; i++){
            Palette p = new Palette();
            p.id = i+1;
            p.orientation = 0;
            p.longueur = 120;
            p.largeur = 80;
            listeP80.add(p);
        }
        for(int i = 0; i < p100; i++){
            Palette p = new Palette();
            p.id = i+1;
            p.orientation = 1;
            p.longueur = 120;
            p.largeur = 100;
            listeP100.add(p);
        }


        System.out.println("P80 : " + listeP80.size());
        System.out.println("P100 : " + listeP100.size());

        //trouver des rangs droits possibles
        int EnLongueur80 = 0;
        int EnLargeur80 = 0;
        int EnLargeur100 = 0;


        try {
            EnLongueur80 = remorque.largeur / listeP80.get(0).largeur;
            if ((EnLongueur80 * listeP80.get(0).largeur) % EnLongueur80 == 0) {
                System.out.println("P80 : la largeur autorise des rangs complets de : " + EnLongueur80);

            }
        }catch(Exception e){EnLongueur80 = 1;}
        try {
            EnLargeur80 = remorque.largeur / listeP80.get(0).longueur;
            if ((EnLargeur80 * listeP80.get(0).longueur) % EnLargeur80 == 0) {
                System.out.println("P80 : la longueur autorise des rangs complets de : " + EnLargeur80);

            }
        }catch(Exception e){EnLargeur80 = 1;}
            try {
            EnLargeur100 = remorque.largeur / listeP100.get(0).longueur;
            if ((EnLargeur100 * listeP100.get(0).longueur) % EnLargeur100 == 0) {
                System.out.println("P100 : la longueur autorise des rangs complets de : " + EnLargeur100);

            }
        }catch(Exception e){EnLargeur100 = 1;}





        //peupler les rangs
        ArrayList<Rang> listeP80RangComplet3 = new ArrayList<Rang>();
        ArrayList<Rang> listeP80RangComplet2 = new ArrayList<Rang>();
        ArrayList<Rang> listeP100RangComplet2 = new ArrayList<Rang>();
        //ArrayList<Rang> listeRangMixte = new ArrayList<Rang>();

        int orientationRang80Par3 = 0;
        int p80RangComplet3 = (listeP80.size()-(listeP80.size() % EnLongueur80))/EnLongueur80;
        if(autorisationForcerCasserUnRang80 == true){p80RangComplet3 = p80RangComplet3 - 1;}
        int longueurOccupéeParLesRangsComplets80Par3 =  faireRang(listeP80, p80RangComplet3, EnLongueur80,listeP80RangComplet3, orientationRang80Par3);

        int orientationRang80Par2 = 1;
        int p80RangComplet2 = (listeP80.size()-(listeP80.size() % EnLargeur80))/EnLargeur80;
        if(autorisationRangBatard80 == true){p80RangComplet2 = p80RangComplet2 -1;}
        int longueurOccupéeParLesRangsComplets80Par2 = faireRang(listeP80, p80RangComplet2, EnLargeur80,listeP80RangComplet2, orientationRang80Par2);

        int orientationRang100Par2 = 1;
        int p100RangComplet2 = (listeP100.size()-(listeP100.size() % EnLargeur100))/EnLargeur100;
        int longueurOccupéeParLesRangsComplets100Par2 = faireRang(listeP100, p100RangComplet2, EnLargeur100,listeP100RangComplet2, orientationRang100Par2);

        // faire rang batard avec les reste de 80 et de 100
        // faireRang(listeP100, p100RangComplet2, p100Rang2,listeP100RangComplet2);
        ArrayList<Integer> tableauResultat = new ArrayList<Integer>();
        int longueurTotaleOccupée = longueurOccupéeParLesRangsComplets80Par3 + longueurOccupéeParLesRangsComplets80Par2 + longueurOccupéeParLesRangsComplets100Par2;
        tableauResultat = placerLesPalettesRestantes(listeP80, listeP100, longueurTotaleOccupée, remorque.longueur );

/*

int surfaceDeRangComplets = (longueurOccupéeParLesRangsComplets80Par3 + longueurOccupéeParLesRangsComplets80Par2 + longueurOccupéeParLesRangsComplets100Par2)* remorque.largeur;

surfaceRestante = surfaceRestante + (tableauResultat.get(0) * 1.2) +tableauResultat.get(1) *1.2);

*/



        double resteA = 0;
        double resteB = 0;

        double a = longueurTotaleOccupée + tableauResultat.get(0);
        double b = longueurTotaleOccupée + tableauResultat.get(1);
        double surfacestr = (p80*(120*80))+(p100*(120*100));
      //  double surfaceRestantestr = 3168 - surfacestr;
        /*
        BigDecimal bd = new BigDecimal(surfaceRestantestr);
        bd = bd.setScale(3,BigDecimal.ROUND_CEILING);
        surfaceRestantestr = bd.doubleValue();
*/
        resteA = (remorque.longueur - a);
        resteB = (remorque.longueur - b);

         double surfaceInutilisée = 0;
        double rectangleA = resteA * remorque.largeur/2;//on divise direct 120 par 100 par soucis de simplicité
        double rectangleB = resteB * remorque.largeur/2;
        if(rectangleA >= 0){
            surfaceInutilisée = rectangleA;
        }

        if(rectangleB >= 0){
            surfaceInutilisée = (surfaceInutilisée + rectangleB)/100;
        }
        double surfaceRemorque = (remorque.longueur*remorque.largeur)/100;
        System.out.println("                                                        ");
        System.out.println("Rang A : " + a /100 +"    Reste : " +resteA/100);
        System.out.println("Rang B : " + b/100 +"    Reste : "+resteB/100);
        resultat = findViewById(R.id.resultat);
        resultat.setText("Rang A : " + a /100 +"    Reste : " +resteA/100  +"\n" +
                "Rang B : " + b /100 +"    Reste : "+resteB/100  +"\n" +
                        "Surface inutilisée : " +  surfaceInutilisée/100 +"/"+ surfaceRemorque/100+""
                );

            //refacto :
        drawView.listeP80RangComplet3 = listeP80RangComplet3;
        drawView.listeP80RangComplet2 = listeP80RangComplet2;
        drawView.listeP100RangComplet2 = listeP100RangComplet2;

        drawView.longueurOccupéeParLesRangsComplets80Par3 = longueurOccupéeParLesRangsComplets80Par3;
        drawView.longueurOccupéeParLesRangsComplets80Par2 = longueurOccupéeParLesRangsComplets80Par2;
        drawView.longueurOccupéeParLesRangsComplets100Par2 = longueurOccupéeParLesRangsComplets100Par2;

        ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets = new ArrayList<ArrayList<Rang>>();
        listeDesListesDeRangComplets.add(listeP100RangComplet2);
        listeDesListesDeRangComplets.add(listeP80RangComplet3);
        listeDesListesDeRangComplets.add(listeP80RangComplet2);
         drawView.listeDesListesDeRangComplets = listeDesListesDeRangComplets;

        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;
        drawView.remorque = remorque;
        drawView.agrandissement = agrandissement;

        linearLayoutContenu = findViewById(R.id.linearLayoutContenu);
        scrollViewRemorque = findViewById(R.id.scrollViewRemorque);
        scrollViewContenu = findViewById(R.id.scrollViewContenu);
        linearLayoutA =  findViewById(R.id.linearLayoutA);

        if (remorque.largeur > 360){
            gaucheOuBas = true ;   // false = gauche true = bas
        }else{
            gaucheOuBas = false; // false = gauche true = bas
        }

        if (gaucheOuBas == true){
                       linearLayoutContenu.removeView(scrollViewRemorque);
            if(scrollViewRemorque.getParent() != null) {
                ((ViewGroup)scrollViewRemorque.getParent()).removeView(scrollViewRemorque); // <- fix
            }
            linearLayoutA.addView(scrollViewRemorque, linearLayoutA.getChildCount());
        }else{
            linearLayoutA.removeView(scrollViewRemorque);
            if(scrollViewRemorque.getParent() != null) {
                ((ViewGroup)scrollViewRemorque.getParent()).removeView(scrollViewRemorque); // <- fix
            }
            linearLayoutContenu.addView(scrollViewRemorque, 0);
        }

        drawPosRemorque.removeView(drawView);
        drawPosRemorque.addView(drawView);
    }
}
