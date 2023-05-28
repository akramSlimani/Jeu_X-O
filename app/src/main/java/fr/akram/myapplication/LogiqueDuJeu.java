package fr.akram.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogiqueDuJeu {

    private int [][] tabJeu;
    private int player=1;

    private Button rejouerBTN;
    private Button accueilBTN;
    private TextView tourJoueur;

    private String[] noms = {"Joueur 1","Joueur 2"};

    // Constructeur initialisant le tableau de jeu.
    LogiqueDuJeu(){
        tabJeu = new int[3][3];
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                tabJeu[r][c]=0;
            }
        }
    }

    // Mettre à jour le tableau de jeu avec le mouvement du joueur actuel.
    public boolean miseAJourTab(int row,int col){
        if(tabJeu[row-1][col-1]==0){
            tabJeu[row-1][col-1]=player;

            if(player==1){
                tourJoueur.setText("C'est le tour de "+noms[1]);
            }
            else{
                tourJoueur.setText("C'est le tour de "+noms[0]);
            }

            return true;
        }else{
            return false;
        }
    }

    // Vérifier si un joueur a gagné ou si le match est nul.
    public boolean gagnant(){
        boolean b = false;


        for(int r=0; r<3;r++){
            if(tabJeu[r][0]==tabJeu[r][1]&&tabJeu[r][0]==tabJeu[r][2]&& tabJeu[r][0]!=0){
                b=true;
            }
        }
        for(int c=0; c<3;c++){
            if(tabJeu[0][c]==tabJeu[1][c]&&tabJeu[0][c]==tabJeu[2][c]&& tabJeu[0][c]!=0){
                b=true;
            }
        }
        if(tabJeu[0][0]==tabJeu[1][1]&&tabJeu[0][0]==tabJeu[2][2]&& tabJeu[0][0]!=0){
            b=true;
        }
        if(tabJeu[2][0]==tabJeu[1][1]&&tabJeu[2][0]==tabJeu[0][2]&& tabJeu[2][0]!=0){
            b=true;
        }

        int tableRempli=0;
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                if(tabJeu[r][c]!=0){
                    tableRempli+=1;
                }
            }
        }

        if(b){
            rejouerBTN.setVisibility(View.VISIBLE);
            accueilBTN.setVisibility(View.VISIBLE);

            tourJoueur.setText(noms[player-1]+" a gagné.");
            return true;
        }else if(tableRempli==9){
            rejouerBTN.setVisibility(View.VISIBLE);
            accueilBTN.setVisibility(View.VISIBLE);

            tourJoueur.setText("Match Nul");
            return true;

        }else{
            return false;
        }
    }

    // Réinitialiser le jeu à son état initial.
    public void resetJeu() {
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                tabJeu[r][c]=0;
            }
        }

        player=1;

        rejouerBTN.setVisibility(View.GONE);
        accueilBTN.setVisibility(View.GONE);

        tourJoueur.setText("C'est le tour de "+noms[0]);





    }

    // Getters et setters.
    public void setNoms(String[] noms) {
        this.noms = noms;
    }

    public void setAccueilBTN(Button accueilBTN) {
        this.accueilBTN = accueilBTN;
    }

    public void setRejouerBTN(Button rejouerBTN) {
        this.rejouerBTN = rejouerBTN;
    }

    public void setTourJoueur(TextView tourJoueur) {
        this.tourJoueur = tourJoueur;
    }

    public int[][] getTabJeu() {
        return tabJeu;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
