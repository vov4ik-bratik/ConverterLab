package lessons.vs.petersonapps.converterlab.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vs on 17.05.2017.
 */

class Currency_ {
    @SerializedName("ask")
    String ask;
    @SerializedName("bid")
    String bid;

    public Currency_() {
    }

    public Currency_(String ask, String bid) {
        this.ask = ask;
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

}
