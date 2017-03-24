package com.math.novusmens_git.personnage;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JoueurTest {

    private Joueur j;

    @Before
    public void init() {
        j = new Joueur(20); // initialise le joueur avec 20 points de temps
    }

    @Test
    public void initTest() throws Exception {
        assertEquals(j.getTimePoint(), 20);
        assertEquals(j.getItems().size(), 0);
    }

    @Test
    public void moveTest() {
        j.move(); // méthode appelé quand le joueur se déplace
        assertEquals(j.getTimePoint(), 19);
    }

    @Test
    public void winTest() {
        Item i = new Item(1, "Dé pipé");
        j.win(i);
        assertEquals(j.getItems().size(), 1);
        assertEquals(j.getItems().get(0).getNom(), "Dé pipé");
    }

    @Test
    public void endLevelTest() {
        Joueur jend = new Joueur(30);
        j.endLevel(); // méthode appelé quand le joueur termine un niveau
        assertEquals(j.getTimePoint(), 15);
    }

    @Test
    public void winTimePointTest() {
        Joueur jwin = new Joueur(5);
        j.winTimePoint(2);
        assertEquals(j.getTimePoint(), 7);
    }

    @Test
    public void looseTimePointTest() {
        Joueur jloos = new Joueur(10);
        j.looseTimePoint(4);
        assertEquals(j.getTimePoint(), 6);
    }

}