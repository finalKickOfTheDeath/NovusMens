package com.math.novusmens_git.enigme;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;

import com.math.novusmens_git.R;

/**
 * Created by Renan on 27/02/2017.
 */
public class Trou implements Element {
    private ImageView représentation;

    public Trou(Activity activité){
        représentation = new ImageView(activité.getApplicationContext());
        représentation.setImageResource(R.mipmap.trou);
        représentation.setTag("trou");
    }

    public ImageView getReprésentation(){
        return représentation;
    }

}
