package com.math.novusmens_git.enigme;



import org.junit.Before;
import org.junit.Test;

import com.math.novusmens_git.enigme.Vase;
import com.math.novusmens_git.exceptionEnigme.VaseDéjàPleinException;
import com.math.novusmens_git.exceptionEnigme.VaseVideException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Math on 05/02/2017.
 */

public class VaseTest {

    private Vase v10l;
    private Vase v7l;
    private Vase v3l;

    @Before
    public void init() {
         v10l = new Vase(10, 10);
         v7l = new Vase(7, 0);
         v3l = new Vase(3, 0);
    }

    @Test
    public void testGetter() {
        assertEquals(10, v10l.getCapacite());
        assertEquals(7, v7l.getCapacite());
        assertEquals(3, v3l.getCapacite());

        assertEquals(10, v10l.getContenance());
        assertEquals(0, v7l.getContenance());
        assertEquals(0, v3l.getContenance());
    }

    @Test
    public void testVerser() throws VaseVideException, VaseDéjàPleinException {
        v10l.verser(v7l);
        assertEquals(3, v10l.getContenance());
        assertEquals(7, v7l.getContenance());

        v10l.verser(v3l);
        assertEquals(0, v10l.getContenance());
        assertEquals(3, v3l.getContenance());

        v7l.verser(v10l);
        assertEquals(0, v7l.getContenance());
        assertEquals(7, v10l.getContenance());

        v3l.verser(v7l);
        assertEquals(0, v3l.getContenance());
        assertEquals(3, v7l.getContenance());

        v10l.verser(v3l);
        assertEquals(4, v10l.getContenance());
        assertEquals(3, v3l.getContenance());

        v3l.verser(v7l);
        assertEquals(0, v3l.getContenance());
        assertEquals(6, v7l.getContenance());

        v10l.verser(v3l);
        assertEquals(1, v10l.getContenance());
        assertEquals(3, v3l.getContenance());
    }



}
