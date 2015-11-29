package es.framework.es.framework.ui;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import es.framework.R;
import es.framework.es.framework.db.ForecastDataSource;
import es.framework.es.framework.db.ForecastHelper;


public class ForecastActivity extends ListActivity {

    protected ArrayList<BigDecimal> mTemperatures;
    ForecastDataSource mDatasource;
    EditText txtMinTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        mDatasource=new ForecastDataSource(ForecastActivity.this);

        txtMinTemperature= (EditText) getActionBar().getCustomView().findViewById(R.id.txtMinTemperature);
        txtMinTemperature.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                filterTemperatures(txtMinTemperature.getText().toString());
                return false;
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        mDatasource.close();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mDatasource.open();
    }

    private void filterTemperatures(String minTemp) {
        Cursor cursor=mDatasource.selectTempGreaterThan(minTemp);
        updateList(cursor);
    }

    protected void updateList(Cursor cursor)
    {
        mTemperatures.clear();

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            double temperature=cursor.getDouble(cursor.getColumnIndex(ForecastHelper.COLUMN_TEMPERATURE));
            mTemperatures.add(new BigDecimal(temperature, MathContext.DECIMAL32));
            cursor.moveToNext();
        }
        ArrayAdapter<BigDecimal> adapter=new ArrayAdapter<BigDecimal>(ForecastActivity.this,android.R.layout.simple_list_item_1,mTemperatures);
        setListAdapter(adapter);

    }
}
