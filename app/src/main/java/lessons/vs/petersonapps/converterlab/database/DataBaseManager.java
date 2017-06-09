package lessons.vs.petersonapps.converterlab.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lessons.vs.petersonapps.converterlab.model.Currency_;
import lessons.vs.petersonapps.converterlab.model.DataModel;
import lessons.vs.petersonapps.converterlab.model.Organizations_;

public class DataBaseManager {

    private static DataBaseManager INSTANCE;

    private static final String TAG = "DataBaseManager";

    private DataBaseHelper dbHelper;
    private SQLiteDatabase dataBase;

    private DataBaseManager(final Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public static synchronized DataBaseManager getInstance(final Context context) {
        if (INSTANCE == null)
            INSTANCE = new DataBaseManager(context);
        return INSTANCE;

    }

    public final void open() {
        if (!isOpen())
            dataBase = dbHelper.getWritableDatabase();
    }

    public final boolean isOpen() {
        boolean isOpen = dataBase != null && dataBase.isOpen();
        return isOpen;
    }

    public final void close() {
        dbHelper.close();
    }

    public final void addOrganizations(List<Organizations_> organizations) {
        dataBase.delete(DataBaseContract.Organisation.TABLE_NAME, null, null);

        for (Organizations_ org : organizations) {
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Organisation.COLUMN_ID, org.getId());
            values.put(DataBaseContract.Organisation.COLUMN_PHONE, org.getPhone());
            values.put(DataBaseContract.Organisation.COLUMN_TITLE, org.getTytle());
            values.put(DataBaseContract.Organisation.COLUMN_ADDRESS, org.getAddress());
            values.put(DataBaseContract.Organisation.COLUMN_CITY_ID, org.getCityId());
            values.put(DataBaseContract.Organisation.COLUMN_LINK, org.getLink());
            values.put(DataBaseContract.Organisation.COLUMN_REGION_ID, org.getRegionId());

            dataBase.insert(DataBaseContract.Organisation.TABLE_NAME, null, values);
        }
    }

    public final void addExchangeRates(List<Organizations_> organizations) {

        ArrayList<Organizations_> orgFromDB = getAllOrganizations();

        dataBase.delete(DataBaseContract.ExchangeRate.TABLE_NAME, null, null);

        for (int i = 0; i < organizations.size(); i++) {
            for (Map.Entry<String, Currency_> entry : organizations.get(i).getCurrencies().entrySet()) {

                ContentValues values = new ContentValues();
                values.put(DataBaseContract.ExchangeRate.COLUMN_ORGANIZATION, organizations.get(i).getId());
                values.put(DataBaseContract.ExchangeRate.COLUMN_NAME_KEY, entry.getKey());
                values.put(DataBaseContract.ExchangeRate.COLUMN_ASK, entry.getValue().getAsk());
                values.put(DataBaseContract.ExchangeRate.COLUMN_BID, entry.getValue().getBid());

                if (!orgFromDB.get(i).getCurrencies().isEmpty()) {
                    for (Map.Entry<String, Currency_> entryFromDB : orgFromDB.get(i).getCurrencies().entrySet()) {
                        if (entry.getKey().equals(entryFromDB.getKey())) {
                            double askDelta = Double.parseDouble(entry.getValue().getAsk()) -
                                    Double.parseDouble(entryFromDB.getValue().getAsk());
                            values.put(DataBaseContract.ExchangeRate.COLUMN_ASK_FLAG, askDelta);

                            double bidDelta = Double.parseDouble(entry.getValue().getBid()) -
                                    Double.parseDouble(entryFromDB.getValue().getBid());
                            values.put(DataBaseContract.ExchangeRate.COLUMN_BID_FLAG, bidDelta);
                        }
                    }
                }
                else{
                    values.put(DataBaseContract.ExchangeRate.COLUMN_ASK_FLAG, 0d);
                    values.put(DataBaseContract.ExchangeRate.COLUMN_BID_FLAG, 0d);
                }

                dataBase.insert(DataBaseContract.ExchangeRate.TABLE_NAME, null, values);
            }
        }
    }

    public final void addOrganisationTypes(Map<String, String> orgTypes) {
        dataBase.delete(DataBaseContract.OrganisationType.TABLE_NAME, null, null);

        for (Map.Entry<String, String> entry : orgTypes.entrySet()) {
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.OrganisationType.COLUMN_ID, entry.getKey());
            values.put(DataBaseContract.OrganisationType.COLUMN_VALUES, entry.getValue());

            dataBase.insert(DataBaseContract.OrganisationType.TABLE_NAME, null, values);
        }

    }

    public final void addCurrencies(Map<String, String> currencies) {

        dataBase.delete(DataBaseContract.Currencies.TABLE_NAME, null, null);

        for (Map.Entry<String, String> entry : currencies.entrySet()) {
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Currencies.COLUMN_ID, entry.getKey());
            values.put(DataBaseContract.Currencies.COLUMN_VALUES, entry.getValue());

            dataBase.insert(DataBaseContract.Currencies.TABLE_NAME, null, values);
        }
    }

    public final void addRegion(Map<String, String> regions) {
        dataBase.delete(DataBaseContract.Region.TABLE_NAME, null, null);

        for (Map.Entry<String, String> entry : regions.entrySet()) {
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.Region.COLUMN_ID, entry.getKey());
            values.put(DataBaseContract.Region.COLUMN_VALUES, entry.getValue());

            dataBase.insert(DataBaseContract.Region.TABLE_NAME, null, values);
        }
    }

    public final void addCity(Map<String, String> city) {
        dataBase.delete(DataBaseContract.City.TABLE_NAME, null, null);

        for (Map.Entry<String, String> entry : city.entrySet()) {
            ContentValues values = new ContentValues();

            values.put(DataBaseContract.City.COLUMN_ID, entry.getKey());
            values.put(DataBaseContract.City.COLUMN_VALUES, entry.getValue());

            dataBase.insert(DataBaseContract.City.TABLE_NAME, null, values);
        }
    }

    public final void addLastUpdateData(DataModel model) {
        dataBase.delete(DataBaseContract.LastUpdateData.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();

        values.put(DataBaseContract.LastUpdateData.COLUMN_VALUES, model.getDate());

        dataBase.insert(DataBaseContract.LastUpdateData.TABLE_NAME, null, values);
    }

    public final ArrayList<Organizations_> getAllOrganizations() {

        ArrayList<Organizations_> orgList = new ArrayList<>();

        Cursor cursor = dataBase.query(DataBaseContract.Organisation.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Organizations_ org = new Organizations_();

            org.setId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_ID)));
            org.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_PHONE)));
            org.setTytle(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_TITLE)));
            org.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_ADDRESS)));
            org.setCityId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_CITY_ID)));
            org.setLink(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_LINK)));
            org.setRegionId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_REGION_ID)));

            orgList.add(org);
        }
        cursor.close();

        for (Organizations_ org : orgList) {
            org.setCurrencies(getAllOrganizationExchangeRate(org.getId()));
        }
        return orgList;
    }

    public final HashMap<String, Currency_> getAllOrganizationExchangeRate(String organizationID) {
        HashMap<String, Currency_> currencies = new HashMap<>();
        String mapKey;
        Cursor cursor = dataBase.query(DataBaseContract.ExchangeRate.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Currency_ mapValue = new Currency_();
            if (cursor.getString(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_ORGANIZATION)).equals(organizationID)) {
                mapKey = cursor.getString(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_NAME_KEY));
                mapValue.setAsk(cursor.getString(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_ASK)));
                mapValue.setBid(cursor.getString(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_BID)));
                mapValue.setAskChangeFlag(cursor.getDouble(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_ASK_FLAG)));
                mapValue.setBidChangeFlag(cursor.getDouble(cursor.getColumnIndex(DataBaseContract.ExchangeRate.COLUMN_BID_FLAG)));

                currencies.put(mapKey, mapValue);
            }
        }

        cursor.close();
        return currencies;
    }

    public final Map<String, String> getAllOrganisationTypes() {
        Map<String, String> orgTypes = new HashMap<>();
        Cursor cursor = dataBase.query(DataBaseContract.OrganisationType.TABLE_NAME, null, null, null, null, null, null);

        String mapKey;
        String mapValue;

        while (cursor.moveToNext()) {
            mapKey = cursor.getString(cursor.getColumnIndex(DataBaseContract.OrganisationType.COLUMN_ID));
            mapValue = cursor.getString(cursor.getColumnIndex(DataBaseContract.OrganisationType.COLUMN_VALUES));

            orgTypes.put(mapKey, mapValue);
        }
        cursor.close();
        return orgTypes;
    }

    public final Map<String, String> getAllCurrencies() {
        Map<String, String> currencies = new HashMap<>();
        Cursor cursor = dataBase.query(DataBaseContract.Currencies.TABLE_NAME, null, null, null, null, null, null);

        String mapKey;
        String mapValue;

        while (cursor.moveToNext()) {
            mapKey = cursor.getString(cursor.getColumnIndex(DataBaseContract.Currencies.COLUMN_ID));
            mapValue = cursor.getString(cursor.getColumnIndex(DataBaseContract.Currencies.COLUMN_VALUES));

            currencies.put(mapKey, mapValue);
        }

        cursor.close();
        return currencies;
    }

    public final Map<String, String> getAllRegions() {
        Map<String, String> region = new HashMap<>();
        Cursor cursor = dataBase.query(DataBaseContract.Region.TABLE_NAME, null, null, null, null, null, null);

        String mapKey;
        String mapValue;

        while (cursor.moveToNext()) {
            mapKey = cursor.getString(cursor.getColumnIndex(DataBaseContract.Region.COLUMN_ID));
            mapValue = cursor.getString(cursor.getColumnIndex(DataBaseContract.Region.COLUMN_VALUES));

            region.put(mapKey, mapValue);
        }

        cursor.close();
        return region;
    }

    public final Map<String, String> getAllCities() {
        Map<String, String> cities = new HashMap<>();
        Cursor cursor = dataBase.query(DataBaseContract.City.TABLE_NAME, null, null, null, null, null, null);

        String mapKey;
        String mapValue;

        while (cursor.moveToNext()) {
            mapKey = cursor.getString(cursor.getColumnIndex(DataBaseContract.City.COLUMN_ID));
            mapValue = cursor.getString(cursor.getColumnIndex(DataBaseContract.City.COLUMN_VALUES));

            cities.put(mapKey, mapValue);
        }

        cursor.close();
        return cities;
    }

    public final String getLastUpdate() {

        Cursor cursor = dataBase.query(DataBaseContract.LastUpdateData.TABLE_NAME, null, null, null, null, null, null);

        String date = cursor.getString(cursor.getColumnIndex(DataBaseContract.LastUpdateData.COLUMN_VALUES));
        cursor.close();
        return date;
    }

    public final Organizations_ getOrganization(String organizationId) {
        Organizations_ org = new Organizations_();
        Cursor cursor = dataBase.query(DataBaseContract.Organisation.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_ID)).equals(organizationId)) {
                org.setId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_ID)));

                org.setTytle(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_TITLE)));
                org.setRegionId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_REGION_ID)));
                org.setCityId(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_CITY_ID)));
                org.setPhone(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_PHONE)));
                org.setAddress(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_ADDRESS)));
                org.setLink(cursor.getString(cursor.getColumnIndex(DataBaseContract.Organisation.COLUMN_LINK)));
                org.setCurrencies(getAllOrganizationExchangeRate(organizationId));
                break;
            }
        }
        cursor.close();
        return org;
    }

    public final void addAllDataToDB(DataModel data) {
        addOrganizations(data.getOrganizations());
        addExchangeRates(data.getOrganizations());
        addOrganisationTypes(data.getOrgTypes());
        addCurrencies(data.getCurrencies());
        addRegion(data.getRegions());
        addCity(data.getCities());
        addLastUpdateData(data);
    }


}
