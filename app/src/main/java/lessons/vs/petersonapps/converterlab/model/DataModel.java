package lessons.vs.petersonapps.converterlab.model;

import java.util.List;
import java.util.Map;

/**
 * Created by vs on 11.05.2017.
 */

public class DataModel {

    private String sourceId;
    private String date;

    List<Organizations_>organizations;

    Map<String, String> orgTypes;
    Map<String, String> currencies;
    Map<String, String> regions;
    Map<String, String> cities;


    public DataModel() {
    }

    public DataModel(String sourceId, String date, List<Organizations_> organizations, Map<String, String> orgTypes, Map<String, String> currencies, Map<String, String> regions, Map<String, String> cities) {
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

    public void setOrganizations(List<Organizations_> organizations) {
        this.organizations = organizations;
    }

    public Map<String, String> getOrgTypes() {
        return orgTypes;
    }

    public void setOrgTypes(Map<String, String> orgTypes) {
        this.orgTypes = orgTypes;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, String> currencies) {
        this.currencies = currencies;
    }

    public Map<String, String> getRegions() {
        return regions;
    }

    public void setRegions(Map<String, String> regions) {
        this.regions = regions;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return null;
    }

}
