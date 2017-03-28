package com.math.novusmens_git.enigme;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.math.novusmens_git.R;

/**
 * Created by Renan on 06/03/2017.
 */
public class Sortie implements Element{

    private ImageView représentation;

    public Sortie(Context context){
        représentation = new ImageView(context);
        représentation.setImageResource(R.mipmap.sortie);
        représentation.setBackgroundColor(Color.GREEN);
    }

    public ImageView getReprésentation(){
        return représentation;
    }
}
