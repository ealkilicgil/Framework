package es.framework.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import es.framework.R;
import es.framework.db.ForecastDataSource;
import es.framework.services.ForecastService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    protected ForecastDataSource mDataSource;

    protected Button mInsertButton;
    protected Button mSelectButton;
    protected Button mUpdateButton;
    protected Button mDeleteButton;
    protected Button showAppsButton;
    protected Button textToSpeechButton;

    protected double[] mTemperatures;
    protected TextView mHighTextView;
    protected TextView mLowTextView;

    protected long mHighTemp;
    protected long mLowTemp;

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        //bundle.putInt("score", score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        //score= bundle.getInt("score");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:    Toast.makeText(getApplicationContext(), "ORIENTATION_PORTRAIT", Toast.LENGTH_SHORT).show();
            case Configuration.ORIENTATION_LANDSCAPE:   Toast.makeText(getApplicationContext(),"ORIENTATION_LANDSCAPE", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Codes to add custom ActionBar
        LinearLayout actionLayout=(LinearLayout)findViewById(R.id.customTaskbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar);

             String[] my_string=getResources().getStringArray(R.array.my_array);
        mDataSource = new ForecastDataSource(MainActivity.this);

        mHighTextView = (TextView)findViewById(R.id.textView2);
        mLowTextView = (TextView)findViewById(R.id.textView3);

        mInsertButton = (Button)findViewById(R.id.insertButton);
        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadForecastData();
            }
        });

        mSelectButton = (Button)findViewById(R.id.selectButton);
        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, ForecastActivity.class));
            }
        });

        mUpdateButton = (Button)findViewById(R.id.updateButton);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=getLayoutInflater();
                View layout= inflater.inflate(R.layout.activity_popup,(ViewGroup)findViewById(R.id.toast_layout));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setView(layout);
                toast.show();
                //    mDataSource.updateTemperature(100);
            }
        });

        mDeleteButton = (Button)findViewById(R.id.deleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     mDataSource.deleteAll();
            }
        });

        showAppsButton= (Button)findViewById(R.id.showAppsButton);
        showAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("market:search?q=" + "ealkilicgil");//getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Uri uri = Uri.parse("https://play.google.com/store/search?q=" + "ealkilicgil");//getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            }
        });

        textToSpeechButton = (Button)findViewById(R.id.textToSpeechButton);
        textToSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextToVoiceActivity.class);
                startActivity(intent);
            }
        });

        TextView photoCredit = (TextView)findViewById(R.id.textView);
        photoCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("market:details?id=" + "com.teamlava.castlestory");//getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + "com.teamlava.castlestory");//getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            }
        });

    }


    protected void loadForecastData()
    {
        ForecastService service=new ForecastService();
        mDataSource.insert(service.loadForecastData());
    }

    @Override
    public void onClick(View v)  {
        switch (v.getId()) {
            case R.id.insertButton: Log.v("onClick", v.getTag().toString());break;
            case R.id.deleteButton: Log.v("onClick", v.getTag().toString());break;
        }
    }


}
