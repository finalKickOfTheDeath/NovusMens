package com.math.novusmens_git.enigme;

import android.content.Context;
import android.widget.ImageView;

import com.math.novusmens_git.R;

/**
 * Created by Renan on 27/02/2017.
 */

public class Passage implements Element {
    private ImageView représentation;

    public Passage(Context context){
        représentation = new ImageView(context);
        représentation.setImageResource(R.mipmap.chemin);
    }

    public ImageView getReprésentation(){
        return représentation;
    }
}
