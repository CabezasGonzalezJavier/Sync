package uk.co.interactive.sync.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.interactive.sync.R;
import uk.co.interactive.sync.Utils.Constants;

public class ZoomActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

            // Get intent data
            Intent i = getIntent();

            // Selected image
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                return;
            }
            String image = extras.getString(Constants.IMAGE_HTTP);

            ImageView imageView = (ImageView) findViewById(R.id.activity_zoom_imageView);
            Picasso.with(ZoomActivity.this).load(image).into(imageView);
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    NavUtils.navigateUpFromSameTask(this);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
