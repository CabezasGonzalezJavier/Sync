package uk.co.interactive.sync.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import uk.co.interactive.sync.MainApplication;
import uk.co.interactive.sync.R;

/**
 * Created by javiergonzalezcabezas on 11/6/15.
 */
public class CustomEditView extends EditText {

    public CustomEditView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }
    public CustomEditView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditViewForm);
            //String fontName = a.getString(R.styleable.EditTextForm_fontName);
            //if (fontName!=null) {
            setTypeface(MainApplication.Fonts.PENCIL);
            //}
            a.recycle();
        }
    }
}

