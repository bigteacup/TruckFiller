package com.example.truckfiller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Switch;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    boolean gaucheOuBas = false;
    DrawView drawView;
    float agrandissement = 1.5f;
    Remorque remorque;
    ArrayList<Palette> listeP80 = new ArrayList<>();
    ArrayList<Palette> listeP100 = new ArrayList<>();
    ArrayList<Rang> listeP80RangComplet3 = new ArrayList<Rang>();
    ArrayList<Rang> listeP80RangComplet2 = new ArrayList<Rang>();
    ArrayList<Rang> listeP100RangComplet2 = new ArrayList<Rang>();
    ArrayList<Rang> listeP100RangComplet3 = new ArrayList<Rang>();
    //ArrayList<Rang> listeRangMixte = new ArrayList<Rang>();
    ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets = new ArrayList<ArrayList<Rang>>();
    private LinearLayout linearLayoutA;
    private TextInputEditText longueurRemorqueId;
    private TextInputEditText largeurRemorqueId;
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
    private TextInputEditText casser80120NombreTextInput;
    private TextInputEditText casser100120NombreTextInput;
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
    boolean oldmode =true;
    private Switch switchDevMode ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   Toolbar toolbar = findViewById(R.id.toolbar);
        //       setSupportActionBar(toolbar);


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        longueurRemorqueId = findViewById(R.id.longueurremorqueid);
        longueurRemorqueId.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int longueur = 0;
                int largeur = 0;
                int hauteur = 0;

                try {
                    longueur = Integer.parseInt(longueurRemorqueId.getText().toString());
                } catch (Exception e) {
                }
                try {
                    largeur = Integer.parseInt(largeurRemorqueId.getText().toString());
                } catch (Exception e) {
                }

                creerRemorque(longueur, largeur, hauteur);
                if(oldmode){
                    calculer();
                }else{
                calculer2();}
            }
        });

        largeurRemorqueId = findViewById(R.id.largeurremorqueid);
        largeurRemorqueId.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int longueur = 0;
                int largeur = 0;
                int hauteur = 0;

                try {
                    longueur = Integer.parseInt(longueurRemorqueId.getText().toString());
                } catch (Exception e) {
                }
                try {
                    largeur = Integer.parseInt(largeurRemorqueId.getText().toString());
                } catch (Exception e) {
                }

                creerRemorque(longueur, largeur, hauteur);
                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
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


                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
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
                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
            }
        });


        casser80120NombreTextInput = findViewById(R.id.casser80120nombre);
        casser80120NombreTextInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
            }
        });

        casser100120NombreTextInput = findViewById(R.id.casser100120nombre);
        casser100120NombreTextInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
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
                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
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
                if(oldmode){
                    calculer();
                }else{
                    calculer2();}
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



        switchDevMode = findViewById(R.id.oldModetid);
        switchDevMode.setChecked(false);
        switchDevMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   switcherEtat(switchAutoriserRotation80.isChecked());
                if (oldmode == true) {
                    oldmode = false;

                    switchDevMode.setChecked(true);
                } else {
                    oldmode = true;
                    switchDevMode.setChecked(false);
                }
                if(oldmode){
                    creerRemorque(1320, 240, 300);
                    calculer();
                }else{
                    creerRemorque(1320, 240, 300);
                    calculer2();}
            }
        });
        //taille par defaut
        creerRemorque(1320, 240, 300);



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

    public int faireRang(ArrayList<Palette> listePalette, int nombreDeRangsPossible, int NombreDEmplacements, ArrayList<Rang> listeDeRangComplet, int orientation, int longueurOccupeeParLesRangsPrecededants) {
        listeDeRangComplet.clear();
        int longeurDuGroupeDeRangs = 0;
        int iY = 0;
        int longueurLargeurSelonOrientation = 0;
        while (listeDeRangComplet.size() < nombreDeRangsPossible) {
            Rang rang = new Rang();
            rang.idDuRang = rang.idDuRang + 1;
            rang.NombreDEmplacements = NombreDEmplacements;
            int iX = 0;
            while (rang.listePaletteDuRang.size() < rang.NombreDEmplacements) {
                Palette pal = listePalette.get(0);
                pal.orientation = orientation;
                if (pal.orientation == 0) {
                    pal.positionX = iX * pal.largeur;
                    pal.positionY = iY * pal.longueur + longueurOccupeeParLesRangsPrecededants;
                    pal.positionXb = pal.positionX + pal.largeur;
                    pal.positionYb = pal.positionY + pal.longueur;
                    longueurLargeurSelonOrientation = pal.longueur;
                } else {
                    pal.positionX = iX * pal.longueur;
                    pal.positionY = iY * pal.largeur + longueurOccupeeParLesRangsPrecededants;
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


    public ArrayList<Integer> placerLesPalettesRestantes(ArrayList<Palette> listeP80, ArrayList<Palette> listeP100, int longueurOccupeeParLesRangsPrecededants, int longueurRemorque) {
        ArrayList<Integer> resultat = new ArrayList<Integer>();
        int a = 0;
        int b = 0;
        if (listeP80.size() > 0) {

            listeP80.get(0).positionX = 0;
            listeP80.get(0).positionY = 0 + longueurOccupeeParLesRangsPrecededants; //longueurOccupée
            listeP80.get(0).positionXb = listeP80.get(0).longueur;
            listeP80.get(0).positionYb = listeP80.get(0).largeur + longueurOccupeeParLesRangsPrecededants;
            a = a + listeP80.get(0).positionYb - longueurOccupeeParLesRangsPrecededants;

            if (listeP80.size() > 1) {
                listeP80.get(1).positionX = 0;
                listeP80.get(1).positionY = listeP80.get(1).largeur + longueurOccupeeParLesRangsPrecededants; //longueurOccupée
                listeP80.get(1).positionXb = listeP80.get(1).longueur;
                listeP80.get(1).positionYb = listeP80.get(1).largeur + listeP80.get(0).largeur + longueurOccupeeParLesRangsPrecededants;
                a = a + listeP80.get(1).positionYb - listeP80.get(0).largeur - longueurOccupeeParLesRangsPrecededants;
            }
        }
        if (listeP100.size() > 0 && listeP80.size() <= 2) {
            listeP100.get(0).positionX = 120;
            listeP100.get(0).positionY = 0 + longueurOccupeeParLesRangsPrecededants; //longueurOccupée
            listeP100.get(0).positionXb = listeP100.get(0).longueur + listeP100.get(0).positionX;
            listeP100.get(0).positionYb = listeP100.get(0).largeur + longueurOccupeeParLesRangsPrecededants;
            b = b + listeP100.get(0).positionYb - longueurOccupeeParLesRangsPrecededants;
        } else if (listeP100.size() > 0) {
            listeP100.get(0).positionX = 120;
            listeP100.get(0).positionY = 0 + longueurOccupeeParLesRangsPrecededants; //longueurOccupée
            listeP100.get(0).positionXb = listeP100.get(0).longueur + listeP100.get(0).positionX;
            listeP100.get(0).positionYb = listeP100.get(0).largeur + longueurOccupeeParLesRangsPrecededants;
            b = b + listeP100.get(0).positionYb - longueurOccupeeParLesRangsPrecededants;
        }
        if (listeP80.size() > 2) {
            listeP80.get(2).positionX = 120;
            listeP80.get(2).positionY = b + longueurOccupeeParLesRangsPrecededants; //longueurOccupée
            listeP80.get(2).positionXb = listeP80.get(2).longueur * 2;
            listeP80.get(2).positionYb = listeP80.get(2).largeur + b + longueurOccupeeParLesRangsPrecededants;
            b = b + listeP80.get(2).largeur;
        }
        resultat.add(0, a);
        resultat.add(1, b);
        return resultat;

    }


    //on fait un affichage au demarrage et on prepare les parametres de la remorque.
    private void creerRemorque(int longueur, int largeur, int hauteur) {
        remorque = null;
        drawPosRemorque = findViewById(R.id.drawPosRemorque);
        remorque = new Remorque();
        remorque.hauteur = 300;
        remorque.largeur = 240;
        remorque.longueur = 1320;
        remorque.hauteur = hauteur;
        remorque.largeur = largeur;
        remorque.longueur = longueur;


        if (drawView == null) {
            drawView = new DrawView(this);
        }
        int sizeX = (int) (remorque.largeur / agrandissement) + 20; // +3
        int sizeY = (int) (remorque.longueur / agrandissement) + 145;
        drawView.setLayoutParams(new LinearLayout.LayoutParams(sizeX, sizeY)); //     ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        drawView.setBackgroundColor(Color.WHITE);
        //drawView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));


        linearLayoutContenu = findViewById(R.id.linearLayoutContenu);
        scrollViewRemorque = findViewById(R.id.scrollViewRemorque);
        scrollViewContenu = findViewById(R.id.scrollViewContenu);
        linearLayoutA = findViewById(R.id.linearLayoutA);

        if (remorque.largeur >= 360) {
            gaucheOuBas = true;   // false = gauche true = bas
        } else {
            gaucheOuBas = false; // false = gauche true = bas
        }

        if (gaucheOuBas == true) {
            linearLayoutContenu.removeView(scrollViewRemorque);
            if (scrollViewRemorque.getParent() != null) {
                ((ViewGroup) scrollViewRemorque.getParent()).removeView(scrollViewRemorque); // <- fix
            }
            linearLayoutA.addView(scrollViewRemorque, linearLayoutA.getChildCount());
        } else {
            linearLayoutA.removeView(scrollViewRemorque);
            if (scrollViewRemorque.getParent() != null) {
                ((ViewGroup) scrollViewRemorque.getParent()).removeView(scrollViewRemorque); // <- fix
            }
            linearLayoutContenu.addView(scrollViewRemorque, 0);
        }


        drawView.listeP80RangComplet3 = listeP80RangComplet3;
        drawView.listeP80RangComplet2 = listeP80RangComplet2;
        drawView.listeP100RangComplet2 = listeP100RangComplet2;
        drawView.listeP100RangComplet3 = listeP100RangComplet3;

        drawView.longueurOccupéeParLesRangsComplets80Par3 = 0;
        drawView.longueurOccupéeParLesRangsComplets80Par2 = 0;
        drawView.longueurOccupéeParLesRangsComplets100Par2 = 0;
        drawView.longueurOccupéeParLesRangsComplets100Par3 = 0;


        drawView.listeDesListesDeRangComplets = listeDesListesDeRangComplets;

        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;
        drawView.remorque = remorque;
        drawView.agrandissement = agrandissement;


        drawPosRemorque.removeView(drawView);
        drawPosRemorque.addView(drawView);


    }


    private void calculer2() {
        drawPosRemorque = findViewById(R.id.drawPosRemorque);
        //drawPosRemorque.removeView(drawView);
        listeP80.clear();
        listeP100.clear();

        new Thread(new Runnable() {
            public void run() {





       final  ArrayList<Palette> listeDePalettesPosees = new ArrayList<>();

        int p80 = 0;
        int p100 = 0;

        try {
            p80 = Integer.parseInt(nombre80120.getText().toString());
        } catch (Exception e) {
            p80 = 0;
        }
        try {
            p100 = Integer.parseInt(nombre100120.getText().toString());
        } catch (Exception e) {
            p100 = 0;
        }




///////////////////////Template
                Palette item1 = new Palette();
                item1.longueur = 120;
                item1.largeur = 100;

                Palette item2 = new Palette();
                item1.longueur = 120;
                item1.largeur = 80;
                //templates possibles rangs droits
                try {
                 item1 = listeP100.get(0);
                if (remorque.largeur % item1.getLargeur() == 0){
                    //rang parfait possible en longueur
                    item1.rangLongueur = true;
                }
                if(remorque.largeur % item1.getLongueur() == 0){
                    //rang parfait possible en largeur
                    item1.rangLargeur = true;
                }
                }catch(Exception e){}
                try {
                 item2 = listeP80.get(0);
                if (remorque.largeur % item2.getLargeur() == 0){
                    //rang parfait possible en longueur
                    item2.rangLongueur = true;
                }
                if(remorque.largeur % item2.getLongueur() == 0){
                    //rang parfait possible en largeur
                    item2.rangLargeur = true;
                }
                }catch(Exception e){}
                //batard////////////////////////////////////////// @ doubler ?
                try {
                int reste = remorque.largeur - item1.getLargeur();
                if (reste % item2.getLargeur() == 0){
                    //rang parfait batard possible en longueur
                    item2.rangBatardLongueurLongueur = true;
                    item1.rangBatardLongueurLongueur = true;
                }
                if(reste % item2.getLongueur() == 0){
                    //rang parfait batard possible en largeur
                    item2.rangBatardLongueurLargeur = true;
                    item1.rangBatardLongueurLargeur = true;
                }

               reste = remorque.largeur - item1.getLongueur();
                if (reste % item2.getLargeur() == 0){
                    //rang parfait batard possible en longueur
                    item2.rangBatardLargeurLongueur = true;
                    item1.rangBatardLargeurLongueur = true;
                }
                if(reste % item2.getLongueur() == 0){
                    //rang parfait batard possible en largeur
                    item2.rangBatardLargeurLargeur = true;
                    item1.rangBatardLargeurLargeur = true;
                }
            }catch(Exception e){}


// on applique ajout des palettes
                for (int i = 0; i < p80; i++) {
                    Palette p = new Palette();
                    p.id = i + 1;
                    p.orientation = 0;
                    p.longueur = 120;
                    p.largeur = 80;
                    p.setOrientationFavorite(0);
                    p.rangLargeur = item2.rangLargeur;
                    p.rangLongueur = item2.rangLongueur;
                    p.rangBatardLargeurLargeur = item2.rangBatardLargeurLargeur;
                    p.rangBatardLargeurLongueur = item2.rangBatardLargeurLongueur;
                    p.rangBatardLongueurLargeur = item2.rangBatardLongueurLargeur;
                    p.rangBatardLongueurLongueur = item2.rangBatardLongueurLongueur;



                    listeP80.add(p);

                }
                for (int i = 0; i < p100; i++) {
                    Palette p = new Palette();
                    p.id = i + 1;
                    p.orientation = 1;
                    p.longueur = 120;
                    p.largeur = 100;
                    p.rangLargeur = item1.rangLargeur;
                    p.rangLongueur = item1.rangLongueur;
                    p.rangBatardLargeurLargeur = item1.rangBatardLargeurLargeur;
                    p.rangBatardLargeurLongueur = item1.rangBatardLargeurLongueur;
                    p.rangBatardLongueurLargeur = item1.rangBatardLongueurLargeur;
                    p.rangBatardLongueurLongueur = item1.rangBatardLongueurLongueur;
                    listeP100.add(p);
                }



        //faire une liste avec les deux listes
        ArrayList<Palette> listeDePalettesAPoser = new ArrayList<>();
        ArrayList<ArrayList<Palette>> listeDesListesDePalettesAPoser = new ArrayList<>();

        listeDesListesDePalettesAPoser.add(listeP100);
        listeDesListesDePalettesAPoser.add(listeP80);


        for (Palette p : listeP80) {
            listeDePalettesAPoser.add(p);
        }

        for (Palette p : listeP100) {
            listeDePalettesAPoser.add(p);
        }

        //onteste
        double moduloAc =0;
        double moduloBc =0;

        boolean reverse= false;
        int compteX = 0;
        int compteY = 0;
        int f=0;
        int e=0;
        boolean continuer = true;
           //     while(continuer == true) {
/*              Palette p = new  Palette();

//on place
                while(continuer){
                    for(int i =0; i < listeDePalettesAPoser.size(); i++){
                        p = listeDePalettesAPoser.get(i);

                        p.setPositionX(compteX);
                        p.setPositionY(compteY);


                        listeDePalettesPosees.add(p);
                    }
                    continuer = false;
                }
*/

                //Pour chaque palette on tente un posistionnement
                // on fais une new Arraylist pour eviter les conccurent ecxeptions : devra etre remplacépar un controle au lancment du calcul
        for (ArrayList<Palette> listeEncours : new ArrayList<ArrayList<Palette>> (listeDesListesDePalettesAPoser)) {


            for (Palette p : new ArrayList<Palette>(listeEncours)) {
                boolean palettePosee = false;



                    for(int iY=0 + e; iY < remorque.longueur; iY = iY + 20 ) {
                        if(palettePosee == true){
                            break;
                        }
                        for (int iX = 0 + f; iX < remorque.largeur; iX = iX + 20) {
                            int   orientation = 0;
                            p.setPositionX(iX);
                            p.setPositionY(iY);



                            //[objectif RANG] orientation en fonction d'un multiple de la largeur de la remorque et d'une dimension de la palette
                         double   moduloA = (remorque.largeur -iX) % p.getLargeur();
                         double   moduloB = (remorque.largeur -iX) % p.getLongueur();
                            if( moduloA == 0 || p.getOrientationFavorite() == 0 ) {
                                //orientation //&& moduloA >= moduloB
                                orientation = 0;
                            }else if( moduloB == 0){
                                orientation= 1;
                            }







                            p.setOrientation(orientation);
                            // si la position selectionnée ne cause pas de superposition avec une autre palette et de depasse pas du container
                            if(estSuperposeAUnAutre(listeDePalettesPosees, p) == false && p.getPositionXb() <= remorque.largeur && p.getPositionYb() <= remorque.longueur ){ //&& p.getPositionYb() <= remorque.longueur

                                ////////////////////////////////////chercheur d'obstacle vers la droite
                                int incrementPointeur = 20;  //doit etre inferieur ou egal à lecart minimum possible du fait de l'agencement possible des palettes
                                boolean obstacleTrouve = false;
                                for(int xl = p.getPositionX(); xl < remorque.largeur; xl = xl + incrementPointeur  ){
                                    Palette pointeur = new Palette();
                                    pointeur.setLargeur(80);
                                    pointeur.setLongueur(120);
                                    pointeur.setPositionX(xl);
                                    pointeur.setPositionY(iY);
                                    pointeur.setOrientation(0);
                                    ////on determine la place sur la droite
                                 int   mesure = xl - incrementPointeur +pointeur.getLargeur();
                                    if(estSuperposeAUnAutre(listeDePalettesPosees, pointeur) == true || mesure >= remorque.largeur   ){

                                    double     moduloAb = (mesure - iX)  % p.getLargeur();
                                    double   moduloBb = (mesure - iX) % p.getLongueur();
                                        if( moduloAb == 0  ) {
                                            orientation = 0;
                                        }else if( moduloBb == 0){
                                            orientation= 1;
                                        }else if (moduloAb < moduloBb){
                                            orientation = 0;
                                        }else {
                                            orientation = 1;
                                        }
                                        obstacleTrouve = true;
                                        break;
                                    }


                                }

                                ////////////////////////////////////chercheur d'obstacle vers la gauche
                                //debranché noptimase pas la place ou tres peu
                                 incrementPointeur = 1;  //doit etre inferieur ou egal à lecart minimum possible du fait de l'agencement possible des palettes
                                    boolean videAAGauche = false;
                                for(int xl = p.getPositionX()-1; xl > 0; xl = xl - incrementPointeur  ){
                                    Palette pointeurGauche =new Palette();
                                    pointeurGauche.setLargeur(1);
                                    pointeurGauche.setLongueur(120);
                                    pointeurGauche.setPositionX(xl);
                                    pointeurGauche.setPositionY(p.getLargeur()+p.getPositionY() -1);
                                    pointeurGauche.setOrientation(0);
                                    ////on determine la place sur la gauche
                                  int  mesure = xl - incrementPointeur +pointeurGauche.getLargeur();
                                    if(estSuperposeAUnAutre(listeDePalettesPosees, pointeurGauche) == true || mesure <= 0   ){
                                        break;

                                    }else{
                                      //  videAAGauche = true;
                                        break; // a faire :prendre la mesure au lieu de break
                                    }


                                }




                                if (videAAGauche == false) {
                                    p.setOrientation(orientation);
                                    listeDePalettesPosees.add(p);
                                    //   p.setOrientation(orientation); //recalcule les positions de la palette
                                    palettePosee = true;
                                }
                                break;

                            }else {
                               // s'il y a superposition on tourne la palette et on retente
                                if(p.getOrientation() == 1){
                                    p.setOrientation(0);
                                }else {
                                    p.setOrientation(1);
                                }
                                if(estSuperposeAUnAutre(listeDePalettesPosees, p) == false && p.getPositionXb() <= remorque.largeur && p.getPositionYb() <= remorque.longueur ) { //&& p.getPositionYb() <= remorque.longueur

                                    //chercheur d'obstacle vers la droite
                                    int incrementPointeur = 20;  //doit etre inferieur ou egal à lecart minimum possible du fait de l'agencement possible des palettes
                                    boolean obstacleTrouve = false;
                                    for (int xl = p.getPositionX(); xl < remorque.largeur; xl = xl + incrementPointeur) {
                                        Palette pointeur = new Palette();
                                        pointeur.setLargeur(80);
                                        pointeur.setLongueur(120);
                                        pointeur.setPositionX(xl);
                                        pointeur.setPositionY(iY);
                                        pointeur.setOrientation(0);
                                        ////on determine la place sur la droite
                                        if (estSuperposeAUnAutre(listeDePalettesPosees, pointeur) == true) {
                                        int     mesure = xl - incrementPointeur + pointeur.getLargeur();
                                         double    moduloAb = (mesure) % p.getLargeur();
                                         double    moduloBb = (mesure) % p.getLongueur();
                                            if (moduloAb == 0) {
                                                orientation = 0;
                                            } else if (moduloBb == 0) {
                                                orientation = 1;
                                            }
                                            obstacleTrouve = true;
                                            break;
                                        }

                                    }








                                   //      p.setOrientation(orientation);
                                        listeDePalettesPosees.add(p);
                                        //   p.setOrientation(orientation); //recalcule les positions de la palette
                                        palettePosee = true;

                                        break;
                                    }

                                }


                        }
                    }




            }
            }//fin for///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //    } //fin while


                runOnUiThread(new Runnable() {
                    public void run() {



        double surfaceUtiliseeA = 0;
        for(Palette pp : listeDePalettesPosees){
            surfaceUtiliseeA =  surfaceUtiliseeA + pp.getSurface()/100;
        }
        final  double surfaceUtilisee = surfaceUtiliseeA;
      final  double surfaceRemorque = (remorque.longueur * remorque.largeur) / 100;
 //       System.out.println("                                                        ");
        //  System.out.println("Rang A : " + a / 100 + "    Reste : " + resteA / 100);
        //   System.out.println("Rang B : " + b / 100 + "    Reste : " + resteB / 100);

        resultat = findViewById(R.id.resultat);
        resultat.setText("Rang A : " + 0 / 100 + "    Reste : " + 0 / 100 + "\n" +
                "Rang B : " + 0 / 100 + "    Reste : " + 0 / 100 + "\n" +
                "Surface inutilisée : " + ((surfaceRemorque - surfaceUtilisee ) / 100) + "/" + surfaceRemorque / 100 + "\n" +
                0 + " P80 à quai" + "\n" +
                0 + " P100 à quai"
        );





        drawView.listeDePalettesPosees = listeDePalettesPosees;
        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;
        drawPosRemorque.removeView(drawView);
        drawPosRemorque.addView(drawView);

                    }
                });
         /////fin thread
            }
        }).start();
        //////////

    }













//////////////////////////// //detection de superposition      //if (RectA.X1 < RectB.X2 && RectA.X2 > RectB.X1 && RectA.Y1 < RectB.Y2 && RectA.Y2 > RectB.Y1)
   private boolean estSuperposeAUnAutre(ArrayList<Palette> listeDePalettesPosees, Palette p){
       boolean resultat = false;
        for (Palette p1 : listeDePalettesPosees) { //refacto :commencer par la fin de la liste
            if (p1.getPositionX() < p.getPositionXb() && p1.getPositionXb() > p.getPositionX() && p1.getPositionY() < p.getPositionYb() && p1.getPositionYb() > p.getPositionY()) {
        resultat =true;
               // System.out.println("P : " + p.getId() + " est superposé à " + p1.getId());
            }
        }
        return resultat;
    }














    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void calculer() {    ///  à factoriser etc
        drawPosRemorque = findViewById(R.id.drawPosRemorque);
        drawPosRemorque.removeView(drawView);

        listeDesListesDeRangComplets.clear();
        listeP100RangComplet2.clear();
        listeP100RangComplet3.clear();
        listeP80RangComplet2.clear();
        listeP80RangComplet3.clear();
/*
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        */


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
        } catch (Exception e) {
            p80 = 0;
        }
        try {
            p100 = Integer.parseInt(nombre100120.getText().toString());
        } catch (Exception e) {
            p100 = 0;
        }

        for (int i = 0; i < p80; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.orientation = 0;
            p.longueur = 120;
            p.largeur = 80;
            listeP80.add(p);
        }
        for (int i = 0; i < p100; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.orientation = 1;
            p.longueur = 120;
            p.largeur = 100;
            listeP100.add(p);
        }


  //      System.out.println("P80 : " + listeP80.size());
  //      System.out.println("P100 : " + listeP100.size());

        //trouver des rangs droits possibles
        int EnLongueur80 = 0;
        int EnLargeur80 = 0;
        int EnLargeur100 = 0;
        int EnLongueur100 = 0;


        try {
            EnLongueur80 = remorque.largeur / listeP80.get(0).largeur;
            if ((EnLongueur80 * listeP80.get(0).largeur) % EnLongueur80 == 0) {
       //         System.out.println("P80 : la largeur autorise des rangs complets de : " + EnLongueur80);

            }
        } catch (Exception e) {
            EnLongueur80 = 1;
        }
        try {
            EnLargeur80 = remorque.largeur / listeP80.get(0).longueur;
            if ((EnLargeur80 * listeP80.get(0).longueur) % EnLargeur80 == 0) {
       //         System.out.println("P80 : la longueur autorise des rangs complets de : " + EnLargeur80);

            }
        } catch (Exception e) {
            EnLargeur80 = 1;
        }
        try {
            EnLongueur100 = remorque.largeur / listeP100.get(0).largeur;
            if ((EnLongueur100 * listeP100.get(0).largeur) % EnLongueur100 == 0) {
         //       System.out.println("P100 : la largeur autorise des rangs complets de : " + EnLongueur100);

            }
        } catch (Exception e) {
            EnLongueur100 = 1;
        }
        try {
            EnLargeur100 = remorque.largeur / listeP100.get(0).longueur;
            if ((EnLargeur100 * listeP100.get(0).longueur) % EnLargeur100 == 0) {
         //       System.out.println("P100 : la longueur autorise des rangs complets de : " + EnLargeur100);

            }
        } catch (Exception e) {
            EnLargeur100 = 1;
        }


        //peupler les rangs
        double moduloEnLongueur100 = 0.0;
        double moduloEnLargeur100 = 0.0;
        int longueurOccupéeParLesRangsComplets100Par3 = 0;
        try {
            moduloEnLongueur100 = (double) remorque.largeur / (double) listeP100.get(0).largeur;

        } catch (Exception e) {
        }
        try {
            moduloEnLargeur100 = (double) remorque.largeur / (double) listeP100.get(0).longueur;
        } catch (Exception e) {
        }
        if (moduloEnLongueur100 - EnLongueur100 < moduloEnLargeur100 - EnLargeur100) {


            //100 120 par 3
            int orientationRang100Par3 = 0;
            int p100RangComplet3 = (listeP100.size() - (listeP100.size() % EnLongueur100)) / EnLongueur100;
            longueurOccupéeParLesRangsComplets100Par3 = faireRang(listeP100, p100RangComplet3, EnLongueur100, listeP100RangComplet3, orientationRang100Par3, 0);
        }
        //100 120 par 2
        int orientationRang100Par2 = 1;
        int p100RangComplet2 = (listeP100.size() - (listeP100.size() % EnLargeur100)) / EnLargeur100;
        int longueurOccupéeParLesRangsComplets100Par2 = faireRang(listeP100, p100RangComplet2, EnLargeur100, listeP100RangComplet2, orientationRang100Par2, longueurOccupéeParLesRangsComplets100Par3);


        //80 par 3
        int orientationRang80Par3 = 0;
        int p80RangComplet3 = (listeP80.size() - (listeP80.size() % EnLongueur80)) / EnLongueur80;
        int casser80120Nombre = 0;
        try {
            casser80120Nombre = Integer.parseInt(casser80120NombreTextInput.getText().toString());
        } catch (Exception e) {
        }
        if (casser80120Nombre > p80RangComplet3) {
            casser80120Nombre = p80RangComplet3;
        }
        if (autorisationForcerCasserUnRang80 == true) {
            p80RangComplet3 = p80RangComplet3 - casser80120Nombre;
        }
        int longueurOccupéeParLesRangsComplets80Par3 = faireRang(listeP80, p80RangComplet3, EnLongueur80, listeP80RangComplet3, orientationRang80Par3, longueurOccupéeParLesRangsComplets100Par3 + longueurOccupéeParLesRangsComplets100Par2);


        //80 par 2
        int orientationRang80Par2 = 1;
        int p80RangComplet2 = (listeP80.size() - (listeP80.size() % EnLargeur80)) / EnLargeur80;
        if (autorisationRangBatard80 == true) {
            p80RangComplet2 = p80RangComplet2 - 1;
        }
        int longueurOccupéeParLesRangsComplets80Par2 = faireRang(listeP80, p80RangComplet2, EnLargeur80, listeP80RangComplet2, orientationRang80Par2, longueurOccupéeParLesRangsComplets100Par3 + longueurOccupéeParLesRangsComplets100Par2 + longueurOccupéeParLesRangsComplets80Par3);


        // faire rang batard avec les reste de 80 et de 100
        // faireRang(listeP100, p100RangComplet2, p100Rang2,listeP100RangComplet2);
        ArrayList<Integer> tableauResultat = new ArrayList<Integer>();
        int longueurTotaleOccupée = longueurOccupéeParLesRangsComplets80Par3 + longueurOccupéeParLesRangsComplets80Par2 + longueurOccupéeParLesRangsComplets100Par2 + longueurOccupéeParLesRangsComplets100Par3;
        tableauResultat = placerLesPalettesRestantes(listeP80, listeP100, longueurTotaleOccupée, remorque.longueur);

        //faire un liste des listes pour simplifier les manupulations
        listeDesListesDeRangComplets.clear();
        listeDesListesDeRangComplets.add(listeP100RangComplet3);
        listeDesListesDeRangComplets.add(listeP100RangComplet2);
        listeDesListesDeRangComplets.add(listeP80RangComplet3);
        listeDesListesDeRangComplets.add(listeP80RangComplet2);
/*

int surfaceDeRangComplets = (longueurOccupéeParLesRangsComplets80Par3 + longueurOccupéeParLesRangsComplets80Par2 + longueurOccupéeParLesRangsComplets100Par2)* remorque.largeur;

surfaceRestante = surfaceRestante + (tableauResultat.get(0) * 1.2) +tableauResultat.get(1) *1.2);

*/
        //Partie texte/resultats
//on compte les palettes qui ne passent pas dans la remorque pour information
        int[] debordementPalette = compterLesPalettesQuiNePassentPas(listeDesListesDeRangComplets, listeP80, listeP100, remorque);

        double resteA = 0;
        double resteB = 0;

        double a = longueurTotaleOccupée + tableauResultat.get(0);
        double b = longueurTotaleOccupée + tableauResultat.get(1);
        double surfacestr = (p80 * (120 * 80)) + (p100 * (120 * 100));
        //  double surfaceRestantestr = 3168 - surfacestr;
        /*
        BigDecimal bd = new BigDecimal(surfaceRestantestr);
        bd = bd.setScale(3,BigDecimal.ROUND_CEILING);
        surfaceRestantestr = bd.doubleValue();
*/
        resteA = (remorque.longueur - a);
        resteB = (remorque.longueur - b);

        double surfaceInutilisée = 0;
        double rectangleA = resteA * remorque.largeur / 2;//on divise direct 120 par 100 par soucis de simplicité
        double rectangleB = resteB * remorque.largeur / 2;
        if (rectangleA >= 0) {
            surfaceInutilisée = rectangleA;
        }

        if (rectangleB >= 0) {
            surfaceInutilisée = (surfaceInutilisée + rectangleB);
        }
        surfaceInutilisée = surfaceInutilisée / 100;
        double surfaceRemorque = (remorque.longueur * remorque.largeur) / 100;
  //      System.out.println("                                                        ");
   //     System.out.println("Rang A : " + a / 100 + "    Reste : " + resteA / 100);
  //      System.out.println("Rang B : " + b / 100 + "    Reste : " + resteB / 100);
        resultat = findViewById(R.id.resultat);
        resultat.setText("Rang A : " + a / 100 + "    Reste : " + resteA / 100 + "\n" +
                "Rang B : " + b / 100 + "    Reste : " + resteB / 100 + "\n" +
                "Surface inutilisée : " + surfaceInutilisée / 100 + "/" + surfaceRemorque / 100 + "\n" +
                debordementPalette[0] + " P80 à quai" + "\n" +
                debordementPalette[1] + " P100 à quai"
        );

        //refacto :
        drawView.listeP100RangComplet3 = listeP100RangComplet3;
        drawView.listeP80RangComplet3 = listeP80RangComplet3;
        drawView.listeP80RangComplet2 = listeP80RangComplet2;
        drawView.listeP100RangComplet2 = listeP100RangComplet2;

        drawView.longueurOccupéeParLesRangsComplets80Par3 = longueurOccupéeParLesRangsComplets80Par3;
        drawView.longueurOccupéeParLesRangsComplets80Par2 = longueurOccupéeParLesRangsComplets80Par2;
        drawView.longueurOccupéeParLesRangsComplets100Par2 = longueurOccupéeParLesRangsComplets100Par2;
        drawView.longueurOccupéeParLesRangsComplets100Par3 = longueurOccupéeParLesRangsComplets100Par3;


        drawView.listeDesListesDeRangComplets = listeDesListesDeRangComplets;

        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;

        drawPosRemorque.removeView(drawView);
        drawPosRemorque.addView(drawView);

    }


    public int[] compterLesPalettesQuiNePassentPas(ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets, ArrayList<Palette> listeP80, ArrayList<Palette> listeP100, Remorque remorque) {
        // tableau de comptage premier element pour les 80, second element pour les 100
        int t[] = {0, 0};

        //   int longueurOccupée = 0;
        //   int positionDeFin = 0;
        //  positionDeFin = longueurOccupée + pal.positionYb;


        for (int i = 0; i < listeDesListesDeRangComplets.size(); i++) {
            //    longueurOccupée = positionDeFin;

            for (Rang r : listeDesListesDeRangComplets.get(i)) {
                for (Palette p : r.listePaletteDuRang) {
                    if (p.positionX > remorque.largeur || p.positionXb > remorque.largeur || p.positionY > remorque.longueur || p.positionYb > remorque.longueur) {
                        if (p.getLargeur() == 80) {
                            t[0] = t[0] + 1;
                        }
                        if (p.getLargeur() == 100) {
                            t[1] = t[1] + 1;
                        }

                    }
                    //   positionDeFin = longueurOccupée + p.positionYb;
                }

            }
        }

        // longueurOccupée = positionDeFin;//on pense à noter la derniere prise de valeur
        //on passe les liste de palettes restantes


        for (Palette p : listeP80) {
            if (p.positionX > remorque.longueur || p.positionXb > remorque.longueur || p.positionY > remorque.longueur || p.positionYb > remorque.longueur) {
                if (p.getLargeur() == 80) {
                    t[0] = t[0] + 1;
                }
                if (p.getLargeur() == 100) {
                    t[1] = t[1] + 1;
                }

            }
        }

        for (Palette p : listeP100) {
            if (p.positionX > remorque.longueur || p.positionXb > remorque.longueur || p.positionY > remorque.longueur || p.positionYb > remorque.longueur) {
                if (p.getLargeur() == 80) {
                    t[0] = t[0] + 1;
                }
                if (p.getLargeur() == 100) {
                    t[1] = t[1] + 1;
                }

            }
        }

        return t;
    }


}
