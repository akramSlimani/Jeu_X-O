package fr.akram.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Initialisation de l'activité.appelée lorsque l'application démarre.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Charge l'interface utilisateur à partir du fichier XML de layout pour interagir avec l'utilisateur..
        setContentView(R.layout.activity_main);
    }

    // démarrer le jeu en lançant une nouvelle activité.
    public void boutonJouer(View view){
        Intent intent = new Intent(this,baseJeu.class);
        // Lancement de l'activité baseJeu.
        startActivity(intent);
    }
}