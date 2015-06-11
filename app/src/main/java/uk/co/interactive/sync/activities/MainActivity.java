package uk.co.interactive.sync.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.interactive.sync.adapters.ImageAdapter;
import uk.co.interactive.sync.models.DataWrapper;
import uk.co.interactive.sync.models.Feed;
import uk.co.interactive.sync.R;
import uk.co.interactive.sync.models.Item;
import uk.co.interactive.sync.utils.Constants;
import uk.co.interactive.sync.utils.Utils;
import uk.co.interactive.sync.views.NonScrollGridView;
import uk.co.interactive.sync.webservice.Client;


public class MainActivity extends Activity implements TextView.OnEditorActionListener, TextWatcher {

    private List<String> mList;
    private NonScrollGridView mGridview;
    private boolean mFinishScroll = false;
    private ImageAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private ListView mListView;
    private Feed mFeed;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mList = new ArrayList<String>();

        mEditText = (EditText) findViewById(R.id.activity_main_editText);
        mEditText.setOnEditorActionListener(this);
        mEditText.addTextChangedListener(this);



        mGridview = (NonScrollGridView) findViewById(R.id.activity_main_gridview);
        mGridview.setNumColumns(3);

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (!mList.get(position).toString().equals("s")) {
                    // Send intent to ZoomActivity
                    Intent intent = new Intent(getApplicationContext(), ZoomActivity.class);
                    // Pass image index
                    intent.putExtra(Constants.IMAGE_HTTP, position);
                    intent.putExtra(Constants.LIST, new DataWrapper((ArrayList<Item>) mFeed.getItems()));
                    startActivity(intent);
                }
            }
        });

        emptyGridView();
    }

    public void emptyGridView(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 21; i++) {
            mList.add("s");
        }
        mAdapter = new ImageAdapter(MainActivity.this, mList);
        mGridview.setAdapter(mAdapter);

    }

    public void getInfo() {
        if (Utils.isOnline(MainActivity.this)) {
            getFeed();
        }else{
            Toast.makeText(MainActivity.this, R.string.check_internet, Toast.LENGTH_LONG).show();
        }
    }

    public void getList() {
        mList = new ArrayList<String>();
        for (int i = 0; i < mFeed.getItems().size(); i++) {
            mList.add(mFeed.getItems().get(i).getMedia().getM());
        }
        buildList();
    }

    public void buildList() {

            mAdapter = new ImageAdapter(MainActivity.this, mList);
            mGridview.setAdapter(mAdapter);

    }

    public void getFeed() {
        Callback<Feed> callback = new Callback<Feed>() {
            @Override
            public void success(Feed feed, Response response) {
                mFeed = feed;
                getList();

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(MainActivity.this,R.string.data_failed,Toast.LENGTH_LONG).show();
            }
        };
        Client.initRestAdapter().getQuestions(Constants.NOJSONCALLBACK, Constants.FORMAT, mEditText.getText().toString() , callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_SEARCH)) {
            String string = mEditText.getText().toString();
            Log.v("gg",string);
            if(mEditText.getText().equals("")){
                emptyGridView();
            }else {
            getInfo();
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        MainActivity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);}
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string = mEditText.getText().toString();
        Log.v("gg",string);
        if(mEditText.getText().toString().equals("")){
            emptyGridView();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
