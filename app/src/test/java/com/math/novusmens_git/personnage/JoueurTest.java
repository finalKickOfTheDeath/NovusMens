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
        assertEquals(20, j.getTimePoint());
        assertEquals(0, j.getItems().size());
    }

    @Test
    public void moveTest() {
        j.move(); // méthode appelé quand le joueur se déplace
        assertEquals(19, j.getTimePoint());
    }

    @Test
    public void winTest() {
        Item i = new Item(1, "Dé pipé");
        j.win(i);
        assertEquals(1, j.getItems().size());
        assertEquals("Dé pipé", j.getItems().get(0).getNom());
    }

    @Test
    public void endLevelTest() {
        Joueur jend = new Joueur(30);
        jend.endLevel(); // méthode appelé quand le joueur termine un niveau
        assertEquals(15, jend.getTimePoint());
    }

    @Test
    public void winTimePointTest() {
        Joueur jwin = new Joueur(5);
        jwin.winTimePoint(2);
        assertEquals(7, jwin.getTimePoint());
    }

    @Test
    public void looseTimePointTest() {
        Joueur jloos = new Joueur(10);
        jloos.looseTimePoint(4);
        assertEquals(6, jloos.getTimePoint());
    }

    @Test
    public void gameOverTest() {
        Joueur jover = new Joueur(5);
        jover.gameOver();
        assertEquals(0, jover.getTimePoint());
    }

    @Test
    public void hasTest() {
        Joueur jhas = new Joueur(20);
        Item key1 = new Item("key");
        Item key2 = new Item("key");
        jhas.win(key1);
        assertEquals(true, jhas.has(key2));
        assertEquals(false, jhas.getItems().contains(key2));
    }

}