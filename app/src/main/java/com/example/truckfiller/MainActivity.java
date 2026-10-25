package com.example.truckfiller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    boolean gaucheOuBas = false;
    DrawView drawView;
    float agrandissement = 1.5f;
    Remorque remorque;
    ArrayList<Palette> listeP80 = new ArrayList<>();
    ArrayList<Palette> listeP100 = new ArrayList<>();
    ArrayList<Palette> listeRolls = new ArrayList<>();
    ArrayList<Rang> listeP80RangComplet3 = new ArrayList<Rang>();
    ArrayList<Rang> listeP80RangComplet2 = new ArrayList<Rang>();
    ArrayList<Rang> listeP100RangComplet2 = new ArrayList<Rang>();
    ArrayList<Rang> listeP100RangComplet3 = new ArrayList<Rang>();
    ArrayList<Rang> listeRollsRangComplet4 = new ArrayList<Rang>();
    ArrayList<Rang> listeRollsRangComplet2 = new ArrayList<Rang>();
    //ArrayList<Rang> listeRangMixte = new ArrayList<Rang>();
    ArrayList<ArrayList<Rang>> listeDesListesDeRangComplets = new ArrayList<ArrayList<Rang>>();
    private LinearLayout linearLayoutA;
    private TextInputEditText longueurRemorqueId;
    private TextInputEditText largeurRemorqueId;
    private TextInputEditText nombre80120;
    private TextInputEditText nombre100120;
    private TextInputEditText nombreRolls;
    private TextInputEditText poidPalet;
    private TextInputEditText piecesParColis;
    private TextInputEditText tailleEnCmInput;
    private TextInputEditText nombreDeSeparations;
    private EditText resultat;
    //palettes hors rangs complet
    private ToggleButton switchForcerCasserUnRang80;
    private ToggleButton switchForcerRotation100;
    private ToggleButton switchForcerRotationRolls;
    private ToggleButton switchAutoriserRotation100Seulle;
    private boolean autorisationForcerCasserUnRang80;
    private boolean autorisationForcerRotation100;
    private boolean autorisationForcerRotationRolls;
    private TextInputEditText casser80120NombreTextInput;
    private TextInputEditText focerRotation100120NombreTextInput;
    //
    private ToggleButton switchAutorisationRangBatard80;
    private ToggleButton switchAutoriserRotation100SiPossibleDeFaireUnRang;
    private ToggleButton switchAutoriserRotationRollsSiPossibleDeFaireUnRang;
    private boolean autorisationRangBatard80;
    private boolean autorisationRotation100SiPossibleDeFaireUnRang = true;
    private boolean autorisationRotationRollsSiPossibleDeFaireUnRang = true;
    private View drawlocation;
    private LinearLayout drawPosRemorque;
    private ScrollView scrollViewRemorque;
    private LinearLayout linearLayoutContenu;
    private ScrollView scrollViewContenu;
    boolean oldMode = true;
    private Switch switchDevMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        drawView = new DrawView(this);
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
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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


                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        nombreRolls = findViewById(R.id.nombreRolls);
        nombreRolls.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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


                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        focerRotation100120NombreTextInput = findViewById(R.id.rotation100120nombre);
        focerRotation100120NombreTextInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        tailleEnCmInput = findViewById(R.id.tailleencm);
        tailleEnCmInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });
        nombreDeSeparations = findViewById(R.id.nombredeseparations);
        nombreDeSeparations.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        switchForcerRotation100 = findViewById(R.id.forcerRotation100);
        switchForcerRotation100.setChecked(false);
        switchForcerRotation100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autorisationForcerRotation100 == true) {
                    autorisationForcerRotation100 = false;
                    switchForcerRotation100.setChecked(false);
                } else {
                    autorisationForcerRotation100 = true;
                    switchForcerRotation100.setChecked(true);
                }
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        switchForcerRotationRolls = findViewById(R.id.forcerRotationRolls);
        switchForcerRotationRolls.setChecked(false);
        switchForcerRotationRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autorisationForcerRotationRolls == true) {
                    autorisationForcerRotationRolls = false;
                    switchForcerRotationRolls.setChecked(false);
                } else {
                    autorisationForcerRotationRolls = true;
                    switchForcerRotationRolls.setChecked(true);
                }
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
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
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        switchAutoriserRotation100SiPossibleDeFaireUnRang = findViewById(R.id.autoriserRotation100SiPossibleDeFaireUnRang);
        switchAutoriserRotation100SiPossibleDeFaireUnRang.setChecked(true);
        switchAutoriserRotation100SiPossibleDeFaireUnRang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autorisationRotation100SiPossibleDeFaireUnRang == true) {
                    autorisationRotation100SiPossibleDeFaireUnRang = false;
                    switchAutoriserRotation100SiPossibleDeFaireUnRang.setChecked(false);
                } else {
                    autorisationRotation100SiPossibleDeFaireUnRang = true;
                    switchAutoriserRotation100SiPossibleDeFaireUnRang.setChecked(true);
                }
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });

        switchAutoriserRotationRollsSiPossibleDeFaireUnRang = findViewById(R.id.autoriserRotationRollsSiPossibleDeFaireUnRang);
        switchAutoriserRotationRollsSiPossibleDeFaireUnRang.setChecked(true);
        switchAutoriserRotationRollsSiPossibleDeFaireUnRang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autorisationRotationRollsSiPossibleDeFaireUnRang == true) {
                    autorisationRotationRollsSiPossibleDeFaireUnRang = false;
                    switchAutoriserRotationRollsSiPossibleDeFaireUnRang.setChecked(false);
                } else {
                    autorisationRotationRollsSiPossibleDeFaireUnRang = true;
                    switchAutoriserRotationRollsSiPossibleDeFaireUnRang.setChecked(true);
                }
                if (oldMode) {
                    calculer();
                } else {
                    calculer4();
                }
            }
        });


        switchDevMode = findViewById(R.id.oldModetid);
        switchDevMode.setChecked(false);
        switchDevMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   switcherEtat(switchAutoriserRotation80.isChecked());
                if (oldMode == true) {
                    oldMode = false;
                    drawView.oldMode = false;
                    switchDevMode.setChecked(true);
                } else {
                    oldMode = true;
                    drawView.oldMode = true;
                    switchDevMode.setChecked(false);
                }
                if (oldMode) {
                   // creerRemorque(1320, 240, 300);
                    calculer();
                } else {
                  //  creerRemorque(1320, 240, 300);
                    calculer4();
                }
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


    public int decalageBarresDeSeparation() {
        ///// barres de separations  ///////////////////////////////
        int nombreDeSeparationsInt = 0;
        try {
            nombreDeSeparationsInt = Integer.parseInt(nombreDeSeparations.getText().toString());
        } catch (Exception e) {
            nombreDeSeparationsInt = 0;
        }
        int tailleEnCmInt = 0;
        try {
            tailleEnCmInt = Integer.parseInt(tailleEnCmInput.getText().toString());
        } catch (Exception e) {
            tailleEnCmInt = 0;
        }
        int decalageBarresDeSeparation = 0;
        decalageBarresDeSeparation = nombreDeSeparationsInt * tailleEnCmInt;
        return decalageBarresDeSeparation;
    }


    public int faireRang(ArrayList<Palette> listePalette, int nombreDeRangsPossible, int NombreDEmplacements, ArrayList<Rang> listeDeRangComplet, int orientation, int longueurOccupeeParLesRangsPrecededants) {
        listeDeRangComplet.clear();
        int longeurDuGroupeDeRangs = 0;
        int iY = 0;
        int longueurLargeurSelonOrientation = 0;
        longueurOccupeeParLesRangsPrecededants = longueurOccupeeParLesRangsPrecededants + decalageBarresDeSeparation();
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
        longueurOccupeeParLesRangsPrecededants = longueurOccupeeParLesRangsPrecededants + decalageBarresDeSeparation();
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


        // if (drawView == null) {
        //    drawView = new DrawView(this);
        // }
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
        drawView.listeRollsRangComplet4 = listeRollsRangComplet4;
        drawView.listeRollsRangComplet2 = listeRollsRangComplet2;

        drawView.longueurOccupéeParLesRangsComplets80Par3 = 0;
        drawView.longueurOccupéeParLesRangsComplets80Par2 = 0;
        drawView.longueurOccupéeParLesRangsComplets100Par2 = 0;
        drawView.longueurOccupéeParLesRangsComplets100Par3 = 0;
        drawView.longueurOccupéeParLesRangsCompletsRollsPar4 = 0;
        drawView.longueurOccupéeParLesRangsCompletsRollsPar2 = 0;


        drawView.listeDesListesDeRangComplets = listeDesListesDeRangComplets;

        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;
        drawView.listeRolls = listeRolls;
        drawView.remorque = remorque;
        drawView.agrandissement = agrandissement;


        drawPosRemorque.removeView(drawView);
        drawPosRemorque.addView(drawView);


    }


    private void calculer2() {
        drawPosRemorque = findViewById(R.id.drawPosRemorque);

        listeP80.clear();
        listeP100.clear();

        new Thread(new Runnable() {
            public void run() {


                final ArrayList<Palette> listeDePalettesPosees = new ArrayList<>();

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



                int nombreDe100ForceeEnRotation =0;
                //drawPosRemorque.removeView(drawView);
                try {
                    if (autorisationForcerRotation100 == true) {
                        nombreDe100ForceeEnRotation = Integer.parseInt(focerRotation100120NombreTextInput.getText().toString());
                    }
                }catch (Exception e){}

///////////////////////Template
                Palette item1 = new Palette();
                item1.longueur = 120;
                item1.largeur = 100;

                Palette item2 = new Palette();
                item2.longueur = 120;
                item2.largeur = 80;
                //templates possibles rangs droits
                try {
                    item1 = listeP100.get(0);
                    if (remorque.largeur % item1.getLargeur() == 0) {
                        //rang parfait possible en longueur
                        item1.rangLongueur = true;
                    }
                    if (remorque.largeur % item1.getLongueur() == 0) {
                        //rang parfait possible en largeur
                        item1.rangLargeur = true;
                    }
                } catch (Exception e) {
                }
                try {
                    item2 = listeP80.get(0);
                    if (remorque.largeur % item2.getLargeur() == 0) {
                        //rang parfait possible en longueur
                        item2.rangLongueur = true;
                    }
                    if (remorque.largeur % item2.getLongueur() == 0) {
                        //rang parfait possible en largeur
                        item2.rangLargeur = true;
                    }
                } catch (Exception e) {
                }
                //batard////////////////////////////////////////// @ doubler ?
               try {
                    int reste = remorque.largeur - item1.getLargeur();
                    if (reste % item2.getLargeur() == 0) {
                        //rang parfait batard possible en longueur
                        item2.rangBatardLongueurLongueur = true;
                        item1.rangBatardLongueurLongueur = true;
                    }
                    if (reste % item2.getLongueur() == 0) {
                        //rang parfait batard possible en largeur
                        item2.rangBatardLongueurLargeur = true;
                        item1.rangBatardLongueurLargeur = true;
                    }

                    reste = remorque.largeur - item1.getLongueur();
                    if (reste % item2.getLargeur() == 0) {
                        //rang parfait batard possible en longueur
                        item2.rangBatardLargeurLongueur = true;
                        item1.rangBatardLargeurLongueur = true;
                    }
                    if (reste % item2.getLongueur() == 0) {
                        //rang parfait batard possible en largeur
                        item2.rangBatardLargeurLargeur = true;
                        item1.rangBatardLargeurLargeur = true;
                    }
                } catch (Exception e) {
                }


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
                double moduloAc = 0;
                double moduloBc = 0;

                boolean reverse = false;
                int compteX = 0;
                int compteY = 0;
                int f = 0;
                int e = 0;
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
                for (ArrayList<Palette> listeEncours : new ArrayList<ArrayList<Palette>>(listeDesListesDePalettesAPoser)) {


                    for (Palette p : new ArrayList<Palette>(listeEncours)) {
                        boolean palettePosee = false;


                        for (int iY = 0 + e; iY < remorque.longueur; iY = iY + 20) {
                            if (palettePosee == true) {
                                break;
                            }
                            for (int iX = 0 + f; iX < remorque.largeur; iX = iX + 20) {
                                int orientation = 0;
                                p.setPositionX(iX);
                                p.setPositionY(iY);


                                //[objectif RANG] orientation en fonction d'un multiple de la largeur de la remorque et d'une dimension de la palette
                                double moduloA = (remorque.largeur - iX) % p.getLargeur();
                                double moduloB = (remorque.largeur - iX) % p.getLongueur();
                                if (moduloA == 0 || p.getOrientationFavorite() == 0) {
                                    //orientation //&& moduloA >= moduloB
                                    orientation = 0;
                                } else if (moduloB == 0) {
                                    orientation = 1;
                                }


                                p.setOrientation(orientation);
                                // si la position selectionnée ne cause pas de superposition avec une autre palette et de depasse pas du container
                                if (estSuperposeAUnAutre(listeDePalettesPosees, p) == false && p.getPositionXb() <= remorque.largeur && p.getPositionYb() <= remorque.longueur) { //&& p.getPositionYb() <= remorque.longueur

                                    ////////////////////////////////////chercheur d'obstacle vers la droite
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
                                        int mesure = xl - incrementPointeur + pointeur.getLargeur();
                                        if (estSuperposeAUnAutre(listeDePalettesPosees, pointeur) == true || mesure >= remorque.largeur) {

                                            double moduloAb = (mesure - iX) % p.getLargeur();
                                            double moduloBb = (mesure - iX) % p.getLongueur();
                                            if (moduloAb == 0) {
                                                orientation = 0;
                                            } else if (moduloBb == 0) {
                                                orientation = 1;
                                            } else if (moduloAb < moduloBb) {
                                                orientation = 0;
                                            } else  {
                                                orientation = 1;
                                            }
                                            if (autorisationForcerRotation100 == true && p.getId() % 2 == 0){
                                                if(nombreDe100ForceeEnRotation > 0){
                                                    orientation = 0;
                                                    nombreDe100ForceeEnRotation = nombreDe100ForceeEnRotation-1;
                                                }
                                            }
                                            obstacleTrouve = true;
                                            break;
                                        }


                                    }

                                    ////////////////////////////////////chercheur d'obstacle vers la gauche
                                    //debranché noptimase pas la place ou tres peu
                                    incrementPointeur = 1;  //doit etre inferieur ou egal à lecart minimum possible du fait de l'agencement possible des palettes
                                    boolean videAAGauche = false;
                                    for (int xl = p.getPositionX() - 1; xl > 0; xl = xl - incrementPointeur) {
                                        Palette pointeurGauche = new Palette();
                                        pointeurGauche.setLargeur(1);
                                        pointeurGauche.setLongueur(120);
                                        pointeurGauche.setPositionX(xl);
                                        pointeurGauche.setPositionY(p.getLargeur() + p.getPositionY() - 1);
                                        pointeurGauche.setOrientation(0);
                                        ////on determine la place sur la gauche
                                        int mesure = xl - incrementPointeur + pointeurGauche.getLargeur();
                                        if (estSuperposeAUnAutre(listeDePalettesPosees, pointeurGauche) == true || mesure <= 0) {
                                            break;

                                        } else {
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

                                } else {
                                    // s'il y a superposition on tourne la palette et on retente
                                    if (p.getOrientation() == 1) {
                                        p.setOrientation(0);
                                    } else {
                                        p.setOrientation(1);
                                    }
                                    if (estSuperposeAUnAutre(listeDePalettesPosees, p) == false && p.getPositionXb() <= remorque.largeur && p.getPositionYb() <= remorque.longueur) { //&& p.getPositionYb() <= remorque.longueur

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
                                                int mesure = xl - incrementPointeur + pointeur.getLargeur();
                                                double moduloAb = (mesure) % p.getLargeur();
                                                double moduloBb = (mesure) % p.getLongueur();
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
                        for (Palette pp : listeDePalettesPosees) {
                            surfaceUtiliseeA = surfaceUtiliseeA + pp.getSurface() / 100;
                        }
                        final double surfaceUtilisee = surfaceUtiliseeA;
                        final double surfaceRemorque = (remorque.longueur * remorque.largeur) / 100;
                        //       System.out.println("                                                        ");
                        //  System.out.println("Rang A : " + a / 100 + "    Reste : " + resteA / 100);
                        //   System.out.println("Rang B : " + b / 100 + "    Reste : " + resteB / 100);

                        resultat = findViewById(R.id.resultat);
                        resultat.setText("Rang A : " + 0 / 100 + "    Reste : " + 0 / 100 + "\n" +
                                "Rang B : " + 0 / 100 + "    Reste : " + 0 / 100 + "\n" +
                                "Surface inutilisée : " + ((surfaceRemorque - surfaceUtilisee) / 100) + "/" + surfaceRemorque / 100 + "\n" +
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
    private boolean estSuperposeAUnAutre(ArrayList<Palette> listeDePalettesPosees, Palette p) {
        boolean resultat = false;
        for (Palette p1 : listeDePalettesPosees) { //refacto :commencer par la fin de la liste
            if (p1.getPositionX() < p.getPositionXb() && p1.getPositionXb() > p.getPositionX() && p1.getPositionY() < p.getPositionYb() && p1.getPositionYb() > p.getPositionY()) {
                resultat = true;
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
        longueurTotaleOccupée = longueurTotaleOccupée + decalageBarresDeSeparation();
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

    // =============================================================================================
    // SOLUTION A : Algorithme complet par Profils (Tranches & Couloirs) + Optimisation Combinatoire
    // Adapté dynamiquement à toutes les dimensions de remorque (largeur / longueur)
    // =============================================================================================

    private static class MotifTranche3 {
        int nb100;
        int nb80;
        int deltaY;
        int orient100;
        int orient80;

        MotifTranche3(int nb100, int nb80, int deltaY, int orient100, int orient80) {
            this.nb100 = nb100;
            this.nb80 = nb80;
            this.deltaY = deltaY;
            this.orient100 = orient100;
            this.orient80 = orient80;
        }
    }

    private static class ProfilCouloirs3 {
        int c120;
        int c100;
        int c80;

        ProfilCouloirs3(int c120, int c100, int c80) {
            this.c120 = c120;
            this.c100 = c100;
            this.c80 = c80;
        }

        int totalCouloirs() {
            return c120 + c100 + c80;
        }
    }

    private static class EvalSolution3 {
        int r100Long;
        int r100Larg;
        MotifTranche3 motifMixte;
        int rMixte;
        int r80Long;
        ProfilCouloirs3 profilCouloirs;
        int[] col100Count;
        int[] col100RotCount;
        int[] col80Count;
        int[] colLargeur;
        int[] colXStart;

        int nbPalettesChargees = -1;
        int surfaceChargee = -1;
        int surfacePerdueFond = Integer.MAX_VALUE;
        int nbP80BonSens = -1;
        int maxY = Integer.MAX_VALUE;
        int ecartCouloirs = Integer.MAX_VALUE;
        int nbRangsComplets = -1;
        int rangA = 0;
        int rangB = 0;

        boolean estMeilleureQue(EvalSolution3 autre) {
            if (autre == null || autre.nbPalettesChargees < 0) {
                return true;
            }
            if (this.nbPalettesChargees != autre.nbPalettesChargees) {
                return this.nbPalettesChargees > autre.nbPalettesChargees;
            }
            if (this.surfaceChargee != autre.surfaceChargee) {
                return this.surfaceChargee > autre.surfaceChargee;
            }
            if (this.surfacePerdueFond != autre.surfacePerdueFond) {
                return this.surfacePerdueFond < autre.surfacePerdueFond;
            }
            if (this.nbP80BonSens != autre.nbP80BonSens) {
                return this.nbP80BonSens > autre.nbP80BonSens;
            }
            if (this.maxY != autre.maxY) {
                return this.maxY < autre.maxY;
            }
            if (this.ecartCouloirs != autre.ecartCouloirs) {
                return this.ecartCouloirs < autre.ecartCouloirs;
            }
            return this.nbRangsComplets > autre.nbRangsComplets;
        }
    }

    private int lireEntierChamp(TextInputEditText champ, int valeurDefaut) {
        if (champ == null || champ.getText() == null) {
            return valeurDefaut;
        }
        try {
            String texte = champ.getText().toString().trim();
            if (texte.isEmpty()) {
                return valeurDefaut;
            }
            return Math.max(0, Integer.parseInt(texte));
        } catch (Exception e) {
            return valeurDefaut;
        }
    }

    private void calculer3() {
        drawPosRemorque = findViewById(R.id.drawPosRemorque);
        resultat = findViewById(R.id.resultat);

        listeP80.clear();
        listeP100.clear();

        final int p80Total = lireEntierChamp(nombre80120, 0);
        final int p100Total = lireEntierChamp(nombre100120, 0);
        final int W = (remorque != null) ? Math.max(0, remorque.largeur) : 240;
        final int L = (remorque != null) ? Math.max(0, remorque.longueur) : 1320;
        final int yDepart = decalageBarresDeSeparation();

        int casser80Force = 0;
        if (autorisationForcerCasserUnRang80) {
            casser80Force = lireEntierChamp(casser80120NombreTextInput, 0);
        }
        int rotation100Force = 0;
        if (autorisationForcerRotation100) {
            rotation100Force = lireEntierChamp(focerRotation100120NombreTextInput, 0);
        }

        for (int i = 0; i < p80Total; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.longueur = 120;
            p.largeur = 80;
            p.orientation = 0;
            listeP80.add(p);
        }
        for (int i = 0; i < p100Total; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.longueur = 120;
            p.largeur = 100;
            p.orientation = 1;
            listeP100.add(p);
        }

        ArrayList<Palette> listeDePalettesPosees = new ArrayList<>();

        if ((p80Total == 0 && p100Total == 0) || W < 80 || L <= 0) {
            int yCur = yDepart;
            for (Palette p : listeP100) {
                p.setPositionX(0);
                p.setPositionY(yCur);
                p.setOrientation(0);
                listeDePalettesPosees.add(p);
                yCur = p.getPositionYb();
            }
            for (Palette p : listeP80) {
                p.setPositionX(0);
                p.setPositionY(yCur);
                p.setOrientation(0);
                listeDePalettesPosees.add(p);
                yCur = p.getPositionYb();
            }
            afficherResultatsCalculer3(listeDePalettesPosees, p80Total, p100Total, yDepart, yDepart, W, L);
            return;
        }

        final int cap100Long = W / 100;
        final int cap100Larg = W / 120;
        final int cap80Long = W / 80;

        ArrayList<MotifTranche3> motifsMixtes = new ArrayList<>();
        motifsMixtes.add(new MotifTranche3(0, 0, 0, 0, 0));
        for (int a = 1; a * 100 <= W; a++) {
            int resteW = W - a * 100;
            int b = resteW / 80;
            if (b >= 1) {
                motifsMixtes.add(new MotifTranche3(a, b, 120, 0, 0));
            }
        }

        ArrayList<ProfilCouloirs3> profilsCouloirs = genererProfilsCouloirs3(W);

        EvalSolution3 meilleure = null;

        int maxR100Long = (cap100Long > 0) ? (p100Total / cap100Long) : 0;
        for (int r100Long = 0; r100Long <= maxR100Long; r100Long++) {
            int rem100ApresLong = p100Total - r100Long * cap100Long;
            int maxR100Larg = (cap100Larg > 0) ? (rem100ApresLong / cap100Larg) : 0;

            for (int r100Larg = 0; r100Larg <= maxR100Larg; r100Larg++) {
                int rem100Apres100 = rem100ApresLong - r100Larg * cap100Larg;

                for (MotifTranche3 motifMixte : motifsMixtes) {
                    int maxRMixte = 0;
                    if (motifMixte.nb100 > 0 && motifMixte.nb80 > 0) {
                        maxRMixte = Math.min(rem100Apres100 / motifMixte.nb100, p80Total / motifMixte.nb80);
                    }

                    for (int rMixte = 0; rMixte <= maxRMixte; rMixte++) {
                        int rem100ApresMixte = rem100Apres100 - rMixte * motifMixte.nb100;
                        int rem80ApresMixte = p80Total - rMixte * motifMixte.nb80;

                        int maxR80Possible = (cap80Long > 0) ? (rem80ApresMixte / cap80Long) : 0;
                        int plafondR80 = maxR80Possible;
                        if (autorisationForcerCasserUnRang80 && casser80Force > 0) {
                            plafondR80 = Math.max(0, maxR80Possible - Math.min(casser80Force, maxR80Possible));
                        }

                        int debutR80 = 0;
                        if (autorisationForcerCasserUnRang80 && casser80Force > 0) {
                            debutR80 = plafondR80;
                        }

                        for (int r80Long = debutR80; r80Long <= plafondR80; r80Long++) {
                            int rem100 = rem100ApresMixte;
                            int rem80 = rem80ApresMixte - r80Long * cap80Long;

                            int yApresTranches = yDepart
                                    + r100Long * 120
                                    + r100Larg * 100
                                    + rMixte * motifMixte.deltaY
                                    + r80Long * 120;

                            int nbRangsComplets = r100Long + r100Larg + rMixte + r80Long;
                            int surfacePerdueTranches = r100Long * (W - cap100Long * 100) * 120
                                    + r100Larg * (W - cap100Larg * 120) * 100
                                    + rMixte * (W - (motifMixte.nb100 * 100 + motifMixte.nb80 * 80)) * motifMixte.deltaY
                                    + r80Long * (W - cap80Long * 80) * 120;

                            int palettesChargeesTranches = compterPalettesChargeesTranches3(
                                    yDepart, r100Long, cap100Long, r100Larg, cap100Larg,
                                    rMixte, motifMixte, r80Long, cap80Long, L
                            );
                            int surfaceChargeeTranches = compterSurfaceChargeeTranches3(
                                    yDepart, r100Long, cap100Long, r100Larg, cap100Larg,
                                    rMixte, motifMixte, r80Long, cap80Long, L
                            );
                            int p80BonSensTranches = compterP80BonSensTranches3(
                                    yDepart, r100Long, r100Larg,
                                    rMixte, motifMixte, r80Long, cap80Long, L
                            );

                            if (rem100 == 0 && rem80 == 0) {
                                int perteBoutTranches = 0;
                                if (yApresTranches <= L) {
                                    int resteFin = L - yApresTranches;
                                    if (resteFin > 0 && resteFin < 80) {
                                        perteBoutTranches = resteFin * W;
                                    }
                                }
                                EvalSolution3 cand = new EvalSolution3();
                                cand.r100Long = r100Long;
                                cand.r100Larg = r100Larg;
                                cand.motifMixte = motifMixte;
                                cand.rMixte = rMixte;
                                cand.r80Long = r80Long;
                                cand.profilCouloirs = new ProfilCouloirs3(0, 0, 0);
                                cand.col100Count = new int[0];
                                cand.col100RotCount = new int[0];
                                cand.col80Count = new int[0];
                                cand.colLargeur = new int[0];
                                cand.colXStart = new int[0];
                                cand.nbPalettesChargees = palettesChargeesTranches;
                                cand.surfaceChargee = surfaceChargeeTranches;
                                cand.surfacePerdueFond = surfacePerdueTranches + perteBoutTranches;
                                cand.nbP80BonSens = p80BonSensTranches;
                                cand.maxY = yApresTranches;
                                cand.ecartCouloirs = 0;
                                cand.nbRangsComplets = nbRangsComplets;
                                cand.rangA = yApresTranches;
                                cand.rangB = yApresTranches;

                                if (cand.estMeilleureQue(meilleure)) {
                                    meilleure = cand;
                                }
                                continue;
                            }

                            for (ProfilCouloirs3 profil : profilsCouloirs) {
                                EvalSolution3 cand = evaluerReliquatDansProfil3(
                                        profil, rem100, rem80, rotation100Force,
                                        yApresTranches, W, L, cap80Long,
                                        r100Long, r100Larg, motifMixte, rMixte, r80Long,
                                        palettesChargeesTranches, surfaceChargeeTranches,
                                        p80BonSensTranches, surfacePerdueTranches, nbRangsComplets
                                );
                                if (cand != null && cand.estMeilleureQue(meilleure)) {
                                    meilleure = cand;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (meilleure != null) {
            construirePalettesPosees3(
                    meilleure, listeP100, listeP80, listeDePalettesPosees,
                    yDepart, W, L, cap100Long, cap100Larg, cap80Long
            );
            afficherResultatsCalculer3(
                    listeDePalettesPosees, p80Total, p100Total,
                    meilleure.rangA, meilleure.rangB, W, L
            );
        } else {
            afficherResultatsCalculer3(listeDePalettesPosees, p80Total, p100Total, yDepart, yDepart, W, L);
        }
    }

    private ArrayList<ProfilCouloirs3> genererProfilsCouloirs3(int W) {
        ArrayList<ProfilCouloirs3> liste = new ArrayList<>();
        for (int c120 = W / 120; c120 >= 0; c120--) {
            int r1 = W - c120 * 120;
            for (int c100 = r1 / 100; c100 >= 0; c100--) {
                int r2 = r1 - c100 * 100;
                int c80 = r2 / 80;
                if (c120 + c100 + c80 > 0) {
                    liste.add(new ProfilCouloirs3(c120, c100, c80));
                }
                if (c80 > 0 && (c120 + c100) > 0) {
                    liste.add(new ProfilCouloirs3(c120, c100, 0));
                }
            }
        }
        return liste;
    }

    private int compterPalettesChargeesTranches3(
            int yDepart, int r100Long, int cap100Long, int r100Larg, int cap100Larg,
            int rMixte, MotifTranche3 motifMixte, int r80Long, int cap80Long, int L
    ) {
        int y = yDepart;
        int total = 0;
        for (int i = 0; i < r100Long; i++) {
            y += 120;
            if (y <= L) total += cap100Long;
        }
        for (int i = 0; i < r100Larg; i++) {
            y += 100;
            if (y <= L) total += cap100Larg;
        }
        for (int i = 0; i < rMixte; i++) {
            y += motifMixte.deltaY;
            if (y <= L) total += (motifMixte.nb100 + motifMixte.nb80);
        }
        for (int i = 0; i < r80Long; i++) {
            y += 120;
            if (y <= L) total += cap80Long;
        }
        return total;
    }

    private int compterSurfaceChargeeTranches3(
            int yDepart, int r100Long, int cap100Long, int r100Larg, int cap100Larg,
            int rMixte, MotifTranche3 motifMixte, int r80Long, int cap80Long, int L
    ) {
        int y = yDepart;
        int surface = 0;
        for (int i = 0; i < r100Long; i++) {
            y += 120;
            if (y <= L) surface += cap100Long * (100 * 120);
        }
        for (int i = 0; i < r100Larg; i++) {
            y += 100;
            if (y <= L) surface += cap100Larg * (100 * 120);
        }
        for (int i = 0; i < rMixte; i++) {
            y += motifMixte.deltaY;
            if (y <= L) surface += motifMixte.nb100 * (100 * 120) + motifMixte.nb80 * (80 * 120);
        }
        for (int i = 0; i < r80Long; i++) {
            y += 120;
            if (y <= L) surface += cap80Long * (80 * 120);
        }
        return surface;
    }

    private int compterP80BonSensTranches3(
            int yDepart, int r100Long, int r100Larg,
            int rMixte, MotifTranche3 motifMixte, int r80Long, int cap80Long, int L
    ) {
        int y = yDepart + r100Long * 120 + r100Larg * 100;
        int total80BonSens = 0;
        for (int i = 0; i < rMixte; i++) {
            y += motifMixte.deltaY;
            if (y <= L) total80BonSens += motifMixte.nb80;
        }
        for (int i = 0; i < r80Long; i++) {
            y += 120;
            if (y <= L) total80BonSens += cap80Long;
        }
        return total80BonSens;
    }

    private EvalSolution3 evaluerReliquatDansProfil3(
            ProfilCouloirs3 profil, int rem100, int rem80, int rotation100Force,
            int yBase, int W, int L, int cap80Long,
            int r100Long, int r100Larg, MotifTranche3 motifMixte, int rMixte, int r80Long,
            int palettesChargeesTranches, int surfaceChargeeTranches,
            int p80BonSensTranches, int surfacePerdueTranches, int nbRangsComplets
    ) {
        int c120 = profil.c120;
        int c100 = profil.c100;
        int c80 = profil.c80;
        int K = c120 + c100 + c80;
        if (K <= 0) {
            return null;
        }
        if (rem100 > 0 && c120 == 0 && c100 == 0 && W >= 100) {
            return null;
        }

        EvalSolution3 meilleureLocale = null;

        int minU100 = (c120 == 0) ? rem100 : 0;
        int maxU100 = (c100 > 0 || (c120 == 0 && W < 100)) ? rem100 : 0;

        for (int u100 = minU100; u100 <= maxU100; u100++) {
            int m100 = rem100 - u100;

            // On n'envoie des P80 dans un couloir de 100cm (ce qui perd 20cm de large) que s'il n'y a ni couloir 120 ni couloir 80
            int maxU80 = (c100 > 0 && c120 == 0 && c80 == 0) ? rem80 : 0;
            for (int u80 = 0; u80 <= maxU80; u80++) {
                if (c100 > 0 && (u100 + u80) < c100 && (rem100 + rem80) > 1) {
                    continue;
                }
                int reste80Apres100 = rem80 - u80;

                int minV80 = (c120 == 0) ? reste80Apres100 : 0;
                int maxV80 = (c80 > 0) ? reste80Apres100 : 0;
                if (minV80 > maxV80) {
                    continue;
                }

                for (int v80 = minV80; v80 <= maxV80; v80++) {
                    if (c120 == 0 && c100 == 0 && cap80Long > 0 && v80 >= cap80Long) {
                        continue;
                    }
                    int m80 = reste80Apres100 - v80;
                    if (c120 == 0 && (m100 > 0 || m80 > 0)) {
                        continue;
                    }

                    EvalSolution3 cand = repartirDansCouloirs120(
                            profil, m100, m80, u100, u80, v80, rotation100Force,
                            yBase, W, L,
                            r100Long, r100Larg, motifMixte, rMixte, r80Long,
                            palettesChargeesTranches, surfaceChargeeTranches,
                            p80BonSensTranches, surfacePerdueTranches, nbRangsComplets
                    );
                    if (cand != null && cand.estMeilleureQue(meilleureLocale)) {
                        meilleureLocale = cand;
                    }
                }
            }
        }
        return meilleureLocale;
    }

    private EvalSolution3 repartirDansCouloirs120(
            final ProfilCouloirs3 profil,
            int m100, int m80, final int u100, final int u80, final int v80,
            final int rotation100Force,
            final int yBase, final int W, final int L,
            final int r100Long, final int r100Larg, final MotifTranche3 motifMixte, final int rMixte, final int r80Long,
            final int palettesChargeesTranches, final int surfaceChargeeTranches,
            final int p80BonSensTranches, final int surfacePerdueTranches, final int nbRangsComplets
    ) {
        final int c120 = profil.c120;
        final int c100 = profil.c100;
        final int c80 = profil.c80;
        final int K = c120 + c100 + c80;

        final int[] temp100 = new int[K];
        final int[] temp80 = new int[K];

        if (c100 > 0) {
            int totalC100 = u100 + u80;
            int base = totalC100 / c100;
            int reste = totalC100 % c100;
            int rest100 = u100;
            int rest80 = u80;
            for (int i = 0; i < c100; i++) {
                int quota = base + (i < reste ? 1 : 0);
                int prend100 = Math.min(rest100, quota);
                int prend80 = Math.min(rest80, quota - prend100);
                temp100[c120 + i] = prend100;
                temp80[c120 + i] = prend80;
                rest100 -= prend100;
                rest80 -= prend80;
            }
        } else if (u100 > 0 && c80 > 0 && W < 100) {
            temp100[0] = u100;
        }

        if (c80 > 0) {
            int base = v80 / c80;
            int reste = v80 % c80;
            for (int i = 0; i < c80; i++) {
                temp80[c120 + c100 + i] += base + (i < reste ? 1 : 0);
            }
        }

        final EvalSolution3[] bestRef = new EvalSolution3[1];

        if (c120 == 0) {
            evaluerConfigurationCouloirsComplete(
                    profil, temp100, temp80, rotation100Force,
                    yBase, W, L,
                    r100Long, r100Larg, motifMixte, rMixte, r80Long,
                    palettesChargeesTranches, surfaceChargeeTranches,
                    p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                    bestRef
            );
            return bestRef[0];
        }

        if (c120 == 1) {
            temp100[0] = m100;
            temp80[0] = m80;
            evaluerConfigurationCouloirsComplete(
                    profil, temp100, temp80, rotation100Force,
                    yBase, W, L,
                    r100Long, r100Larg, motifMixte, rMixte, r80Long,
                    palettesChargeesTranches, surfaceChargeeTranches,
                    p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                    bestRef
            );
            return bestRef[0];
        }

        if (c120 == 2) {
            for (int a100 = 0; a100 <= m100; a100++) {
                temp100[0] = a100;
                temp100[1] = m100 - a100;
                for (int a80 = 0; a80 <= m80; a80++) {
                    temp80[0] = a80;
                    temp80[1] = m80 - a80;
                    evaluerConfigurationCouloirsComplete(
                            profil, temp100, temp80, rotation100Force,
                            yBase, W, L,
                            r100Long, r100Larg, motifMixte, rMixte, r80Long,
                            palettesChargeesTranches, surfaceChargeeTranches,
                            p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                            bestRef
                    );
                }
            }
            return bestRef[0];
        }

        repartirC120Recursif(
                0, c120, m100, m80, temp100, temp80,
                profil, rotation100Force, yBase, W, L,
                r100Long, r100Larg, motifMixte, rMixte, r80Long,
                palettesChargeesTranches, surfaceChargeeTranches,
                p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                bestRef
        );
        return bestRef[0];
    }

    private void repartirC120Recursif(
            int idx, int c120, int rest100, int rest80,
            int[] temp100, int[] temp80,
            ProfilCouloirs3 profil, int rotation100Force, int yBase, int W, int L,
            int r100Long, int r100Larg, MotifTranche3 motifMixte, int rMixte, int r80Long,
            int palettesChargeesTranches, int surfaceChargeeTranches,
            int p80BonSensTranches, int surfacePerdueTranches, int nbRangsComplets,
            EvalSolution3[] bestRef
    ) {
        int colsRestantes = c120 - idx;
        if (colsRestantes == 1) {
            temp100[idx] = rest100;
            temp80[idx] = rest80;
            evaluerConfigurationCouloirsComplete(
                    profil, temp100, temp80, rotation100Force,
                    yBase, W, L,
                    r100Long, r100Larg, motifMixte, rMixte, r80Long,
                    palettesChargeesTranches, surfaceChargeeTranches,
                    p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                    bestRef
            );
            return;
        }

        int moy100 = rest100 / colsRestantes;
        int min100 = Math.max(0, moy100 - 2);
        int max100 = Math.min(rest100, moy100 + 2);

        int moy80 = rest80 / colsRestantes;
        int min80 = Math.max(0, moy80 - 3);
        int max80 = Math.min(rest80, moy80 + 3);

        for (int p100 = min100; p100 <= max100; p100++) {
            temp100[idx] = p100;
            for (int p80 = min80; p80 <= max80; p80++) {
                temp80[idx] = p80;
                repartirC120Recursif(
                        idx + 1, c120, rest100 - p100, rest80 - p80,
                        temp100, temp80,
                        profil, rotation100Force, yBase, W, L,
                        r100Long, r100Larg, motifMixte, rMixte, r80Long,
                        palettesChargeesTranches, surfaceChargeeTranches,
                        p80BonSensTranches, surfacePerdueTranches, nbRangsComplets,
                        bestRef
                );
            }
        }
    }

    private void evaluerConfigurationCouloirsComplete(
            ProfilCouloirs3 profil, int[] temp100, int[] temp80, int rotation100Force,
            int yBase, int W, int L,
            int r100Long, int r100Larg, MotifTranche3 motifMixte, int rMixte, int r80Long,
            int palettesChargeesTranches, int surfaceChargeeTranches,
            int p80BonSensTranches, int surfacePerdueTranches, int nbRangsComplets,
            EvalSolution3[] bestRef
    ) {
        int c120 = profil.c120;
        int c100 = profil.c100;
        int c80 = profil.c80;
        int K = c120 + c100 + c80;

        boolean vuCouloirVide = false;
        for (int k = 0; k < K; k++) {
            if (temp100[k] + temp80[k] == 0) {
                vuCouloirVide = true;
            } else if (vuCouloirVide) {
                return;
            }
        }

        int nbChargees = palettesChargeesTranches;
        int surfaceChargee = surfaceChargeeTranches;
        int nbP80BonSens = p80BonSensTranches;
        int surfacePerdueInternes = 0;
        int maxY = yBase;
        int minY = Integer.MAX_VALUE;

        int[] rot100ParCol = new int[K];
        int rotRestant = Math.max(0, rotation100Force);

        int[] yFinCol = new int[K];
        int sommeLargeurActive = 0;
        int totalPalettesCouloirs = 0;

        for (int k = 0; k < K; k++) {
            int largCol = (k < c120) ? 120 : ((k < c120 + c100) ? 100 : 80);
            int n100 = temp100[k];
            int n80 = temp80[k];

            totalPalettesCouloirs += (n100 + n80);
            if (n100 + n80 > 0) {
                sommeLargeurActive += largCol;
            }

            int n100Rot = 0;
            if (largCol == 120 && rotRestant > 0 && n100 > 0) {
                n100Rot = Math.min(n100, rotRestant);
                rotRestant -= n100Rot;
            } else if (largCol < 120) {
                n100Rot = n100;
            }
            rot100ParCol[k] = n100Rot;
            int n100Norm = n100 - n100Rot;

            int dy100Norm = 100;
            int dy100Rot = 120;
            int dy80 = (largCol == 120) ? 80 : 120;

            if (largCol == 120 && n100Rot > 0 && !autorisationForcerRotation100) {
                surfacePerdueInternes += n100Rot * (120 - 100) * 120;
            }
            if (largCol == 100 && n80 > 0) {
                surfacePerdueInternes += n80 * (100 - 80) * 120;
            }

            int hCol = n100Norm * dy100Norm + n100Rot * dy100Rot + n80 * dy80;
            int yFin = yBase + hCol;
            yFinCol[k] = yFin;

            if (yFin > maxY) {
                maxY = yFin;
            }
            if (yFin < minY) {
                minY = yFin;
            }

            int dispoY = Math.max(0, L - yBase);
            if (hCol <= dispoY) {
                if (largCol >= 100) {
                    nbChargees += (n100 + n80);
                    surfaceChargee += n100 * (100 * 120) + n80 * (80 * 120);
                } else {
                    nbChargees += n80;
                    surfaceChargee += n80 * (80 * 120);
                }
                if (largCol < 120) {
                    nbP80BonSens += n80;
                }
            } else if (dispoY > 0) {
                int bestInColCount = 0;
                int bestInColSurf = 0;
                int bestInCol80 = 0;
                int maxI100Norm = (largCol >= 100) ? n100Norm : 0;
                int maxI100Rot = (largCol >= 100) ? n100Rot : 0;

                for (int iNorm = 0; iNorm <= maxI100Norm; iNorm++) {
                    int y1 = iNorm * dy100Norm;
                    if (y1 > dispoY) break;
                    for (int iRot = 0; iRot <= maxI100Rot; iRot++) {
                        int y2 = y1 + iRot * dy100Rot;
                        if (y2 > dispoY) break;
                        int i80 = Math.min(n80, (dispoY - y2) / dy80);
                        int count = iNorm + iRot + i80;
                        int surf = (iNorm + iRot) * (100 * 120) + i80 * (80 * 120);
                        if (count > bestInColCount || (count == bestInColCount && surf > bestInColSurf)) {
                            bestInColCount = count;
                            bestInColSurf = surf;
                            bestInCol80 = i80;
                        }
                    }
                }
                nbChargees += bestInColCount;
                surfaceChargee += bestInColSurf;
                if (largCol < 120) {
                    nbP80BonSens += bestInCol80;
                }
            }
        }

        if (minY == Integer.MAX_VALUE) {
            minY = yBase;
        }
        int ecart = maxY - minY;
        int surfacePerdueDroiteCouloirs = 0;
        if (totalPalettesCouloirs > 1) {
            surfacePerdueDroiteCouloirs = Math.max(0, W - sommeLargeurActive) * Math.max(0, maxY - yBase);
        }

        int surfacePerdueCreux = 0;
        int surfacePerdueBout = 0;
        for (int k = 0; k < K; k++) {
            int largCol = (k < c120) ? 120 : ((k < c120 + c100) ? 100 : 80);
            int n100 = temp100[k];
            int n80 = temp80[k];
            if (n100 + n80 > 0) {
                int creuxCol = Math.max(0, (maxY - yFinCol[k]) - 160);
                surfacePerdueCreux += creuxCol * largCol;
                if (yFinCol[k] <= L) {
                    int pasMinCol = (largCol == 120) ? 80 : 120;
                    int resteCol = L - yFinCol[k];
                    if (resteCol > 0 && resteCol < pasMinCol) {
                        surfacePerdueBout += resteCol * largCol;
                    }
                }
            }
        }

        int surfacePerdueTotaleFond = surfacePerdueTranches
                + surfacePerdueInternes
                + surfacePerdueDroiteCouloirs
                + surfacePerdueCreux
                + surfacePerdueBout;

        int rangA = yFinCol[0];
        int rangB = (K >= 2) ? yFinCol[1] : yBase;
        if (K > 2) {
            rangA = yBase;
            rangB = yBase;
            for (int k = 0; k < K; k++) {
                if (k < K / 2) {
                    rangA = Math.max(rangA, yFinCol[k]);
                } else {
                    rangB = Math.max(rangB, yFinCol[k]);
                }
            }
        }

        EvalSolution3 cand = new EvalSolution3();
        cand.nbPalettesChargees = nbChargees;
        cand.surfaceChargee = surfaceChargee;
        cand.surfacePerdueFond = surfacePerdueTotaleFond;
        cand.nbP80BonSens = nbP80BonSens;
        cand.maxY = maxY;
        cand.ecartCouloirs = ecart;
        cand.nbRangsComplets = nbRangsComplets;
        cand.rangA = rangA;
        cand.rangB = rangB;

        if (cand.estMeilleureQue(bestRef[0])) {
            cand.r100Long = r100Long;
            cand.r100Larg = r100Larg;
            cand.motifMixte = motifMixte;
            cand.rMixte = rMixte;
            cand.r80Long = r80Long;
            cand.profilCouloirs = profil;
            cand.col100Count = new int[K];
            cand.col100RotCount = new int[K];
            cand.col80Count = new int[K];
            cand.colLargeur = new int[K];
            cand.colXStart = new int[K];

            int xCur = 0;
            for (int k = 0; k < K; k++) {
                int largCol = (k < c120) ? 120 : ((k < c120 + c100) ? 100 : 80);
                cand.col100Count[k] = temp100[k];
                cand.col100RotCount[k] = rot100ParCol[k];
                cand.col80Count[k] = temp80[k];
                cand.colLargeur[k] = largCol;
                cand.colXStart[k] = xCur;
                xCur += largCol;
            }
            bestRef[0] = cand;
        }
    }

    private void construirePalettesPosees3(
            EvalSolution3 sol,
            ArrayList<Palette> pool100,
            ArrayList<Palette> pool80,
            ArrayList<Palette> sortie,
            int yDepart, int W, int L,
            int cap100Long, int cap100Larg, int cap80Long
    ) {
        int idx100 = 0;
        int idx80 = 0;
        int yCur = yDepart;

        for (int r = 0; r < sol.r100Long; r++) {
            for (int c = 0; c < cap100Long && idx100 < pool100.size(); c++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(c * 100);
                p.setPositionY(yCur);
                p.setOrientation(0);
                sortie.add(p);
            }
            yCur += 120;
        }

        for (int r = 0; r < sol.r100Larg; r++) {
            for (int c = 0; c < cap100Larg && idx100 < pool100.size(); c++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(c * 120);
                p.setPositionY(yCur);
                p.setOrientation(1);
                sortie.add(p);
            }
            yCur += 100;
        }

        if (sol.motifMixte != null && sol.rMixte > 0) {
            for (int r = 0; r < sol.rMixte; r++) {
                int xCur = 0;
                for (int c = 0; c < sol.motifMixte.nb100 && idx100 < pool100.size(); c++) {
                    Palette p = pool100.get(idx100++);
                    p.setPositionX(xCur);
                    p.setPositionY(yCur);
                    p.setOrientation(0);
                    sortie.add(p);
                    xCur += 100;
                }
                for (int c = 0; c < sol.motifMixte.nb80 && idx80 < pool80.size(); c++) {
                    Palette p = pool80.get(idx80++);
                    p.setPositionX(xCur);
                    p.setPositionY(yCur);
                    p.setOrientation(0);
                    sortie.add(p);
                    xCur += 80;
                }
                yCur += sol.motifMixte.deltaY;
            }
        }

        for (int r = 0; r < sol.r80Long; r++) {
            for (int c = 0; c < cap80Long && idx80 < pool80.size(); c++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(c * 80);
                p.setPositionY(yCur);
                p.setOrientation(0);
                sortie.add(p);
            }
            yCur += 120;
        }

        int K = (sol.colLargeur != null) ? sol.colLargeur.length : 0;
        int yBaseCouloirs = yCur;

        for (int k = 0; k < K; k++) {
            int xCol = sol.colXStart[k];
            int largCol = sol.colLargeur[k];
            int n100 = sol.col100Count[k];
            int n100Rot = sol.col100RotCount[k];
            int n100Norm = n100 - n100Rot;
            int n80 = sol.col80Count[k];

            int dy100Norm = 100;
            int dy100Rot = 120;
            int dy80 = (largCol == 120) ? 80 : 120;
            int orient80 = (largCol == 120) ? 1 : 0;

            int hCol = n100Norm * dy100Norm + n100Rot * dy100Rot + n80 * dy80;
            int dispoY = Math.max(0, L - yBaseCouloirs);

            int inNorm = n100Norm;
            int inRot = n100Rot;
            int in80 = n80;

            if (hCol > dispoY && dispoY > 0) {
                int bestCount = -1;
                int bestSurf = -1;
                int maxINorm = (largCol >= 100) ? n100Norm : 0;
                int maxIRot = (largCol >= 100) ? n100Rot : 0;

                for (int iN = 0; iN <= maxINorm; iN++) {
                    int y1 = iN * dy100Norm;
                    if (y1 > dispoY) break;
                    for (int iR = 0; iR <= maxIRot; iR++) {
                        int y2 = y1 + iR * dy100Rot;
                        if (y2 > dispoY) break;
                        int i8 = Math.min(n80, (dispoY - y2) / dy80);
                        int count = iN + iR + i8;
                        int surf = (iN + iR) * (100 * 120) + i8 * (80 * 120);
                        if (count > bestCount || (count == bestCount && surf > bestSurf)) {
                            bestCount = count;
                            bestSurf = surf;
                            inNorm = iN;
                            inRot = iR;
                            in80 = i8;
                        }
                    }
                }
            }

            int outNorm = n100Norm - inNorm;
            int outRot = n100Rot - inRot;
            int out80 = n80 - in80;

            int yCol = yBaseCouloirs;

            for (int i = 0; i < inNorm && idx100 < pool100.size(); i++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(1);
                sortie.add(p);
                yCol += dy100Norm;
            }
            for (int i = 0; i < inRot && idx100 < pool100.size(); i++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(0);
                sortie.add(p);
                yCol += dy100Rot;
            }
            for (int i = 0; i < in80 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(orient80);
                sortie.add(p);
                yCol += dy80;
            }

            for (int i = 0; i < outNorm && idx100 < pool100.size(); i++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(1);
                sortie.add(p);
                yCol += dy100Norm;
            }
            for (int i = 0; i < outRot && idx100 < pool100.size(); i++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(0);
                sortie.add(p);
                yCol += dy100Rot;
            }
            for (int i = 0; i < out80 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(xCol);
                p.setPositionY(yCol);
                p.setOrientation(orient80);
                sortie.add(p);
                yCol += dy80;
            }
        }
    }

    private void afficherResultatsCalculer3(
            ArrayList<Palette> listeDePalettesPosees,
            int p80Total, int p100Total,
            int rangA, int rangB, int W, int L
    ) {
        int p80EnRemorque = 0;
        int p100EnRemorque = 0;
        int maxYbGlobal = L;

        for (Palette p : listeDePalettesPosees) {
            if (p.getPositionYb() > maxYbGlobal) {
                maxYbGlobal = p.getPositionYb();
            }
            if (p.getPositionX() >= 0 && p.getPositionXb() <= W && p.getPositionY() >= 0 && p.getPositionYb() <= L) {
                if (p.getLargeur() == 80) {
                    p80EnRemorque++;
                } else if (p.getLargeur() == 100) {
                    p100EnRemorque++;
                }
            }
        }

        int p80AQuai = Math.max(0, p80Total - p80EnRemorque);
        int p100AQuai = Math.max(0, p100Total - p100EnRemorque);

        double a = rangA;
        double b = rangB;
        double resteA = L - a;
        double resteB = L - b;

        double surfaceRemorqueM2 = (L * (double) W) / 10000.0;
        double surfaceUtiliseeM2 = (p80EnRemorque * (80.0 * 120.0) + p100EnRemorque * (100.0 * 120.0)) / 10000.0;
        double surfaceInutiliseeM2 = Math.max(0.0, Math.round((surfaceRemorqueM2 - surfaceUtiliseeM2) * 100.0) / 100.0);
        surfaceRemorqueM2 = Math.round(surfaceRemorqueM2 * 100.0) / 100.0;

        if (resultat != null) {
            resultat.setText(
                    "Rang A : " + (a / 100.0) + "    Reste : " + (resteA / 100.0) + "\n" +
                    "Rang B : " + (b / 100.0) + "    Reste : " + (resteB / 100.0) + "\n" +
                    "Surface inutilisée : " + surfaceInutiliseeM2 + "/" + surfaceRemorqueM2 + "\n" +
                    p80AQuai + " P80 à quai" + "\n" +
                    p100AQuai + " P100 à quai"
            );
        }

        drawView.oldMode = false;
        drawView.listeDePalettesPosees = listeDePalettesPosees;
        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;

        int sizeX = (int) (W / agrandissement) + 20;
        int sizeY = Math.max((int) (L / agrandissement) + 145, (int) (maxYbGlobal / agrandissement) + 30);
        drawView.setLayoutParams(new LinearLayout.LayoutParams(sizeX, sizeY));

        if (drawPosRemorque != null) {
            drawPosRemorque.removeView(drawView);
            drawPosRemorque.addView(drawView);
        }
        drawView.invalidate();
    }


    // =============================================================================================
    // CALCULER 4 : Algorithme par blocs ordonnés (P100 -> Transition P100/P80 -> P80 -> Rolls en dernier)
    // Respecte strictement l'ordre de chargement cariste, l'orientation optimale des P80 (80*120)
    // et des P100 (120*100), ainsi que les boutons d'autorisation/forçage de rotation des Rolls (57*135).
    // =============================================================================================

    private static class ProfilCouloirs4 {
        int c120;
        int c100;
        int c80;

        ProfilCouloirs4(int c120, int c100, int c80) {
            this.c120 = c120;
            this.c100 = c100;
            this.c80 = c80;
        }

        int totalCouloirs() {
            return c120 + c100 + c80;
        }
    }

    private static class EvalSolution4 {
        // 1. Rangs complets P100 au fond
        int r100Long;
        int r100Larg;

        // 2. Zone de transition P100 / P80 (immédiatement après les rangs P100 si rem100 > 0)
        ProfilCouloirs4 profilTrans100;
        int[] transCol100;
        int[] transCol100Rot;
        int[] transCol80;
        int[] transColLargeur;
        int[] transColXStart;

        // 3. Rangs complets P80 au milieu
        int r80Long;
        int r80Larg;

        // 4. Zone de transition P80 / Rolls + Zone finale Rolls tout au bout
        int modeP80Tail; // 0=aucun, 1=1xC80, 2=2xC80, 3=1xC120, 4=C120+C80
        int tail80Col0;
        int tail80Col1;
        int rollsDroiteMode; // 0=aucun, 1=en long (57*135), 2=en large (135*57)
        int rollsDroiteCount;
        int rollsSousP80Mode; // 0=aucun, 1=en long (57*135), 2=en large (135*57)
        int rollsSousP80Count;

        // 5. Rangs / reliquat de Rolls sur toute la largeur W (tout à la fin)
        int rRollsLong;
        int rRollsLarg;
        int remRollsFinalMode; // 1=en long (57*135), 2=en large (135*57)
        int remRollsFinalCount;

        // Métriques d'évaluation
        int nbP100EtP80Chargees = -1;
        int nbPalettesChargees = -1;
        int surfaceChargee = -1;
        int nbP100EnRangsComplets = -1;
        int surfacePerdueFond = Integer.MAX_VALUE;
        int nbP80BonSens = -1;
        int nbRollsSensVoulu = -1;
        int maxYCharge = Integer.MAX_VALUE;
        int ecartCouloirs = Integer.MAX_VALUE;
        int nbRangsComplets = -1;
        int rangA = 0;
        int rangB = 0;

        boolean estMeilleureQue(EvalSolution4 autre) {
            if (autre == null) return true;
            // 1. Priorité absolue aux palettes (P100 + P80) : ne jamais laisser une P80 ou P100 à quai pour mettre des Rolls à la place
            if (this.nbP100EtP80Chargees != autre.nbP100EtP80Chargees) {
                return this.nbP100EtP80Chargees > autre.nbP100EtP80Chargees;
            }
            // 2. A nombre de palettes (P100 + P80) égal, maximiser le nombre total d'éléments (donc le nombre de Rolls chargés en dernier)
            if (this.nbPalettesChargees != autre.nbPalettesChargees) {
                return this.nbPalettesChargees > autre.nbPalettesChargees;
            }
            // 3. A nombre d'éléments égal, maximiser la surface utile chargée
            if (this.surfaceChargee != autre.surfaceChargee) {
                return this.surfaceChargee > autre.surfaceChargee;
            }
            // 4. Garder les P100 groupées en rangs complets au fond (ne jamais casser un rang P100 sans gain de chargement)
            if (this.nbP100EnRangsComplets != autre.nbP100EnRangsComplets) {
                return this.nbP100EnRangsComplets > autre.nbP100EnRangsComplets;
            }
            // 5. Minimiser la surface perdue interne (trous internes dans les blocs P100/P80 ou au-dessus des Rolls)
            if (this.surfacePerdueFond != autre.surfacePerdueFond) {
                return this.surfacePerdueFond < autre.surfacePerdueFond;
            }
            // 6. Maximiser le nombre de P80 dans le sens pratique pour le cariste (en long 80*120)
            if (this.nbP80BonSens != autre.nbP80BonSens) {
                return this.nbP80BonSens > autre.nbP80BonSens;
            }
            // 7. Maximiser le nombre de Rolls dans le sens souhaité
            if (this.nbRollsSensVoulu != autre.nbRollsSensVoulu) {
                return this.nbRollsSensVoulu > autre.nbRollsSensVoulu;
            }
            // 8. Minimiser la longueur occupée (maxYCharge)
            if (this.maxYCharge != autre.maxYCharge) {
                return this.maxYCharge < autre.maxYCharge;
            }
            // 9. Minimiser le décrochement entre gauche et droite
            if (this.ecartCouloirs != autre.ecartCouloirs) {
                return this.ecartCouloirs < autre.ecartCouloirs;
            }
            // 10. Privilégier le plus grand nombre de rangs complets
            return this.nbRangsComplets > autre.nbRangsComplets;
        }
    }

    private void calculer4() {
        drawPosRemorque = findViewById(R.id.drawPosRemorque);
        resultat = findViewById(R.id.resultat);

        listeDesListesDeRangComplets.clear();
        listeP100RangComplet2.clear();
        listeP100RangComplet3.clear();
        listeP80RangComplet2.clear();
        listeP80RangComplet3.clear();
        listeRollsRangComplet4.clear();
        listeRollsRangComplet2.clear();
        listeP80.clear();
        listeP100.clear();
        listeRolls.clear();

        int p80 = 0;
        int p100 = 0;
        int rolls = 0;
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
        try {
            if (nombreRolls != null) {
                rolls = Integer.parseInt(nombreRolls.getText().toString());
            }
        } catch (Exception e) {
            rolls = 0;
        }
        p80 = Math.max(0, p80);
        p100 = Math.max(0, p100);
        rolls = Math.max(0, rolls);

        int casser80 = 0;
        if (autorisationForcerCasserUnRang80) {
            try {
                casser80 = Math.max(0, Integer.parseInt(casser80120NombreTextInput.getText().toString()));
            } catch (Exception e) {
                casser80 = 0;
            }
        }

        int rotation100Force = 0;
        if (autorisationForcerRotation100) {
            try {
                rotation100Force = Math.max(0, Integer.parseInt(focerRotation100120NombreTextInput.getText().toString()));
            } catch (Exception e) {
                rotation100Force = 0;
            }
        }

        for (int i = 0; i < p80; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.orientation = 0;
            p.longueur = 120;
            p.largeur = 80;
            p.setOrientationFavorite(0);
            listeP80.add(p);
        }
        for (int i = 0; i < p100; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.orientation = 1;
            p.longueur = 120;
            p.largeur = 100;
            p.setOrientationFavorite(1);
            listeP100.add(p);
        }
        for (int i = 0; i < rolls; i++) {
            Palette p = new Palette();
            p.id = i + 1;
            p.orientation = 0;
            p.longueur = 135;
            p.largeur = 57;
            p.setOrientationFavorite(0);
            listeRolls.add(p);
        }

        int W = (remorque != null && remorque.largeur > 0) ? remorque.largeur : 240;
        int L = (remorque != null && remorque.longueur > 0) ? remorque.longueur : 1320;
        int yDepart = decalageBarresDeSeparation();

        ArrayList<Palette> listeDePalettesPosees = new ArrayList<>();

        if (W < 57 || (p80 == 0 && p100 == 0 && rolls == 0)) {
            afficherResultatsCalculer4(listeDePalettesPosees, p80, p100, rolls, yDepart, yDepart, W, L);
            return;
        }

        int cap100Long = W / 100;
        int cap100Larg = W / 120;
        int cap80Long = W / 80;
        int cap80Larg = W / 120;
        int capRollsLong = W / 57;
        int capRollsLarg = W / 135;

        boolean rotRollsPermise = autorisationRotationRollsSiPossibleDeFaireUnRang || autorisationForcerRotationRolls;

        ArrayList<ProfilCouloirs4> profilsTrans100 = genererProfilsCouloirs4(W);
        EvalSolution4[] bestRef = new EvalSolution4[]{null};

        int maxR100Long = (cap100Long > 0) ? (p100 / cap100Long) : 0;
        boolean testerR100Long = (cap100Long > 0 && (W % 100 == 0 || (W % 100) < (W % 120)));

        for (int r100Long = 0; r100Long <= maxR100Long; r100Long++) {
            if (!testerR100Long && r100Long > 0) {
                continue;
            }
            int rem100AfterLong = p100 - r100Long * cap100Long;
            int maxR100Larg = (cap100Larg > 0) ? (rem100AfterLong / cap100Larg) : 0;
            // On ne casse au maximum qu'un seul rang complet de P100 (sauf si rotation100Force l'impose ou si W % 120 != 0)
            int nbCasser100Max = (W % 120 == 0) ? Math.max(1, (rotation100Force + cap100Larg - 1) / Math.max(1, cap100Larg)) : maxR100Larg;
            int minR100Larg = Math.max(0, maxR100Larg - nbCasser100Max);

            for (int r100Larg = minR100Larg; r100Larg <= maxR100Larg; r100Larg++) {
                int rem100 = rem100AfterLong - r100Larg * cap100Larg;

                if (rem100 == 0) {
                    // Pas besoin de zone de transition P100/P80 : on passe directement au bloc P80
                    evaluerBlocP80EtRolls4(
                            W, L, yDepart,
                            r100Long, r100Larg,
                            null, null, null, null,
                            p80, rolls, casser80, rotation100Force, rotRollsPermise,
                            cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                            bestRef
                    );
                } else {
                    // Il reste rem100 palettes P100 : on les place immédiatement après les rangs P100
                    // dans une zone de transition P100 / P80 avec le juste nombre de P80 à droite
                    for (ProfilCouloirs4 profil : profilsTrans100) {
                        int nbCol100 = profil.c120 + profil.c100;
                        if (nbCol100 == 0) continue;
                        int K = profil.totalCouloirs();
                        int[] temp100 = new int[K];
                        int[] temp80 = new int[K];
                        repartir100Transition4(
                                0, nbCol100, rem100, K, temp100, temp80,
                                W, L, yDepart, r100Long, r100Larg, profil,
                                p80, rolls, casser80, rotation100Force, rotRollsPermise,
                                cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                                bestRef
                        );
                    }
                }
            }
        }

        EvalSolution4 best = bestRef[0];
        if (best != null) {
            construirePalettesPosees4(
                    best, listeP100, listeP80, listeRolls, listeDePalettesPosees,
                    yDepart, W, L,
                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg
            );
            afficherResultatsCalculer4(listeDePalettesPosees, p80, p100, rolls, best.rangA, best.rangB, W, L);
        } else {
            afficherResultatsCalculer4(listeDePalettesPosees, p80, p100, rolls, yDepart, yDepart, W, L);
        }
    }

    private ArrayList<ProfilCouloirs4> genererProfilsCouloirs4(int W) {
        ArrayList<ProfilCouloirs4> liste = new ArrayList<>();
        for (int c120 = 0; c120 <= W / 120; c120++) {
            int rem120 = W - c120 * 120;
            for (int c100 = 0; c100 <= rem120 / 100; c100++) {
                int rem100 = rem120 - c100 * 100;
                int c80 = rem100 / 80;
                int rem80 = rem100 - c80 * 80;
                if (rem80 < 80 && (c120 + c100 + c80) > 0) {
                    liste.add(new ProfilCouloirs4(c120, c100, c80));
                }
            }
        }
        if (liste.isEmpty()) {
            liste.add(new ProfilCouloirs4(0, 0, 1));
        }
        return liste;
    }

    private void repartir100Transition4(
            int idxCol100, int nbCol100, int rest100, int K,
            int[] temp100, int[] temp80,
            int W, int L, int yDepart, int r100Long, int r100Larg, ProfilCouloirs4 profil,
            int p80Total, int rollsTotal, int casser80, int rotation100Force, boolean rotRollsPermise,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg,
            EvalSolution4[] bestRef
    ) {
        if (idxCol100 == nbCol100 - 1) {
            temp100[idxCol100] = rest100;
            int somme100Trans = 0;
            for (int k = 0; k < nbCol100; k++) {
                somme100Trans += temp100[k];
            }
            // Déterminer combien de P80 mettre dans la zone de transition P100/P80 :
            // En W=240, on met uniquement les P80 nécessaires pour accompagner les P100 sur la même hauteur (ex: 1 à 4 P80).
            // En W=300 (où W % 80 != 0), les couloirs 120+100+80 peuvent accueillir toutes les P80 ou une partie.
            int max80ForTrans = (W % 80 == 0) ? Math.min(p80Total, Math.max(3, somme100Trans * 2 + 1)) : p80Total;
            int min80ForTrans = (W % 80 == 0) ? 0 : Math.max(0, p80Total - cap80Long * 2);
            for (int n80Trans = min80ForTrans; n80Trans <= max80ForTrans; n80Trans++) {
                repartir80Transition4(
                        0, K, n80Trans, temp100, temp80,
                        W, L, yDepart, r100Long, r100Larg, profil,
                        p80Total - n80Trans, rollsTotal, casser80, rotation100Force, rotRollsPermise,
                        cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                        bestRef
                );
            }
            return;
        }

        for (int v = rest100; v >= 0; v--) {
            temp100[idxCol100] = v;
            repartir100Transition4(
                    idxCol100 + 1, nbCol100, rest100 - v, K, temp100, temp80,
                    W, L, yDepart, r100Long, r100Larg, profil,
                    p80Total, rollsTotal, casser80, rotation100Force, rotRollsPermise,
                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                    bestRef
            );
        }
    }

    private void repartir80Transition4(
            int idxCol, int K, int rest80, int[] temp100, int[] temp80,
            int W, int L, int yDepart, int r100Long, int r100Larg, ProfilCouloirs4 profil,
            int rem80AfterTrans, int rollsTotal, int casser80, int rotation100Force, boolean rotRollsPermise,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg,
            EvalSolution4[] bestRef
    ) {
        if (idxCol == K - 1) {
            temp80[idxCol] = rest80;
            int[] rot100 = new int[K];
            int rotRest = Math.max(0, rotation100Force);
            for (int k = 0; k < K; k++) {
                int largCol = (k < profil.c120) ? 120 : ((k < profil.c120 + profil.c100) ? 100 : 80);
                if (largCol == 120 && rotRest > 0 && temp100[k] > 0) {
                    rot100[k] = Math.min(temp100[k], rotRest);
                    rotRest -= rot100[k];
                } else if (largCol < 120) {
                    rot100[k] = temp100[k];
                }
            }
            evaluerBlocP80EtRolls4(
                    W, L, yDepart,
                    r100Long, r100Larg,
                    profil, temp100, rot100, temp80,
                    rem80AfterTrans, rollsTotal, casser80, rotation100Force, rotRollsPermise,
                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                    bestRef
            );
            return;
        }

        boolean estCol100 = (idxCol >= profil.c120 && idxCol < profil.c120 + profil.c100);
        if (estCol100 && profil.c80 > 0 && (W % 80 == 0 || W % 120 == 0)) {
            temp80[idxCol] = 0;
            repartir80Transition4(
                    idxCol + 1, K, rest80, temp100, temp80,
                    W, L, yDepart, r100Long, r100Larg, profil,
                    rem80AfterTrans, rollsTotal, casser80, rotation100Force, rotRollsPermise,
                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                    bestRef
            );
            return;
        }

        for (int v = rest80; v >= 0; v--) {
            temp80[idxCol] = v;
            repartir80Transition4(
                    idxCol + 1, K, rest80 - v, temp100, temp80,
                    W, L, yDepart, r100Long, r100Larg, profil,
                    rem80AfterTrans, rollsTotal, casser80, rotation100Force, rotRollsPermise,
                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                    bestRef
            );
        }
    }

    private void evaluerBlocP80EtRolls4(
            int W, int L, int yDepart,
            int r100Long, int r100Larg,
            ProfilCouloirs4 profilTrans100, int[] trans100, int[] trans100Rot, int[] trans80,
            int rem80Dispo, int rollsTotal, int casser80, int rotation100Force, boolean rotRollsPermise,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg,
            EvalSolution4[] bestRef
    ) {
        // Calcul de la hauteur et des métriques des Étapes 1 et 2 (P100 + Transition P100/P80)
        int yCursor = yDepart;
        int nbChargeesBase = 0;
        int surfaceChargeeBase = 0;
        int nbP80BonSensBase = 0;
        int surfacePerdueBase = 0;
        int nbP100EnRangsComplets = (W % 120 == 0) ? (r100Long * cap100Long + r100Larg * cap100Larg) : 0;

        for (int i = 0; i < r100Long; i++) {
            if (yCursor + 120 <= L) {
                nbChargeesBase += cap100Long;
                surfaceChargeeBase += cap100Long * (100 * 120);
            }
            surfacePerdueBase += (W - cap100Long * 100) * 120;
            yCursor += 120;
        }
        for (int i = 0; i < r100Larg; i++) {
            if (yCursor + 100 <= L) {
                nbChargeesBase += cap100Larg;
                surfaceChargeeBase += cap100Larg * (100 * 120);
            }
            surfacePerdueBase += (W - cap100Larg * 120) * 100;
            yCursor += 100;
        }

        int yApresRangs100 = yCursor;
        int maxYTrans100 = yApresRangs100;
        int minYTrans100 = yApresRangs100;

        if (profilTrans100 != null && trans100 != null) {
            int K = profilTrans100.totalCouloirs();
            boolean vuVide = false;
            for (int k = 0; k < K; k++) {
                if (trans100[k] + trans80[k] == 0) {
                    vuVide = true;
                } else if (vuVide) {
                    return;
                }
            }
            minYTrans100 = Integer.MAX_VALUE;
            int sommeLargActive = 0;
            int totalTrans = 0;
            int[] yFinTrans = new int[K];

            for (int k = 0; k < K; k++) {
                int largCol = (k < profilTrans100.c120) ? 120 : ((k < profilTrans100.c120 + profilTrans100.c100) ? 100 : 80);
                int n100 = trans100[k];
                int n100Rot = trans100Rot[k];
                int n100Norm = n100 - n100Rot;
                int n80 = trans80[k];

                totalTrans += (n100 + n80);
                if (n100 + n80 > 0) {
                    sommeLargActive += largCol;
                }

                int dy100Norm = 100;
                int dy100Rot = 120;
                int dy80 = (largCol == 120) ? 80 : 120;

                if (largCol == 120 && n100Rot > 0 && !autorisationForcerRotation100) {
                    surfacePerdueBase += n100Rot * (120 - 100) * 120;
                }
                if (largCol == 100 && n80 > 0 && (W % 80 == 0 || W % 120 == 0)) {
                    surfacePerdueBase += n80 * (100 - 80) * 120;
                }

                int hCol = n100Norm * dy100Norm + n100Rot * dy100Rot + n80 * dy80;
                int yFin = yApresRangs100 + hCol;
                yFinTrans[k] = yFin;
                if (yFin > maxYTrans100) maxYTrans100 = yFin;
                if (yFin < minYTrans100) minYTrans100 = yFin;

                int dispoY = Math.max(0, L - yApresRangs100);
                int yLocal = 0;
                int in100Norm = Math.min(n100Norm, (dispoY - yLocal) / dy100Norm);
                yLocal += in100Norm * dy100Norm;
                int in100Rot = Math.min(n100Rot, (dispoY - yLocal) / dy100Rot);
                yLocal += in100Rot * dy100Rot;
                int in80 = Math.min(n80, (dispoY - yLocal) / dy80);

                nbChargeesBase += (in100Norm + in100Rot + in80);
                surfaceChargeeBase += (in100Norm + in100Rot) * (100 * 120) + in80 * (80 * 120);
                if (largCol < 120) {
                    nbP80BonSensBase += in80;
                }
            }
            if (minYTrans100 == Integer.MAX_VALUE) minYTrans100 = yApresRangs100;
            // Si d'autres éléments (P80 ou Rolls) sont posés en dessous de la zone de transition P100/P80,
            // toute largeur inutilisée (W - sommeLargActive) forme un trou interne enfermé au milieu de la remorque !
            if (totalTrans > 1 || rem80Dispo > 0 || rollsTotal > 0) {
                surfacePerdueBase += Math.max(0, W - sommeLargActive) * Math.max(0, maxYTrans100 - yApresRangs100);
            }
            for (int k = 0; k < K; k++) {
                int largCol = (k < profilTrans100.c120) ? 120 : ((k < profilTrans100.c120 + profilTrans100.c100) ? 100 : 80);
                if (trans100[k] + trans80[k] > 0) {
                    surfacePerdueBase += Math.max(0, maxYTrans100 - yFinTrans[k]) * largCol;
                }
            }
        }

        int yBaseP80 = maxYTrans100;

        // Étape 3 : Rangs complets de P80 (r80Long en 80*120, puis r80Larg en 120*80)
        int maxR80Possible = (cap80Long > 0) ? (rem80Dispo / cap80Long) : 0;
        int maxR80Autorise = Math.max(0, maxR80Possible - casser80);
        int minR80Long = Math.max(0, maxR80Autorise - 2);
        if (W % 80 != 0) {
            minR80Long = 0;
        }

        for (int r80Long = minR80Long; r80Long <= maxR80Autorise; r80Long++) {
            int rem80AfterLong = rem80Dispo - r80Long * cap80Long;
            // En W=240, 3 rangs de 2 P80 en large (240 cm) équivalent à 2 rangs de 3 P80 en long (240 cm),
            // donc r80Larg n'a besoin de varier qu'entre 0 et 3 (ou plus si casser80 > 0 ou autorisationRangBatard80).
            int maxR80LargPossible = (cap80Larg > 0) ? (rem80AfterLong / cap80Larg) : 0;
            int maxR80LargUtile = (W % 80 == 0 && casser80 == 0 && !autorisationRangBatard80)
                    ? Math.min(maxR80LargPossible, 3)
                    : maxR80LargPossible;

            for (int r80Larg = 0; r80Larg <= maxR80LargUtile; r80Larg++) {
                int rem80Tail = rem80AfterLong - r80Larg * cap80Larg;

                int yCur80 = yBaseP80;
                int nbChargeesAfter80 = nbChargeesBase;
                int surfaceChargeeAfter80 = surfaceChargeeBase;
                int nbP80BonSensAfter80 = nbP80BonSensBase;
                int surfacePerdueAfter80 = surfacePerdueBase;

                for (int i = 0; i < r80Long; i++) {
                    if (yCur80 + 120 <= L) {
                        nbChargeesAfter80 += cap80Long;
                        surfaceChargeeAfter80 += cap80Long * (80 * 120);
                        nbP80BonSensAfter80 += cap80Long;
                    }
                    surfacePerdueAfter80 += (W - cap80Long * 80) * 120;
                    yCur80 += 120;
                }
                for (int i = 0; i < r80Larg; i++) {
                    if (yCur80 + 80 <= L) {
                        nbChargeesAfter80 += cap80Larg;
                        surfaceChargeeAfter80 += cap80Larg * (80 * 120);
                    }
                    surfacePerdueAfter80 += (W - cap80Larg * 120) * 80;
                    yCur80 += 80;
                }

                // Étape 4 : Transition P80 / Rolls + Zone finale Rolls
                int maxM80Tail = Math.min(rem80Tail, 4);
                for (int m80 = 0; m80 <= maxM80Tail; m80++) {
                    if (m80 < rem80Tail && yCur80 + 80 <= L && rollsTotal == 0) {
                        // S'il n'y a pas de Rolls et qu'il reste de la place, on tente toujours de poser toutes les P80
                        continue;
                    }
                    evaluerTransitionP80EtRolls4(
                            W, L, yCur80,
                            r100Long, r100Larg, profilTrans100, trans100, trans100Rot, trans80,
                            r80Long, r80Larg,
                            m80, rollsTotal, rotRollsPermise,
                            nbChargeesAfter80, surfaceChargeeAfter80, nbP100EnRangsComplets,
                            nbP80BonSensAfter80, surfacePerdueAfter80,
                            cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                            minYTrans100, maxYTrans100,
                            bestRef
                    );
                }
            }
        }
    }

    private void evaluerTransitionP80EtRolls4(
            int W, int L, int yBaseTail,
            int r100Long, int r100Larg,
            ProfilCouloirs4 profilTrans100, int[] trans100, int[] trans100Rot, int[] trans80,
            int r80Long, int r80Larg,
            int m80, int rollsTotal, boolean rotRollsPermise,
            int nbChargeesAfter80, int surfaceChargeeAfter80, int nbP100EnRangsComplets,
            int nbP80BonSensAfter80, int surfacePerdueAfter80,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg,
            int minYTrans100, int maxYTrans100,
            EvalSolution4[] bestRef
    ) {
        // Modes de placement des m80 dernières P80 à gauche :
        // mode 0 : m80 == 0
        // mode 1 : 1 couloir 80 cm (m80 en 80*120)
        // mode 2 : 2 couloirs 80 cm (col0 et col1 en 80*120)
        // mode 3 : 1 couloir 120 cm (m80 en 120*80)
        // mode 4 : 1 couloir 120 cm (col0 en 120*80) + 1 couloir 80 cm (col1 en 80*120)
        int minMode = (m80 == 0) ? 0 : 1;
        int maxMode = (m80 == 0) ? 0 : 4;

        for (int mode = minMode; mode <= maxMode; mode++) {
            int wP80 = 0;
            int col0 = 0;
            int col1 = 0;
            int hCol0 = 0;
            int hCol1 = 0;

            if (mode == 0) {
                wP80 = 0;
            } else if (mode == 1) {
                if (W < 80) continue;
                wP80 = 80;
                col0 = m80;
                hCol0 = col0 * 120;
            } else if (mode == 2) {
                if (m80 < 2 || W < 160) continue;
                wP80 = 160;
                col0 = (m80 + 1) / 2;
                col1 = m80 / 2;
                hCol0 = col0 * 120;
                hCol1 = col1 * 120;
            } else if (mode == 3) {
                if (W < 120) continue;
                wP80 = 120;
                col0 = m80;
                hCol0 = col0 * 80;
            } else if (mode == 4) {
                if (m80 < 2 || W < 200) continue;
                wP80 = 200;
                col0 = m80 - 1;
                col1 = 1;
                hCol0 = col0 * 80;
                hCol1 = col1 * 120;
            }

            int hP80Max = Math.max(hCol0, hCol1);
            // Si une partie des m80 dépasse L, ce mode est inutile car un m80 plus petit est déjà testé
            if (m80 > 0 && yBaseTail + hP80Max > L) {
                continue;
            }

            int p80BonSensTail = 0;
            if (mode == 1) p80BonSensTail = col0;
            else if (mode == 2) p80BonSensTail = col0 + col1;
            else if (mode == 4) p80BonSensTail = col1;

            int wDroite = W - wP80;
            int capDrLong = (wP80 > 0) ? (wDroite / 57) : 0;
            int capDrLarg = (wP80 > 0 && rotRollsPermise) ? (wDroite / 135) : 0;

            int maxDrModes = (wP80 == 0 || rollsTotal == 0) ? 0 : 2;
            for (int drMode = 0; drMode <= maxDrModes; drMode++) {
                int capDr = (drMode == 1) ? capDrLong : ((drMode == 2) ? capDrLarg : 0);
                int dyDr = (drMode == 1) ? 135 : 57;
                if (drMode > 0 && capDr == 0) continue;

                // Nombre maximum de Rolls qu'on peut loger à droite des m80 P80
                int maxRangsDr = (drMode == 0) ? 0 : Math.min(4, Math.max(1, (hP80Max + dyDr - 1) / dyDr) + 1);
                int maxRollsDr = Math.min(rollsTotal, maxRangsDr * capDr);

                for (int nRollsDr = 0; nRollsDr <= maxRollsDr; nRollsDr++) {
                    if (drMode > 0 && nRollsDr == 0) continue;
                    int rangsDr = (drMode == 0 || capDr == 0) ? 0 : ((nRollsDr + capDr - 1) / capDr);
                    int hDr = rangsDr * dyDr;
                    if (yBaseTail + hDr > L) continue;

                    int remRollsAfterDr = rollsTotal - nRollsDr;

                    // Peut-on glisser des Rolls sous les P80 de gauche (si wP80 >= 135 en large ou >= 57 en long)
                    // pour combler le décrochement ou la fin de remorque ?
                    int maxSousModes = (wP80 > 0 && remRollsAfterDr > 0) ? 2 : 0;
                    for (int sousMode = 0; sousMode <= maxSousModes; sousMode++) {
                        int capSous = 0;
                        int dySous = 0;
                        if (sousMode == 1) {
                            capSous = wP80 / 57;
                            dySous = 135;
                        } else if (sousMode == 2 && rotRollsPermise) {
                            capSous = wP80 / 135;
                            dySous = 57;
                        }
                        if (sousMode > 0 && capSous == 0) continue;

                        int dispoSousY = Math.max(0, L - (yBaseTail + hP80Max));
                        int maxRangsSous = (sousMode == 0) ? 0 : Math.min(2, dispoSousY / dySous);
                        int maxRollsSous = Math.min(remRollsAfterDr, maxRangsSous * capSous);

                        for (int nRollsSous = 0; nRollsSous <= maxRollsSous; nRollsSous++) {
                            if (sousMode > 0 && nRollsSous == 0) continue;
                            int rangsSous = (sousMode == 0 || capSous == 0) ? 0 : ((nRollsSous + capSous - 1) / capSous);
                            int hSous = rangsSous * dySous;

                            int yGaucheFin = yBaseTail + hP80Max + hSous;
                            int yDroiteFin = yBaseTail + hDr;
                            int yBaseFullRolls = Math.max(yGaucheFin, yDroiteFin);

                            int remRollsFull = remRollsAfterDr - nRollsSous;

                            // Étape 5 : Placement des Rolls restants (remRollsFull) sur toute la largeur W à partir de yBaseFullRolls
                            evaluerZoneFinaleRolls4(
                                    W, L, yBaseTail, yGaucheFin, yDroiteFin, yBaseFullRolls,
                                    r100Long, r100Larg, profilTrans100, trans100, trans100Rot, trans80,
                                    r80Long, r80Larg,
                                    mode, col0, col1, wP80, hCol0, hCol1,
                                    drMode, nRollsDr, hDr,
                                    sousMode, nRollsSous, hSous,
                                    m80, p80BonSensTail, remRollsFull, rotRollsPermise,
                                    nbChargeesAfter80, surfaceChargeeAfter80, nbP100EnRangsComplets,
                                    nbP80BonSensAfter80, surfacePerdueAfter80,
                                    cap100Long, cap100Larg, cap80Long, cap80Larg, capRollsLong, capRollsLarg,
                                    minYTrans100, maxYTrans100,
                                    bestRef
                            );
                        }
                    }
                }
            }
        }
    }

    private void evaluerZoneFinaleRolls4(
            int W, int L, int yBaseTail, int yGaucheFin, int yDroiteFin, int yBaseFullRolls,
            int r100Long, int r100Larg,
            ProfilCouloirs4 profilTrans100, int[] trans100, int[] trans100Rot, int[] trans80,
            int r80Long, int r80Larg,
            int modeP80Tail, int col0, int col1, int wP80, int hCol0, int hCol1,
            int drMode, int nRollsDr, int hDr,
            int sousMode, int nRollsSous, int hSous,
            int m80, int p80BonSensTail, int remRollsFull, boolean rotRollsPermise,
            int nbChargeesAfter80, int surfaceChargeeAfter80, int nbP100EnRangsComplets,
            int nbP80BonSensAfter80, int surfacePerdueAfter80,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg,
            int minYTrans100, int maxYTrans100,
            EvalSolution4[] bestRef
    ) {
        int dispoY = Math.max(0, L - yBaseFullRolls);
        int maxRRollsLong = (capRollsLong > 0 && !autorisationForcerRotationRolls)
                ? Math.min(remRollsFull / capRollsLong, dispoY / 135)
                : 0;

        for (int rRollsLong = 0; rRollsLong <= maxRRollsLong; rRollsLong++) {
            int remAfterLong = remRollsFull - rRollsLong * capRollsLong;
            int yAfterLong = yBaseFullRolls + rRollsLong * 135;
            int dispoAfterLong = Math.max(0, L - yAfterLong);

            int maxRRollsLarg = (capRollsLarg > 0 && rotRollsPermise)
                    ? Math.min(remAfterLong / capRollsLarg, dispoAfterLong / 57)
                    : 0;

            for (int rRollsLarg = 0; rRollsLarg <= maxRRollsLarg; rRollsLarg++) {
                int remFinal = remAfterLong - rRollsLarg * capRollsLarg;
                int yAfterLarg = yAfterLong + rRollsLarg * 57;
                int dispoFinal = Math.max(0, L - yAfterLarg);

                // Pour les derniers Rolls hors rang complet (remFinal < capRollsLong), on teste en long (57*135) et en large (135*57)
                int maxFinalMode = rotRollsPermise ? 2 : 1;
                for (int finalMode = 1; finalMode <= maxFinalMode; finalMode++) {
                    if (autorisationForcerRotationRolls && finalMode == 1 && rotRollsPermise && capRollsLarg > 0) {
                        continue;
                    }
                    int capFinal = (finalMode == 1) ? capRollsLong : capRollsLarg;
                    int dyFinal = (finalMode == 1) ? 135 : 57;
                    int nFinalRentres = 0;
                    int hFinal = 0;
                    if (remFinal > 0 && capFinal > 0 && dispoFinal >= dyFinal) {
                        int maxRangsFin = dispoFinal / dyFinal;
                        nFinalRentres = Math.min(remFinal, maxRangsFin * capFinal);
                        int rangsFin = (nFinalRentres + capFinal - 1) / capFinal;
                        hFinal = rangsFin * dyFinal;
                    }

                    int nbP100EtP80Chargees = nbChargeesAfter80 + m80;
                    int rollsCharges = nRollsDr + nRollsSous + rRollsLong * capRollsLong + rRollsLarg * capRollsLarg + nFinalRentres;
                    int nbChargees = nbP100EtP80Chargees + rollsCharges;
                    int surfaceChargee = surfaceChargeeAfter80 + m80 * (80 * 120) + rollsCharges * (57 * 135);
                    int nbP80BonSens = nbP80BonSensAfter80 + p80BonSensTail;

                    int rollsEnLong = ((drMode == 1) ? nRollsDr : 0)
                            + ((sousMode == 1) ? nRollsSous : 0)
                            + rRollsLong * capRollsLong
                            + ((finalMode == 1) ? nFinalRentres : 0);
                    int rollsEnLarge = rollsCharges - rollsEnLong;
                    int nbRollsSensVoulu = autorisationForcerRotationRolls ? rollsEnLarge : rollsEnLong;

                    // Calcul de la surface perdue interne
                    int surfacePerdueTail = 0;
                    boolean hasRollsBelow = (rRollsLong > 0 || rRollsLarg > 0 || nFinalRentres > 0);
                    if (hasRollsBelow && (m80 > 0 || nRollsDr > 0 || nRollsSous > 0)) {
                        // La zone yBaseTail..yBaseFullRolls est refermée par des Rolls en dessous :
                        // tout espace non occupé dans ce rectangle est un trou interne enfermé !
                        int surfaceZoneTail = W * (yBaseFullRolls - yBaseTail);
                        int surfaceUtileTail = m80 * (80 * 120) + (nRollsDr + nRollsSous) * (57 * 135);
                        surfacePerdueTail += Math.max(0, surfaceZoneTail - surfaceUtileTail);
                    } else if (m80 > 0 || nRollsDr > 0 || nRollsSous > 0) {
                        // Fin de chargement ouverte vers les portes : on ne pénalise que les décrochements internes entre P80
                        // ou un retour en L sous les P80 (nRollsSous > 0)
                        if (modeP80Tail == 2 && hCol0 != hCol1) {
                            surfacePerdueTail += Math.abs(hCol0 - hCol1) * 80;
                        } else if (modeP80Tail == 4 && hCol0 != hCol1) {
                            surfacePerdueTail += Math.abs(hCol0 - hCol1) * 80;
                        }
                        if (nRollsSous > 0) {
                            int nbColsSous = (sousMode == 1) ? Math.min(nRollsSous, Math.max(1, wP80 / 57)) : 1;
                            int wSousUsed = (sousMode == 1) ? (nbColsSous * 57) : 135;
                            surfacePerdueTail += Math.max(0, wP80 - wSousUsed) * hSous;
                        }
                        if (nRollsDr == 0 && nRollsSous == 0 && m80 > 1 && (W % 80 != 0)) {
                            surfacePerdueTail += Math.max(0, W - wP80) * (yGaucheFin - yBaseTail);
                        }
                    }

                    int maxYCharge = yAfterLarg + hFinal;
                    int ecart = Math.abs(yGaucheFin - yDroiteFin);
                    if (rRollsLong == 0 && rRollsLarg == 0 && nFinalRentres == 0 && m80 == 0 && nRollsDr == 0 && nRollsSous == 0) {
                        ecart = maxYTrans100 - minYTrans100;
                    }

                    int nbRangsComplets = r100Long + r100Larg + r80Long + r80Larg + rRollsLong + rRollsLarg;

                    EvalSolution4 cand = new EvalSolution4();
                    cand.nbP100EtP80Chargees = nbP100EtP80Chargees;
                    cand.nbPalettesChargees = nbChargees;
                    cand.surfaceChargee = surfaceChargee;
                    cand.nbP100EnRangsComplets = nbP100EnRangsComplets;
                    cand.surfacePerdueFond = surfacePerdueAfter80 + surfacePerdueTail;
                    cand.nbP80BonSens = nbP80BonSens;
                    cand.nbRollsSensVoulu = nbRollsSensVoulu;
                    cand.maxYCharge = maxYCharge;
                    cand.ecartCouloirs = ecart;
                    cand.nbRangsComplets = nbRangsComplets;

                    if (cand.estMeilleureQue(bestRef[0])) {
                        cand.r100Long = r100Long;
                        cand.r100Larg = r100Larg;
                        if (profilTrans100 != null && trans100 != null) {
                            int K = profilTrans100.totalCouloirs();
                            cand.profilTrans100 = profilTrans100;
                            cand.transCol100 = new int[K];
                            cand.transCol100Rot = new int[K];
                            cand.transCol80 = new int[K];
                            cand.transColLargeur = new int[K];
                            cand.transColXStart = new int[K];
                            int xCur = 0;
                            for (int k = 0; k < K; k++) {
                                int largCol = (k < profilTrans100.c120) ? 120 : ((k < profilTrans100.c120 + profilTrans100.c100) ? 100 : 80);
                                cand.transCol100[k] = trans100[k];
                                cand.transCol100Rot[k] = trans100Rot[k];
                                cand.transCol80[k] = trans80[k];
                                cand.transColLargeur[k] = largCol;
                                cand.transColXStart[k] = xCur;
                                xCur += largCol;
                            }
                        }
                        cand.r80Long = r80Long;
                        cand.r80Larg = r80Larg;
                        cand.modeP80Tail = modeP80Tail;
                        cand.tail80Col0 = col0;
                        cand.tail80Col1 = col1;
                        cand.rollsDroiteMode = drMode;
                        cand.rollsDroiteCount = nRollsDr;
                        cand.rollsSousP80Mode = sousMode;
                        cand.rollsSousP80Count = nRollsSous;
                        cand.rRollsLong = rRollsLong;
                        cand.rRollsLarg = rRollsLarg;
                        cand.remRollsFinalMode = finalMode;
                        cand.remRollsFinalCount = nFinalRentres;
                        bestRef[0] = cand;
                    }
                }
            }
        }
    }

    private void construirePalettesPosees4(
            EvalSolution4 sol,
            ArrayList<Palette> pool100,
            ArrayList<Palette> pool80,
            ArrayList<Palette> poolRolls,
            ArrayList<Palette> sortie,
            int yDepart, int W, int L,
            int cap100Long, int cap100Larg, int cap80Long, int cap80Larg, int capRollsLong, int capRollsLarg
    ) {
        int idx100 = 0;
        int idx80 = 0;
        int idxRolls = 0;
        int yCur = yDepart;
        int yGauche = yDepart;
        int yDroite = yDepart;

        // 1. Rangs complets P100
        for (int r = 0; r < sol.r100Long; r++) {
            if (yCur + 120 > L) break;
            for (int c = 0; c < cap100Long && idx100 < pool100.size(); c++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(c * 100);
                p.setPositionY(yCur);
                p.setOrientation(0);
                sortie.add(p);
            }
            yCur += 120;
        }
        for (int r = 0; r < sol.r100Larg; r++) {
            if (yCur + 100 > L) break;
            for (int c = 0; c < cap100Larg && idx100 < pool100.size(); c++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(c * 120);
                p.setPositionY(yCur);
                p.setOrientation(1);
                sortie.add(p);
            }
            yCur += 100;
        }
        yGauche = yCur;
        yDroite = yCur;

        // 2. Zone de transition P100 / P80 (si présente)
        if (sol.profilTrans100 != null && sol.transColLargeur != null) {
            int K = sol.transColLargeur.length;
            int yBaseTrans = yCur;
            int maxYTrans = yBaseTrans;
            yGauche = yBaseTrans;
            yDroite = yBaseTrans;

            for (int k = 0; k < K; k++) {
                int xCol = sol.transColXStart[k];
                int largCol = sol.transColLargeur[k];
                int n100 = sol.transCol100[k];
                int n100Rot = sol.transCol100Rot[k];
                int n100Norm = n100 - n100Rot;
                int n80 = sol.transCol80[k];

                int dy100Norm = 100;
                int dy100Rot = 120;
                int dy80 = (largCol == 120) ? 80 : 120;
                int orient80 = (largCol == 120) ? 1 : 0;

                int yCol = yBaseTrans;
                for (int i = 0; i < n100Norm && idx100 < pool100.size(); i++) {
                    if (yCol + dy100Norm > L) break;
                    Palette p = pool100.get(idx100++);
                    p.setPositionX(xCol);
                    p.setPositionY(yCol);
                    p.setOrientation(1);
                    sortie.add(p);
                    yCol += dy100Norm;
                }
                for (int i = 0; i < n100Rot && idx100 < pool100.size(); i++) {
                    if (yCol + dy100Rot > L) break;
                    Palette p = pool100.get(idx100++);
                    p.setPositionX(xCol);
                    p.setPositionY(yCol);
                    p.setOrientation(0);
                    sortie.add(p);
                    yCol += dy100Rot;
                }
                for (int i = 0; i < n80 && idx80 < pool80.size(); i++) {
                    if (yCol + dy80 > L) break;
                    Palette p = pool80.get(idx80++);
                    p.setPositionX(xCol);
                    p.setPositionY(yCol);
                    p.setOrientation(orient80);
                    sortie.add(p);
                    yCol += dy80;
                }
                if (yCol > maxYTrans) maxYTrans = yCol;
                if (k == 0 || (K > 2 && k < K / 2)) {
                    yGauche = Math.max(yGauche, yCol);
                } else {
                    yDroite = Math.max(yDroite, yCol);
                }
            }
            yCur = maxYTrans;
        }

        // 3. Rangs complets P80 (en long 80*120, puis en large 120*80)
        for (int r = 0; r < sol.r80Long; r++) {
            if (yCur + 120 > L) break;
            for (int c = 0; c < cap80Long && idx80 < pool80.size(); c++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(c * 80);
                p.setPositionY(yCur);
                p.setOrientation(0);
                sortie.add(p);
            }
            yCur += 120;
            yGauche = yCur;
            yDroite = yCur;
        }
        for (int r = 0; r < sol.r80Larg; r++) {
            if (yCur + 80 > L) break;
            for (int c = 0; c < cap80Larg && idx80 < pool80.size(); c++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(c * 120);
                p.setPositionY(yCur);
                p.setOrientation(1);
                sortie.add(p);
            }
            yCur += 80;
            yGauche = yCur;
            yDroite = yCur;
        }

        // 4. Zone de transition P80 / Rolls
        int yBaseTail = yCur;
        int wP80 = 0;
        if (sol.modeP80Tail == 1) {
            wP80 = 80;
            int yCol = yBaseTail;
            for (int i = 0; i < sol.tail80Col0 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(0);
                p.setPositionY(yCol);
                p.setOrientation(0);
                sortie.add(p);
                yCol += 120;
            }
            yGauche = yCol;
        } else if (sol.modeP80Tail == 2) {
            wP80 = 160;
            int yCol0 = yBaseTail;
            for (int i = 0; i < sol.tail80Col0 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(0);
                p.setPositionY(yCol0);
                p.setOrientation(0);
                sortie.add(p);
                yCol0 += 120;
            }
            int yCol1 = yBaseTail;
            for (int i = 0; i < sol.tail80Col1 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(80);
                p.setPositionY(yCol1);
                p.setOrientation(0);
                sortie.add(p);
                yCol1 += 120;
            }
            yGauche = Math.max(yCol0, yCol1);
            yDroite = yCol1;
        } else if (sol.modeP80Tail == 3) {
            wP80 = 120;
            int yCol = yBaseTail;
            for (int i = 0; i < sol.tail80Col0 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(0);
                p.setPositionY(yCol);
                p.setOrientation(1);
                sortie.add(p);
                yCol += 80;
            }
            yGauche = yCol;
        } else if (sol.modeP80Tail == 4) {
            wP80 = 200;
            int yCol0 = yBaseTail;
            for (int i = 0; i < sol.tail80Col0 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(0);
                p.setPositionY(yCol0);
                p.setOrientation(1);
                sortie.add(p);
                yCol0 += 80;
            }
            int yCol1 = yBaseTail;
            for (int i = 0; i < sol.tail80Col1 && idx80 < pool80.size(); i++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(120);
                p.setPositionY(yCol1);
                p.setOrientation(0);
                sortie.add(p);
                yCol1 += 120;
            }
            yGauche = yCol0;
            yDroite = yCol1;
        }

        // Rolls à droite des dernières P80
        if (sol.rollsDroiteCount > 0 && wP80 > 0) {
            int wDroite = W - wP80;
            int capDr = (sol.rollsDroiteMode == 1) ? Math.max(1, wDroite / 57) : Math.max(1, wDroite / 135);
            int wElem = (sol.rollsDroiteMode == 1) ? 57 : 135;
            int dyElem = (sol.rollsDroiteMode == 1) ? 135 : 57;
            int orientElem = (sol.rollsDroiteMode == 1) ? 0 : 1;

            int yDr = yBaseTail;
            int placed = 0;
            while (placed < sol.rollsDroiteCount && idxRolls < poolRolls.size()) {
                for (int c = 0; c < capDr && placed < sol.rollsDroiteCount && idxRolls < poolRolls.size(); c++) {
                    Palette p = poolRolls.get(idxRolls++);
                    p.setPositionX(wP80 + c * wElem);
                    p.setPositionY(yDr);
                    p.setOrientation(orientElem);
                    sortie.add(p);
                    placed++;
                }
                yDr += dyElem;
            }
            yDroite = yDr;
        }

        // Rolls sous les P80 de gauche (si présents)
        if (sol.rollsSousP80Count > 0 && wP80 > 0) {
            int capSous = (sol.rollsSousP80Mode == 1) ? Math.max(1, wP80 / 57) : Math.max(1, wP80 / 135);
            int wElem = (sol.rollsSousP80Mode == 1) ? 57 : 135;
            int dyElem = (sol.rollsSousP80Mode == 1) ? 135 : 57;
            int orientElem = (sol.rollsSousP80Mode == 1) ? 0 : 1;

            int ySous = yGauche;
            int placed = 0;
            while (placed < sol.rollsSousP80Count && idxRolls < poolRolls.size()) {
                for (int c = 0; c < capSous && placed < sol.rollsSousP80Count && idxRolls < poolRolls.size(); c++) {
                    Palette p = poolRolls.get(idxRolls++);
                    p.setPositionX(c * wElem);
                    p.setPositionY(ySous);
                    p.setOrientation(orientElem);
                    sortie.add(p);
                    placed++;
                }
                ySous += dyElem;
            }
            yGauche = ySous;
        }

        // 5. Zone finale Rolls sur toute la largeur W (tout en bas)
        yCur = Math.max(yGauche, yDroite);

        for (int r = 0; r < sol.rRollsLong; r++) {
            for (int c = 0; c < capRollsLong && idxRolls < poolRolls.size(); c++) {
                Palette p = poolRolls.get(idxRolls++);
                p.setPositionX(c * 57);
                p.setPositionY(yCur);
                p.setOrientation(0);
                sortie.add(p);
            }
            yCur += 135;
            yGauche = yCur;
            yDroite = yCur;
        }

        for (int r = 0; r < sol.rRollsLarg; r++) {
            for (int c = 0; c < capRollsLarg && idxRolls < poolRolls.size(); c++) {
                Palette p = poolRolls.get(idxRolls++);
                p.setPositionX(c * 135);
                p.setPositionY(yCur);
                p.setOrientation(1);
                sortie.add(p);
            }
            yCur += 57;
            yGauche = yCur;
            yDroite = yCur;
        }

        if (sol.remRollsFinalCount > 0 && idxRolls < poolRolls.size()) {
            int capFin = (sol.remRollsFinalMode == 1) ? Math.max(1, capRollsLong) : Math.max(1, capRollsLarg);
            int wElem = (sol.remRollsFinalMode == 1) ? 57 : 135;
            int dyElem = (sol.remRollsFinalMode == 1) ? 135 : 57;
            int orientElem = (sol.remRollsFinalMode == 1) ? 0 : 1;

            int placed = 0;
            while (placed < sol.remRollsFinalCount && idxRolls < poolRolls.size()) {
                int nbInRow = 0;
                for (int c = 0; c < capFin && placed < sol.remRollsFinalCount && idxRolls < poolRolls.size(); c++) {
                    Palette p = poolRolls.get(idxRolls++);
                    p.setPositionX(c * wElem);
                    p.setPositionY(yCur);
                    p.setOrientation(orientElem);
                    sortie.add(p);
                    placed++;
                    nbInRow++;
                }
                yCur += dyElem;
                yGauche = yCur;
                if (nbInRow * wElem > W / 2) {
                    yDroite = yCur;
                }
            }
        }

        // 6. Éléments à quai (débordement au-delà de L, dessinés tout en bas dans la zone rouge)
        int yDebordement = Math.max(yCur, L);

        while (idx100 < pool100.size()) {
            int parRang = Math.max(1, cap100Larg > 0 ? cap100Larg : cap100Long);
            int orient = (cap100Larg > 0) ? 1 : 0;
            int wElem = (orient == 1) ? 120 : 100;
            int dyElem = (orient == 1) ? 100 : 120;
            int nbInRow = 0;
            for (int c = 0; c < parRang && idx100 < pool100.size(); c++) {
                Palette p = pool100.get(idx100++);
                p.setPositionX(c * wElem);
                p.setPositionY(yDebordement);
                p.setOrientation(orient);
                sortie.add(p);
                nbInRow++;
            }
            yDebordement += dyElem;
            yGauche = yDebordement;
            if (nbInRow > 1) yDroite = yDebordement;
        }

        while (idx80 < pool80.size()) {
            int Reste80 = pool80.size() - idx80;
            boolean enLarge = (sol.r80Larg > 0 || Reste80 <= 2) && cap80Larg > 0 && Reste80 < cap80Long;
            int parRang = enLarge ? Math.max(1, cap80Larg) : Math.max(1, cap80Long);
            int orient = enLarge ? 1 : 0;
            int wElem = enLarge ? 120 : 80;
            int dyElem = enLarge ? 80 : 120;
            int nbInRow = 0;
            for (int c = 0; c < parRang && idx80 < pool80.size(); c++) {
                Palette p = pool80.get(idx80++);
                p.setPositionX(c * wElem);
                p.setPositionY(yDebordement);
                p.setOrientation(orient);
                sortie.add(p);
                nbInRow++;
            }
            yDebordement += dyElem;
            yGauche = yDebordement;
            if (nbInRow * wElem > W / 2) yDroite = yDebordement;
        }

        while (idxRolls < poolRolls.size()) {
            boolean enLarge = autorisationForcerRotationRolls && capRollsLarg > 0;
            int parRang = enLarge ? Math.max(1, capRollsLarg) : Math.max(1, capRollsLong);
            int orient = enLarge ? 1 : 0;
            int wElem = enLarge ? 135 : 57;
            int dyElem = enLarge ? 57 : 135;
            int nbInRow = 0;
            for (int c = 0; c < parRang && idxRolls < poolRolls.size(); c++) {
                Palette p = poolRolls.get(idxRolls++);
                p.setPositionX(c * wElem);
                p.setPositionY(yDebordement);
                p.setOrientation(orient);
                sortie.add(p);
                nbInRow++;
            }
            yDebordement += dyElem;
            yGauche = yDebordement;
            if (nbInRow * wElem > W / 2) yDroite = yDebordement;
        }

        sol.rangA = yGauche;
        sol.rangB = yDroite;
    }

    private void afficherResultatsCalculer4(
            ArrayList<Palette> listeDePalettesPosees,
            int p80Total, int p100Total, int rollsTotal,
            int rangA, int rangB, int W, int L
    ) {
        int p80EnRemorque = 0;
        int p100EnRemorque = 0;
        int rollsEnRemorque = 0;
        int maxYbGlobal = L;

        for (Palette p : listeDePalettesPosees) {
            if (p.getPositionYb() > maxYbGlobal) {
                maxYbGlobal = p.getPositionYb();
            }
            if (p.getPositionX() >= 0 && p.getPositionXb() <= W && p.getPositionY() >= 0 && p.getPositionYb() <= L) {
                if (p.getLargeur() == 80) {
                    p80EnRemorque++;
                } else if (p.getLargeur() == 100) {
                    p100EnRemorque++;
                } else if (p.getLargeur() == 57) {
                    rollsEnRemorque++;
                }
            }
        }

        int p80AQuai = Math.max(0, p80Total - p80EnRemorque);
        int p100AQuai = Math.max(0, p100Total - p100EnRemorque);
        int rollsAQuai = Math.max(0, rollsTotal - rollsEnRemorque);

        double a = rangA;
        double b = rangB;
        double resteA = L - a;
        double resteB = L - b;

        double surfaceRemorqueM2 = (L * (double) W) / 10000.0;
        double surfaceUtiliseeM2 = (
                p80EnRemorque * (80.0 * 120.0)
                + p100EnRemorque * (100.0 * 120.0)
                + rollsEnRemorque * (57.0 * 135.0)
        ) / 10000.0;
        double surfaceInutiliseeM2 = Math.max(0.0, Math.round((surfaceRemorqueM2 - surfaceUtiliseeM2) * 100.0) / 100.0);
        surfaceRemorqueM2 = Math.round(surfaceRemorqueM2 * 100.0) / 100.0;

        if (resultat != null) {
            resultat.setText(
                    "Rang A : " + (a / 100.0) + "    Reste : " + (resteA / 100.0) + "\n" +
                    "Rang B : " + (b / 100.0) + "    Reste : " + (resteB / 100.0) + "\n" +
                    "Surface inutilisée : " + surfaceInutiliseeM2 + "/" + surfaceRemorqueM2 + "\n" +
                    p80AQuai + " P80 à quai" + "\n" +
                    p100AQuai + " P100 à quai" + "\n" +
                    rollsAQuai + " Rolls à quai"
            );
        }

        drawView.oldMode = false;
        drawView.listeDePalettesPosees = listeDePalettesPosees;
        drawView.listeP80 = listeP80;
        drawView.listeP100 = listeP100;
        drawView.listeRolls = listeRolls;

        int sizeX = (int) (W / agrandissement) + 20;
        int sizeY = Math.max((int) (L / agrandissement) + 145, (int) (maxYbGlobal / agrandissement) + 30);
        drawView.setLayoutParams(new LinearLayout.LayoutParams(sizeX, sizeY));

        if (drawPosRemorque != null) {
            drawPosRemorque.removeView(drawView);
            drawPosRemorque.addView(drawView);
        }
        drawView.invalidate();
    }


}
