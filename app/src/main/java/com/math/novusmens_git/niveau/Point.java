package com.math.novusmens_git.niveau;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Math on 21/03/2017.
 */

public class Point implements Parcelable {

    private long id;
    private boolean resolu;

    public Point() {

    }

    public Point(long id, boolean resolu) {
        this.id = id;
        this.resolu = resolu;
    }


    protected Point(Parcel in) {
        getFromParcel(in);
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeByte((byte) (resolu ? 1 : 0));
    }

    public void getFromParcel(Parcel in) {
        id = in.readLong();
        resolu = in.readByte() != 0;
    }

}
