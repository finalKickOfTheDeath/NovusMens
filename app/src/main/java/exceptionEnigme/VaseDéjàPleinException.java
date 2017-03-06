package exceptionEnigme;

/**
 * Created by Math on 05/02/2017.
 */

public class VaseDéjàPleinException extends Exception {

    public VaseDéjàPleinException() {}

    public String toString() {
        return "Ce vase est déjà plein";
    }

}
