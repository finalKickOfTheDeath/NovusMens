package com.math.novusmens_git.joueur;

import com.math.novusmens_git.menu.Joueur;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerTest {
    @Test
    public void test1() throws Exception {
        Joueur j = new Joueur(20); // initialise le joueur avec 20 points de temps
        assertEquals(j.getTimePoint(),20);
        assertEquals(j.getItems().size(),0);

        j.move(); // méthode appelé quand le joueur se déplace
        assertEquals(j.getTimePoint(),19);

        j.win("Dé pipé");
        assertEquals(j.getItems().size(),1);
        assertEquals(j.getItems().get(0),"Dé pipé");

        j.endLevel(); // méthode appelé quand le joueur termine un niveau
        assertEquals(j.getTimePoint(),9);
    }
}