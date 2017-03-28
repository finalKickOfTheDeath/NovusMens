package com.math.novusmens_git.enigme;

import android.content.Context;
import android.widget.ImageView;

import com.math.novusmens_git.R;


/**
 * Created by Renan on 06/03/2017.
 */
public class Personnage implements Element{
    private ImageView représentation;

    public Personnage(Context context){
        représentation = new ImageView(context);
        représentation.setImageResource(R.mipmap.fox);

    }

    public ImageView getReprésentation(){
        return représentation;
    }
}
