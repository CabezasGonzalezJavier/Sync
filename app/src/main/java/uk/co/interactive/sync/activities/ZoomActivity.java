package uk.co.interactive.sync.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.interactive.sync.R;
import uk.co.interactive.sync.models.DataWrapper;
import uk.co.interactive.sync.models.Item;
import uk.co.interactive.sync.utils.Constants;

public class ZoomActivity extends Activity implements GestureDetector.OnGestureListener {
    private GestureDetector mDetector;
    private Point mPoint;
    private boolean mShowWindow = false;
    ArrayList<Item> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        mDetector = new GestureDetector(this);

        int[] location = new int[2];
        mPoint = new Point();
        mPoint.x = location[0];
        mPoint.y = location[1];


        // Get intent data
        Intent i = getIntent();

        // Selected image
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
                return;
        }
        String image = extras.getString(Constants.IMAGE_HTTP);

        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra(Constants.LIST);
        mList = dw.getItems();

        ImageView imageView = (ImageView) findViewById(R.id.activity_zoom_imageView);


        Picasso.with(ZoomActivity.this).load(image).into(imageView);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!mShowWindow){
            showPopup(ZoomActivity.this,mPoint);
        }
        return false;
    }

    // The method that displays the popup.

    private void showPopup(final Activity context, Point p) {

        mShowWindow = true;
        Rect rectgle= new Rect();
        Window window= this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
        int StatusBarHeight= rectgle.top;

        Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int popupWidth = display.getWidth()- 170;
        int popupHeight = 550;

        // Inflate the popup_layout.xml
        RelativeLayout viewGroup = (RelativeLayout) context
                .findViewById(R.id.popup_relative_layout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(false);

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout,Gravity.CENTER_VERTICAL, p.x , p.y);

        // Getting a reference to Close button, and close the popup when
        // clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowWindow = false;
                popup.dismiss();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return mDetector.onTouchEvent(me);
    }

}
