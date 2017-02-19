package com.ironalloygames.station7.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.ironalloygames.station7.Game;

import android.os.Bundle;

public class Station7Activity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new Game(), true);
    }
}