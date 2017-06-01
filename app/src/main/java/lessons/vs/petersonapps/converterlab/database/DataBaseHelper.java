package lessons.vs.petersonapps.converterlab.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vs on 29.05.2017.
 */

public final class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "converterLabDB.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseContract.OrganisationType.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.Currencies.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.Region.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.City.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.LastUpdateData.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.Organisation.CREATE_TABLE_SQL);
        db.execSQL(DataBaseContract.ExchangeRate.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.Organisation.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.ExchangeRate.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.OrganisationType.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.Currencies.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.Region.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.City.DELETE_TABLE_SQL);
        db.execSQL(DataBaseContract.LastUpdateData.DELETE_TABLE_SQL);

        onCreate(db);
    }
}
