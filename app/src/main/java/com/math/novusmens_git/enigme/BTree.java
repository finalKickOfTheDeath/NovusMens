package com.math.novusmens_git.enigme;

/**
 * Created by Alice on 23/03/2017.
 */

public class BTree implements IBTree {

    private String question;
    private String reponseGauche;
    private String reponseDroite;
    private BTree leftTree;
    private BTree rightTree;

    public BTree(String Q, String rG, String rS,BTree g, BTree d) {
        question = Q;
        reponseGauche=rG;
        reponseDroite=rS;
        leftTree = g;
        rightTree = d;
    }

    public BTree(String Q, String rG, String rS) {
        question = Q;
        reponseGauche=rG;
        reponseDroite=rS;
        leftTree = new BTree();
        rightTree = new BTree();
    }

    public BTree(String Q) {
        question = Q;
        reponseGauche=null;
        reponseDroite=null;
        leftTree = null;
        rightTree = null;
    }

    public BTree() {
        question = null;
        reponseGauche=null;
        reponseDroite=null;
        leftTree = null;
        rightTree = null;
    }


    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        return "[" + question + reponseGauche + reponseDroite + "]";
    }

    @Override
    public boolean isEmpty() {

        return question == null;
    }

    @Override
    public String getQuestion()  {
            return this.question;
    }

    @Override
    public String getReponseGauche() {
            return this.reponseGauche;
    }

    @Override
    public String getReponseDroite()  {
            return this.reponseDroite;
    }

    @Override
    public BTree getLeftTree() throws Exception {
        if(!isEmpty()) {
            return leftTree;
        } else {
            throw new Exception ("Arbre vide n'a pas de fils gauche");
        }
    }

    @Override
    public BTree getRightTree() throws Exception {
        if(!isEmpty()) {
            return rightTree;
        } else {
            throw new Exception ("Arbre vide n'a pas de fils droit");
        }
    }

    @Override
    public void setLeftTree(BTree leftTree) throws Exception {
        if(!isEmpty()) {
            this.leftTree = leftTree;
        } else {
            throw new Exception ("Arbre vide ne peut pas avoir de fils gauche");
        }
    }

    @Override
    public void setRightTree(BTree rightTree) throws Exception {
        if(!isEmpty()) {
            this.rightTree = rightTree;
        } else {
            throw new Exception ("Arbre vide ne peut pas avoir de fils droit");
        }
    }

    @Override
    public void setReponseGauche(String v)  {
        reponseGauche=v;
    }

    private void setQuestion(String v) {
        question=v;
    }

    @Override
    public void setReponseDroite(String v) {
        reponseDroite=v;
    }
}
