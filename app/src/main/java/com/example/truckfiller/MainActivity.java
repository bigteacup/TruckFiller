package com.example.truckfiller;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutA ;
    private TextInputEditText nombre80120;
    private TextInputEditText nombre100120;
    private TextInputEditText poidPalet;
    private TextInputEditText piecesParColis;
    private EditText resultat;
    //palettes hors rangs complet
    private ToggleButton switchAutoriserRotation80Seulle;
    private ToggleButton switchAutoriserRotation100Seulle;
    private boolean autorisationRotation80Seulle = true;
    private boolean autorisationRotation100Seulle = false;

    //
    private ToggleButton switchAutoriserRotation80SiPossibleDeFaireUnRang;
    private ToggleButton switchAutoriserRotation100SiPossibleDeFaireUnRang;
    private boolean autorisationRotation80SiPossibleDeFaireUnRang = true;
    private boolean autorisationRotation100SiPossibleDeFaireUnRang = false;















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
            public void onTextChanged(CharSequence s, int st, int b, int c)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                calculer();
            }
        });

        nombre100120 = findViewById(R.id.nombre100120);
        nombre100120.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int st, int b, int c)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int st, int c, int a)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                calculer();
            }
        });

switchAutoriserRotation80Seulle = findViewById(R.id.autoriserRotation80Seulle);
      switchAutoriserRotation80Seulle.setChecked(true);
switchAutoriserRotation80Seulle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //   switcherEtat(switchAutoriserRotation80.isChecked());
    if(autorisationRotation80Seulle == true){
        autorisationRotation80Seulle = false;
    } else{
        autorisationRotation80Seulle = true;
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


     //   SiPossibleDeFaireUnRang
        switchAutoriserRotation80SiPossibleDeFaireUnRang = findViewById(R.id.autoriserRotation80SiPossibleDeFaireUnRang);
        switchAutoriserRotation80SiPossibleDeFaireUnRang.setChecked(true);
        switchAutoriserRotation80SiPossibleDeFaireUnRang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   switcherEtat(switchAutoriserRotation80.isChecked());
                if(autorisationRotation80SiPossibleDeFaireUnRang == true){
                    autorisationRotation80SiPossibleDeFaireUnRang = false;
                } else{
                    autorisationRotation80SiPossibleDeFaireUnRang = true;
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
private void switcherEtat(boolean etat){

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





    private void calculer(){
        Remorque remorque = new Remorque();
        remorque.hauteur = 300;
        remorque.largeur = 240;
        remorque.longueur = 1320;
        ArrayList<Palette> listeP80 = new ArrayList<>();
        ArrayList<Palette> listeP100 = new ArrayList<>();
        listeP80.clear();
        listeP100.clear();

        int p80 = 0;
        int p80Rang3 = 3;
        int p80Rang2 = 2;

        int p100 = 0;
        int p100Rang2 = 2;



          try {
              p80 = Integer.parseInt(nombre80120.getText().toString());
          }catch(Exception e){p80=0;}
          try {
              p100 = Integer.parseInt(nombre100120.getText().toString());
          }catch(Exception e){p100=0;}

          for(int i = 0; i < p80; i++){
              Palette p = new Palette();
              p.id = i+1;
              p.longueur = 120;
              p.largeur = 80;
              listeP80.add(p);
          }
        for(int i = 0; i < p100; i++){
            Palette p = new Palette();
            p.id = i+1;
            p.longueur = 120;
            p.largeur = 100;
            listeP100.add(p);
        }
        System.out.println("P80 : " + listeP80.size());
        System.out.println("P100 : " + listeP100.size());

        //trouver des rangs droits possibles
        boolean continuer = true;
      //  int i = 1 ;
       // while(continuer){

            double a = remorque.largeur / listeP80.get(0).largeur;
            if((a*listeP80.get(0).largeur) % a  == 0) {
                System.out.println("P80 : la largeur autorise des rangs complets de : " + a);
            }
                a = remorque.largeur / listeP80.get(0).longueur;
                if((a*listeP80.get(0).longueur) % a  == 0){
                    System.out.println("P80 : la longueur autorise des rangs complets de : "+ a);
              //  i++;
            }

      //  }





        int p80RangComplet3 = (listeP80.size()-(listeP80.size() % p80Rang3))/3;
        int p80RangComplet2 = 0;
        int p100RangComplet2 = (listeP100.size()-(listeP100.size() % p100Rang2))/2;
        int p80Reste = (listeP80.size() % p80Rang3);
        int p100Reste = (listeP100.size() % p100Rang2);

        //Rotation pour faire rang complets alternatif
        if(p80Reste > 0 && autorisationRotation80SiPossibleDeFaireUnRang == true){
            if(p80Reste % p80Rang2 == 0){
                p80RangComplet2++ ;
                p80Reste=0;
            }
        }

        int longueurOcupeeParLesRangsComplets = ((p80RangComplet3*120)+(p80RangComplet2*80))+p100RangComplet2*100;



        //Rang incomplet à rotation interdites
        //TODO
        int longueurOcupeeParLesRangsIncomplets80 = 0;
        if(p80Reste > 1){
            longueurOcupeeParLesRangsIncomplets80 = 120;
            p80Reste =0;
        }


        //Rotation pour palette seulle
        int p80LongueurLargeur = 80; //
        if(p80Reste == 1 && autorisationRotation80Seulle == true){
            p80LongueurLargeur = 80;
        }else{
            p80LongueurLargeur = 120;

        }



        double longueurRangA = longueurOcupeeParLesRangsComplets +longueurOcupeeParLesRangsIncomplets80+ (p80Reste*p80LongueurLargeur);
        double longueurRangB = longueurOcupeeParLesRangsComplets +longueurOcupeeParLesRangsIncomplets80+ (p100Reste*100);

        double resteA = (remorque.longueur-longueurRangA);

        double resteB = (remorque.longueur-longueurRangB);


        System.out.println("                                                        ");
        System.out.println("Rang A : " +longueurRangA/100 +"    Reste : " +resteA/100);
        System.out.println("Rang B : " +longueurRangB/100 +"    Reste : "+resteB/100);
        resultat = findViewById(R.id.resultat);
        resultat.setText("Rang A : " +longueurRangA/100 +"    Reste : " +resteA/100  +"\n" +
                "Rang B : " +longueurRangB/100 +"    Reste : "+resteB/100 );


    }
}
