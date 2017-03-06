package enigme;

import android.widget.TextView;

import exceptionEnigme.VaseDéjàPleinException;
import exceptionEnigme.VaseVideException;


/**
 * Created by Math on 05/02/2017.
 */

public class Vase {

    private int capacite;
    private int contenance;
    private TextView textView;

    public Vase(int capacite, int contenance) {
        this.capacite = capacite;
        this.contenance = contenance;
    }

    public Vase(int capacite, int contenance, TextView textView) {
        this(capacite, contenance);
        this.textView = textView;
    }

    public void verser(Vase receveur) throws VaseDéjàPleinException, VaseVideException {
        if(this.getContenance() == 0) {
            throw new VaseVideException();
        }
        if(receveur.getContenance() == receveur.getCapacite()) {
            throw new VaseDéjàPleinException();
        }
        int quantiteMaxPossible = receveur.getCapacite() - receveur.getContenance();
        int quantiteTransvasée;
        int quantiteRestance;
        if(this.getContenance() > quantiteMaxPossible) {
            quantiteTransvasée = quantiteMaxPossible;
            quantiteRestance = this.getContenance() - quantiteMaxPossible;
        } else {
            quantiteTransvasée = this.getContenance();
            quantiteRestance = 0;
        }
        if(quantiteRestance < 0)
            quantiteRestance = 0;
        this.enlever(quantiteTransvasée);
        receveur.ajouter(quantiteTransvasée);
    }

    @Override
    public String toString() {
        return contenance + "l";
    }

    private void ajouter(int quantite) {
        contenance += quantite;
    }

    private void enlever(int quantite) {
        contenance -= quantite;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getContenance() {
        return contenance;
    }

    public TextView getTextView() {
        return textView;
    }

}
