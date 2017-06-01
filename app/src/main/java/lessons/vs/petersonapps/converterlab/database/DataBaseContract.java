package lessons.vs.petersonapps.converterlab.database;

/**
 * Created by vs on 29.05.2017.
 */

public final class DataBaseContract {

    public static final class Organisation {
        public static final String TABLE_NAME = "organization_data";

        public static final String COLUMN_ID = "orgId";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CITY_ID = "cityId";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_REGION_ID = "regionId";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                        COLUMN_PHONE + " TEXT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_CITY_ID + " TEXT, " +
                        COLUMN_LINK + " TEXT, " +
                        COLUMN_REGION_ID + " TEXT " + ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE " + TABLE_NAME;

    }

    public static final class ExchangeRate {
        public static final String TABLE_NAME = "exchange_rate";

        public static final String COLUMN_KEY_ID = "id";
        public static final String COLUMN_ORGANIZATION = "exchangeRateId";
        public static final String COLUMN_NAME_KEY = "name_key";
        public static final String COLUMN_ASK = "ask";
        public static final String COLUMN_BID = "bid";
        public static final String COLUMN_ASK_FLAG = "ask_flag";
        public static final String COLUMN_BID_FLAG = "bid_flag";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_KEY_ID + " integer primary key autoincrement, " +
                        COLUMN_ORGANIZATION + " TEXT " +
                        COLUMN_NAME_KEY + " TEXT " +
                        COLUMN_ASK + " TEXT " +
                        COLUMN_ASK_FLAG + " REAL " +
                        COLUMN_BID + " TEXT " +
                        COLUMN_BID_FLAG + " REAL, " +
                        " FOREIGN KEY (" + COLUMN_ORGANIZATION + ") REFERENCES " +
                        Organisation.TABLE_NAME + "(" + Organisation.COLUMN_ID + ")" +
                        ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static final class OrganisationType {
        public static final String TABLE_NAME = "organization_type";

        public static final String COLUMN_ID = "orgTypeId";
        public static final String COLUMN_VALUES = "val_orgtype";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                        COLUMN_VALUES + " INTEGER " +
                        ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static final class Currencies {
        public static final String TABLE_NAME = "currencies";

        public static final String COLUMN_ID = "currencyId";
        public static final String COLUMN_VALUES = "val_currency";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                        COLUMN_VALUES + " TEXT " +
                        ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Region {
        public static final String TABLE_NAME = "regions";

        public static final String COLUMN_ID = "regionId";
        public static final String COLUMN_VALUES = "val_region";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                        COLUMN_VALUES + " TEXT " +
                        ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class City {
        public static final String TABLE_NAME = "cities";

        public static final String COLUMN_ID = "cityId";
        public static final String COLUMN_VALUES = "val_city";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                        COLUMN_VALUES + " TEXT " +
                        ");";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class LastUpdateData {
        public static final String TABLE_NAME = "lastUpdateData";

        public static final String COLUMN_VALUES = "val_data";

        public static final String CREATE_TABLE_SQL =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_VALUES + " TEXT);";

        public static final String DELETE_TABLE_SQL =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
