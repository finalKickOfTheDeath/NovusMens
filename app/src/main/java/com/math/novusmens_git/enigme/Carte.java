package com.math.novusmens_git.enigme;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by Renan on 23/03/2017.
 */

public class Carte {
    private TableLayout carte;
    private Point posEntrée, posSortie, posPerso;
    private Personnage personnage;
    private final int LONGUEUR, LARGEUR;



    public Carte(int longueur, int largeur, Point entrée, Point sortie, TableLayout carte) {
        this.LONGUEUR = longueur;
        this.LARGEUR = largeur;
        this.posEntrée = new Point(entrée);
        this.posSortie = new Point(sortie);
        this.posPerso = new Point(entrée);
        this.personnage = new Personnage(carte.getContext());
        this.carte = carte;
        initCarte();

    }

    private void initCarte() {
        for(int i = 0; i < LARGEUR; ++i){
            carte.addView(new TableRow(carte.getContext()),i);
            TableRow lignei = (TableRow)carte.getChildAt(i);
            for (int j = 0; j < LONGUEUR; ++j){
                Passage passage = new Passage(lignei.getContext());
                lignei.addView(passage.getReprésentation(),j);
            }
        }
        //ajouter(personnage, posPerso);
        //ajouter(sortie, posSortie);

    }

    public void déplacerPersonnage(int dx, int dy) {

        try {
            View objet = ((TableRow) carte.getChildAt(posPerso.x + dx)).getChildAt(posPerso.y + dy);
            //Le personnage tombe-t-il dans un trou ?
            if ("trou".equals(objet.getTag())) {
                Log.d("position", "PERDU");
                return;
            }

            //Le personnage pousse-t-il une caisse ?
            if ("caisse".equals(objet.getTag())) {

                //La caisse tombe-t-elle dans un trou ?
                if ("trou".equals(((TableRow)carte.getChildAt(posPerso.x +(2*dx))).getChildAt(posPerso.y + (2*dy)).getTag())) {
                    ajouter(new Passage(carte.getContext()), new Point(posPerso.x+(2*dx), posPerso.y+(2*dy)));
                }
                //La caisse est-t-elle suivie d'une autre caisse ?
                else if ("caisse".equals(((TableRow)carte.getChildAt(posPerso.x +(2*dx))).getChildAt(posPerso.y + (2*dy)).getTag())) {
                    return; // car trop lourd => le personnage n'avacnce pas
                }
                else {
                    ajouter(new Caisse(carte.getContext()), new Point(posPerso.x+(2*dx), posPerso.y+(2*dy)));
                }

            }

            ajouter(new Personnage(carte.getContext()), new Point(posPerso.x+dx, posPerso.y+dy));
            ajouter(new Passage(carte.getContext()), new Point(posPerso.x, posPerso.y));
            posPerso.x += dx;
            posPerso.y += dy;
            Log.d("position", "Personnage à la position ["+posPerso.x+","+posPerso.y+"]");


        }
        catch (Exception e){
             //Log.d("position", "Personnage à la position ["+posPerso.x+","+posPerso.y+"]");
             //Log.d("position", e.getMessage());
        }
    }



    public void ajouter(Element newElement, Point coord) throws IndexOutOfBoundsException {
        if(coord.equals(posSortie))
           newElement.getReprésentation().setBackgroundColor(Color.GREEN);
        else
            newElement.getReprésentation().setBackgroundColor(0x9127187); //Marron
        ((TableRow)carte.getChildAt(coord.x)).removeViewAt(coord.y);
        ((TableRow)carte.getChildAt(coord.x)).addView(newElement.getReprésentation(), coord.y);
    }

    public void clear() {
       try {
            for (int i = 0; i < LARGEUR; ++i) {
                carte.removeViewAt(0);
            }
        this.posPerso = new Point(posEntrée);
        initCarte();
        } catch (Exception e){
           e.printStackTrace();
        }
    }

    public boolean estFini() {
        return posPerso.equals(posSortie);
    }
}
