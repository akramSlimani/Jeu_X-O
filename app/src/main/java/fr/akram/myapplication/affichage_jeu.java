package fr.akram.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// Cette classe gère l'affichage de l'interface de jeu.
public class affichage_jeu extends AppCompatActivity {

    private XO XO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_jeu);
        // Initialisation des boutons et du texte.
        Button rejouerBTN = findViewById(R.id.rejouer);
        Button accueilBTN = findViewById(R.id.accueil);
        TextView joueurTour = findViewById(R.id.noms);

        // Cacher les boutons au début du jeu
        rejouerBTN.setVisibility(View.GONE);
        accueilBTN.setVisibility(View.GONE);

        String[] noms = getIntent().getStringArrayExtra("NOM_JOUERS");

        if(noms!=null){
            joueurTour.setText("C'est le tour de "+noms[0]);

        }
        // Initialisation de l'instance de XO et configuration du jeu.
        XO=findViewById(R.id.XO);

        XO.setUpJeu(rejouerBTN,accueilBTN,joueurTour,noms);



    }

    // réinitialiser le jeu et redessine la vue par le bouton rejouer.
    public void rejouerbouton(View view){
        XO.resetJeu();
        XO.invalidate();


    }

    //  rediriger l'utilisateur vers l'activité principale par le bouton accueil.
    public void accueilButton(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}