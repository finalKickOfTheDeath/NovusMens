package com.math.novusmens_git.enigme;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.math.novusmens_git.R;

/**
 * Created by Renan on 27/02/2017.
 */
public class Caisse implements Element {

    private ImageView représentation;

    public Caisse(Context context){
        représentation = new ImageView(context);
        représentation.setImageResource(R.mipmap.barrel);
        représentation.setTag("caisse");
    }

    public ImageView getReprésentation(){
        return représentation;
    }
}
