package com.math.novusmens_git.enigme;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Math on 25/03/2017.
 */
public class BTreeTest {

    private static final String QUESTION = "question";
    private static final String REPG = "repGauche";
    private static final String REPD = "repDroite";

    private BTree bTree;

    //niveau 1
    private static final String NIV1_Q = "question 1";
    private static final String NIV1_RG = "RG 1";
    private static final String NIV1_RD = "RD 1";

    private BTree bTreeNiv1;

    //niveau 2
    private static final String NIV2_Q_G = "question 2 gauche";
    private static final String NIV2_RG_G = "RG 2 gauche";
    private static final String NIV2_RD_G = "RD 2 gauche";

    private BTree bTreeNiv2_G;

    private static final String NIV2_Q_D = "question 2 droite";
    private static final String NIV2_RG_D = "RG 2 droite";
    private static final String NIV2_RD_D = "RD 2 droite";

    private BTree bTreeNiv2_D;

    //niveau 3
    private static final String NIV3_Q_G_G = "question 3 gauche gauche";
    private static final String NIV3_RG_G_G = "RG 3 gauche gauche";
    private static final String NIV3_RD_G_G = "RD 3 gauche gauche";

    private BTree bTreeNiv3_G_G;

    private static final String NIV3_Q_G_D = "question 3 gauche droite";
    private static final String NIV3_RG_G_D = "RG 3 gauche droite";
    private static final String NIV3_RD_G_D = "RD 3 gauche droite";

    private BTree bTreeNiv3_G_D;

    private static final String NIV3_Q_D_G = "question 3 droite gauche";
    private static final String NIV3_RG_D_G = "RG 3 droite gauche";
    private static final String NIV3_RD_D_G = "RD 3 droite gauche";

    private BTree bTreeNiv3_D_G;

    private static final String NIV3_Q_D_D = "question 3 droite droite";
    private static final String NIV3_RG_D_D = "RG 3 droite droite";
    private static final String NIV3_RD_D_D = "RD 3 droite droite";

    private BTree bTreeNiv3_D_D;

    //reponses
    private static final String REP_G_G_G = "reponse ggg";
    private static final String REP_G_G_D = "reponse ggd";
    private static final String REP_G_D_G = "reponse gdg";
    private static final String REP_G_D_D = "reponse gdd";
    private static final String REP_D_G_G = "reponse dgg";
    private static final String REP_D_G_D = "reponse dgd";
    private static final String REP_D_D_G = "reponse ddg";
    private static final String REP_D_D_D = "reponse ddd";

    private BTree bTreeREP_G_G_G;
    private BTree bTreeREP_G_G_D;
    private BTree bTreeREP_G_D_G;
    private BTree bTreeREP_G_D_D;
    private BTree bTreeREP_D_G_G;
    private BTree bTreeREP_D_G_D;
    private BTree bTreeREP_D_D_G;
    private BTree bTreeREP_D_D_D;

    @Before
    public void init() {
        bTree = new BTree(QUESTION, REPG, REPD);
    }

    @Test
    public void getterTest() {
        assertEquals(QUESTION, bTree.getQuestion());
        assertEquals(REPG, bTree.getReponseGauche());
        assertEquals(REPD, bTree.getReponseDroite());
    }

    @Test
    public void toStringTest() {
        assertEquals("[" + QUESTION + REPG + REPD + "]", "[" + bTree.getQuestion() + bTree.getReponseGauche() + bTree.getReponseDroite() + "]");
    }

    @Test
    public void bTreeFilsTest() {
        try {
            assertEquals(true, bTree.getLeftTree().isEmpty());
            assertEquals(true, bTree.getRightTree().isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void niveau1Test() {
        bTreeNiv1 = new BTree(NIV1_Q, NIV1_RG, NIV1_RD);
        assertEquals(NIV1_Q, bTreeNiv1.getQuestion());
        assertEquals(NIV1_RG, bTreeNiv1.getReponseGauche());
        assertEquals(NIV1_RD, bTreeNiv1.getReponseDroite());
    }

    @Test
    public void niveau2Test() throws Exception {
        bTreeNiv1 = new BTree(NIV1_Q, NIV1_RG, NIV1_RD);
        bTreeNiv2_G = new BTree(NIV2_Q_G, NIV2_RG_G, NIV2_RD_G);
        bTreeNiv2_D = new BTree(NIV2_Q_D, NIV2_RG_D, NIV2_RD_D);

        bTreeNiv1.setLeftTree(bTreeNiv2_G);
        bTreeNiv1.setRightTree(bTreeNiv2_D);

        assertEquals(NIV2_Q_G, bTreeNiv1.getLeftTree().getQuestion());
        assertEquals(NIV2_Q_D, bTreeNiv1.getRightTree().getQuestion());
        assertEquals(NIV2_RG_G, bTreeNiv1.getLeftTree().getReponseGauche());
        assertEquals(NIV2_RD_G, bTreeNiv1.getLeftTree().getReponseDroite());
        assertEquals(NIV2_RG_D, bTreeNiv1.getRightTree().getReponseGauche());
        assertEquals(NIV2_RD_D, bTreeNiv1.getRightTree().getReponseDroite());

    }


    @Test
    public void niveau3Test() throws Exception {
        bTreeNiv1 = new BTree(NIV1_Q, NIV1_RG, NIV1_RD);
        bTreeNiv2_G = new BTree(NIV2_Q_G, NIV2_RG_G, NIV2_RD_G);
        bTreeNiv2_D = new BTree(NIV2_Q_D, NIV2_RG_D, NIV2_RD_D);

        bTreeNiv1.setLeftTree(bTreeNiv2_G);
        bTreeNiv1.setRightTree(bTreeNiv2_D);

        bTreeNiv3_G_G = new BTree(NIV3_Q_G_G, NIV3_RG_G_G, NIV3_RD_G_G);
        bTreeNiv3_G_D = new BTree(NIV3_Q_G_D, NIV3_RG_G_D, NIV3_RD_G_D);
        bTreeNiv3_D_G = new BTree(NIV3_Q_D_G, NIV3_RG_D_G, NIV3_RD_D_G);
        bTreeNiv3_D_D = new BTree(NIV3_Q_D_D, NIV3_RG_D_D, NIV3_RD_D_D);

        bTreeNiv2_G.setLeftTree(bTreeNiv3_G_G);
        bTreeNiv2_G.setRightTree(bTreeNiv3_G_D);
        bTreeNiv2_D.setLeftTree(bTreeNiv3_D_G);
        bTreeNiv2_D.setRightTree(bTreeNiv3_D_D);

        assertEquals(NIV3_Q_G_G, bTreeNiv1.getLeftTree().getLeftTree().getQuestion());
        assertEquals(NIV3_Q_G_D, bTreeNiv1.getLeftTree().getRightTree().getQuestion());
        assertEquals(NIV3_Q_D_G, bTreeNiv1.getRightTree().getLeftTree().getQuestion());
        assertEquals(NIV3_Q_D_D, bTreeNiv1.getRightTree().getRightTree().getQuestion());

        assertEquals(NIV3_RG_G_G, bTreeNiv1.getLeftTree().getLeftTree().getReponseGauche());
        assertEquals(NIV3_RD_G_G, bTreeNiv1.getLeftTree().getLeftTree().getReponseDroite());
        assertEquals(NIV3_RG_G_D, bTreeNiv1.getLeftTree().getRightTree().getReponseGauche());
        assertEquals(NIV3_RD_G_D, bTreeNiv1.getLeftTree().getRightTree().getReponseDroite());
        assertEquals(NIV3_RG_D_G, bTreeNiv1.getRightTree().getLeftTree().getReponseGauche());
        assertEquals(NIV3_RD_D_G, bTreeNiv1.getRightTree().getLeftTree().getReponseDroite());
        assertEquals(NIV3_RG_D_D, bTreeNiv1.getRightTree().getRightTree().getReponseGauche());
        assertEquals(NIV3_RD_D_D, bTreeNiv1.getRightTree().getRightTree().getReponseDroite());
    }

    @Test
    public void reponseTest() throws Exception {
        bTreeNiv1 = new BTree(NIV1_Q, NIV1_RG, NIV1_RD);

        bTreeNiv2_G = new BTree(NIV2_Q_G, NIV2_RG_G, NIV2_RD_G);
        bTreeNiv2_D = new BTree(NIV2_Q_D, NIV2_RG_D, NIV2_RD_D);

        bTreeNiv1.setLeftTree(bTreeNiv2_G);
        bTreeNiv1.setRightTree(bTreeNiv2_D);

        bTreeNiv3_G_G = new BTree(NIV3_Q_G_G, NIV3_RG_G_G, NIV3_RD_G_G);
        bTreeNiv3_G_D = new BTree(NIV3_Q_G_D, NIV3_RG_G_D, NIV3_RD_G_D);
        bTreeNiv3_D_G = new BTree(NIV3_Q_D_G, NIV3_RG_D_G, NIV3_RD_D_G);
        bTreeNiv3_D_D = new BTree(NIV3_Q_D_D, NIV3_RG_D_D, NIV3_RD_D_D);

        bTreeNiv2_G.setLeftTree(bTreeNiv3_G_G);
        bTreeNiv2_G.setRightTree(bTreeNiv3_G_D);
        bTreeNiv2_D.setLeftTree(bTreeNiv3_D_G);
        bTreeNiv2_D.setRightTree(bTreeNiv3_D_D);

        bTreeREP_G_G_G = new BTree(REP_G_G_G);
        bTreeREP_G_G_D = new BTree(REP_G_G_D);
        bTreeREP_G_D_G = new BTree(REP_G_D_G);
        bTreeREP_G_D_D = new BTree(REP_G_D_D);
        bTreeREP_D_G_G = new BTree(REP_D_G_G);
        bTreeREP_D_G_D = new BTree(REP_D_G_D);
        bTreeREP_D_D_G = new BTree(REP_D_D_G);
        bTreeREP_D_D_D = new BTree(REP_D_D_D);

        bTreeNiv3_G_G.setLeftTree(bTreeREP_G_G_G);
        bTreeNiv3_G_G.setRightTree(bTreeREP_G_G_D);
        bTreeNiv3_G_D.setLeftTree(bTreeREP_G_D_G);
        bTreeNiv3_G_D.setRightTree(bTreeREP_G_D_D);
        bTreeNiv3_D_G.setLeftTree(bTreeREP_D_G_G);
        bTreeNiv3_D_G.setRightTree(bTreeREP_D_G_D);
        bTreeNiv3_D_D.setLeftTree(bTreeREP_D_D_G);
        bTreeNiv3_D_D.setRightTree(bTreeREP_D_D_D);

        assertEquals(REP_G_G_G, bTreeNiv1.getLeftTree().getLeftTree().getLeftTree().getQuestion());
        assertEquals(REP_G_G_D, bTreeNiv1.getLeftTree().getLeftTree().getRightTree().getQuestion());
        assertEquals(REP_G_D_G, bTreeNiv1.getLeftTree().getRightTree().getLeftTree().getQuestion());
        assertEquals(REP_G_D_D, bTreeNiv1.getLeftTree().getRightTree().getRightTree().getQuestion());
        assertEquals(REP_D_G_G, bTreeNiv1.getRightTree().getLeftTree().getLeftTree().getQuestion());
        assertEquals(REP_D_G_D, bTreeNiv1.getRightTree().getLeftTree().getRightTree().getQuestion());
        assertEquals(REP_D_D_G, bTreeNiv1.getRightTree().getRightTree().getLeftTree().getQuestion());
        assertEquals(REP_D_D_D, bTreeNiv1.getRightTree().getRightTree().getRightTree().getQuestion());
    }



}