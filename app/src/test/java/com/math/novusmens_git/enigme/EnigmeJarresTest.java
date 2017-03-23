package com.math.novusmens_git.enigme;

import org.junit.Before;
import org.junit.Test;

import com.math.novusmens_git.enigme.EnigmeJarresActivity;
import com.math.novusmens_git.enigme.Vase;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Math on 06/02/2017.
 */

public class EnigmeJarresTest {

    private EnigmeJarresActivity enigme1;

    @Before
    public void init() {
        enigme1 = new EnigmeJarresActivity();
    }

    @Test
    public void enigmeNonResolue() {
        assertEquals(false, enigme1.estResolue());
    }

    @Test
    public void enigmeResolue() {
        enigme1.setVase10l(new Vase(10, 5));
        enigme1.setVase7l((new Vase(7, 5)));
        assertEquals(true, enigme1.estResolue());
    }

}
