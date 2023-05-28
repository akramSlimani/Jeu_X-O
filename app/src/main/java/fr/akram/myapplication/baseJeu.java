package fr.akram.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class baseJeu extends AppCompatActivity {

    private EditText joueur1;
    private EditText joueur2;

    // Méthode appelée à la création de l'activité baseJeu.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Charge l'interface utilisateur à partir du fichier XML de layout.
        setContentView(R.layout.base_jeu);
        // Associe les variables joueur1 et joueur2 aux EditText.
        joueur1=findViewById(R.id.editTextTextPersonName);
        joueur2=findViewById(R.id.editTextTextPersonName2);
    }

    // Elle récupère les noms des joueurs et les transmet à l'activité suivante lorsqu'on appuie su envoyer.
    public void boutonEnvoyer(View view){
        // Récupère les noms entrés par l'utilisateur.
        String joueur1Nom = joueur1.getText().toString();
        String joueur2Nom = joueur2.getText().toString();
        // intention pour passager à l'activité affichage_jeu
        Intent intent = new Intent(this,affichage_jeu.class);
        intent.putExtra("NOM_JOUERS",new String[]{joueur1Nom,joueur2Nom});// Ajoute les noms des joueurs
        startActivity(intent);//fermer l'activité baseJeu et démarre affichage_jeu
    }
}