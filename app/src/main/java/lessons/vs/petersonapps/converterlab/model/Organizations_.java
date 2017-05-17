package lessons.vs.petersonapps.converterlab.model;

import java.util.Map;

/**
 * Created by vs on 17.05.2017.
 */

public class Organizations_ {

    String id;
    int oldId;
    int orgType;
    boolean branch;
    String tytle;
    String regionId;
    String cityId;
    String phone;
    String address;
    String link;

    Map<String, Currency_> currencies;

    public Organizations_() {
    }

    public Organizations_(String id, int oldId, int orgType, boolean branch, String tytle, String regionId, String cityId, String phone, String address, String link, Map<String, Currency_> currencies) {
        this.id = id;
        this.oldId = oldId;
        this.orgType = orgType;
        this.branch = branch;
        this.tytle = tytle;
        this.regionId = regionId;
        this.cityId = cityId;
        this.phone = phone;
        this.address = address;
        this.link = link;
        this.currencies = currencies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOldId() {
        return oldId;
    }

    public void setOldId(int oldId) {
        this.oldId = oldId;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public boolean isBranch() {
        return branch;
    }

    public void setBranch(boolean branch) {
        this.branch = branch;
    }

    public String getTytle() {
        return tytle;
    }

    public void setTytle(String tytle) {
        this.tytle = tytle;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, Currency_> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency_> currencies) {
        this.currencies = currencies;
    }
}
