package lessons.vs.petersonapps.converterlab.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vs on 11.05.2017.
 */

public class DataModel {

    @SerializedName("sourceId")
    private String sourceId;
    @SerializedName("date")
    private String date;

    @SerializedName("organizations")
    private ArrayList<Organizations_> organizations;

    @SerializedName("orgTypes")
    HashMap<String, String> orgTypes;

    @SerializedName("currencies")
    HashMap<String, String> currencies;

    @SerializedName("regions")
    HashMap<String, String> regions;

    @SerializedName("cities")
    HashMap<String, String> cities;


    public DataModel() {
    }

    public DataModel(String sourceId, String date, ArrayList<Organizations_> organizations, HashMap<String, String> orgTypes, HashMap<String, String> currencies, HashMap<String, String> regions, HashMap<String, String> cities) {
        this.sourceId = sourceId;
        this.date = date;
        this.organizations = organizations;
        this.orgTypes = orgTypes;
        this.currencies = currencies;
        this.regions = regions;
        this.cities = cities;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Organizations_> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organizations_> organizations) {
        this.organizations = organizations;
    }

    public Map<String, String> getOrgTypes() {
        return orgTypes;
    }

    public void setOrgTypes(HashMap<String, String> orgTypes) {
        this.orgTypes = orgTypes;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, String> currencies) {
        this.currencies = currencies;
    }

    public Map<String, String> getRegions() {
        return regions;
    }

    public void setRegions(HashMap<String, String> regions) {
        this.regions = regions;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(HashMap<String, String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return null;
    }


    public void convertBankData() {

        for (int i = 0; i < organizations.size(); i++) {
            for (Map.Entry<String, String> entry:regions.entrySet() ) {
                if(organizations.get(i).getRegionId().equalsIgnoreCase(entry.getKey())){
                    organizations.get(i).setRegionId(entry.getValue());
                }
            }

            for (Map.Entry<String, String> entry:cities.entrySet() ) {
                if(organizations.get(i).getCityId().equalsIgnoreCase(entry.getKey())){
                    organizations.get(i).setCityId(entry.getValue());
                }
            }

        }
    }
}
