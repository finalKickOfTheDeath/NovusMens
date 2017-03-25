package com.math.novusmens_git.enigme;

/**
 * Created by Alice on 23/03/2017.
 */

public interface IBTree {

    boolean isEmpty();
	  /* accesseur : renvoie vrai si l'arbre ne contient aucun element */

    String getQuestion() throws Exception;
      /* accesseur : renvoie la valeur contenue a la racine de l'arbre */

    BTree getLeftTree() throws Exception;
	  /* accesseur : renvoie le sous-arbre gauche de l'arbre */

    BTree getRightTree() throws Exception;
	  /* accesseur : renvoie le sous-arbre droit de l'arbre */

    String getReponseGauche() throws Exception;
      /* accesseur : renvoie la valeur a la racine du sous-arbre gauche */

    String getReponseDroite() throws Exception;
	  /* accesseur : renvoie la valeur a la racine du sous-arbre droit */

    void setLeftTree(BTree leftTree) throws Exception;
	  /* modificateur : ajoute un sous-arbre gauche a la racine de l'arbre (si libre) */

    void setRightTree(BTree rightTree) throws Exception;
	  /* modificateur : ajoute un sous-arbre droit a la racine de l'arbre (si libre) */

    void setReponseGauche(String v) throws Exception;

    void setReponseDroite(String v) throws Exception;
	  /* modificateur : ajoute une valeur en fils droit de la racine (si libre) */
}
