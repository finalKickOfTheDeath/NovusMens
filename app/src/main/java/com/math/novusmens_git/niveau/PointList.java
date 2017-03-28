package com.math.novusmens_git.niveau;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Math on 27/03/2017.
 */

public class PointList extends ArrayList<Point> implements Parcelable {

    public PointList() {

    }

    protected PointList(Parcel in) {
        getFromParcel(in);
    }

    public static final Creator<PointList> CREATOR = new Creator<PointList>() {
        @Override
        public PointList createFromParcel(Parcel in) {
            return new PointList(in);
        }

        @Override
        public PointList[] newArray(int size) {
            return new PointList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i < size; i++)
        {
            Point point = this.get(i); //on lit chaque objet point
            dest.writeLong(point.getId());
            dest.writeByte((byte) (point.isResolu() ? 1 : 0));
        }
    }

    private void getFromParcel(Parcel in) {
        //on vide la liste avant tout remplissage
        this.clear();
        //récupération du nombre d'objet
        int size = in.readInt();
        //on repeuple la liste avec de nouveaux points
        for(int i = 0; i < size; i++) {
            Point point = new Point();
            point.setId(in.readLong());
            Byte b = in.readByte();
            if(b == 1) {
                point.setResolu(true);
            }
            else {
                point.setResolu(false);
            }
            this.add(point);
        }
    }
}
