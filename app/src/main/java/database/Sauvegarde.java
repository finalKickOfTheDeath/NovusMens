package database;

/**
 * Created by Math on 20/03/2017.
 */

public class Sauvegarde {

    private long id;
    private String date;
    private int pointTemps;
    private int numNiveau;

    public Sauvegarde(String date, int pointTemps, int numNiveau) {
        this.date = date;
        this.pointTemps = pointTemps;
        this.numNiveau = numNiveau;
    }

    public Sauvegarde(long id, String date, int pointTemps, int numNiveau) {
        this(date, pointTemps, numNiveau);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPointTemps() {
        return pointTemps;
    }

    public void setPointTemps(int pointTemps) {
        this.pointTemps = pointTemps;
    }

    public int getNumNiveau() {
        return numNiveau;
    }

    public void setNumNiveau(int numNiveau) {
        this.numNiveau = numNiveau;
    }
}
