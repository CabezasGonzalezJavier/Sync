package uk.co.interactive.sync;

import android.app.Application;
import android.graphics.Typeface;

import uk.co.interactive.sync.utils.Constants;

/**
 * Created by javiergonzalezcabezas on 11/6/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initializeTypefaces();

    }

    public static class Fonts {
        public static Typeface PENCIL;
    }

    private void initializeTypefaces() {

        Fonts.PENCIL = Typeface.createFromAsset(getAssets(), Constants.FONT);
    }

}
