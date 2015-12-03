package es.framework.es.framework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import es.framework.es.framework.entities.Forecast;


/**
 * Created by 02481552 on 11.10.2015.
 */
public class ForecastDataSource {
    private SQLiteDatabase mDatabase;
    private ForecastHelper mForecastHelper;
    private Context mContext;

   public ForecastDataSource(Context context){
       mContext=context;
       mForecastHelper =new ForecastHelper(mContext );
   }
    public void open(){
        mForecastHelper.getWritableDatabase();
    }

    public void close(){
        mForecastHelper.close();
    }
    public void insert(Forecast forecast){
        mDatabase.beginTransaction();
        try {
            for(Forecast.HourlyForecast.HourData hour:forecast.hourly.data){
            ContentValues values = new ContentValues();
            values.put(ForecastHelper.COLUMN_TEMPERATURE, hour.temperature);
            mDatabase.insert(ForecastHelper.TABLE_TEMPERATURES, null,values );
        }
        }
        finally {
            mDatabase.setTransactionSuccessful();
        }
    }

    public Cursor select(){
        Cursor cursor=mDatabase.query(ForecastHelper.TABLE_TEMPERATURES,
                new String[]{ForecastHelper.COLUMN_TEMPERATURE},//column names
                null,//where clause
                null,//where parameters
                null,//group by
                null,//having
                null//order by
                );
        return  cursor;
    }

    public Cursor selectTempGreaterThan(String minTemp){
        String whereClause=ForecastHelper.COLUMN_TEMPERATURE+">?";
        Cursor cursor=mDatabase.query(ForecastHelper.TABLE_TEMPERATURES,
                new String[]{ForecastHelper.COLUMN_TEMPERATURE},//column names
                whereClause,//where clause
                new String[]{minTemp},//where parameters
                null,//group by
                null,//having
                null//order by
        );
        return  cursor;
    }
}
