package com.math.novusmens_git.niveau;

/**
 * Created by Math on 21/03/2017.
 */

public class Point {

    private long id;
    private boolean resolu;

    public Point(long id, boolean resolu) {
        this.id = id;
        this.resolu = resolu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isResolu() {
        return resolu;
    }

    public void setResolu(boolean resolu) {
        this.resolu = resolu;
    }
}
