package lessons.vs.petersonapps.converterlab.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vs on 17.05.2017.
 */

public class Currency_ {
    @SerializedName("ask")
    String ask;
    @SerializedName("bid")
    String bid;

    Double askChangeFlag;
    Double bidChangeFlag;

    public Currency_() {
        this.ask = "0";
        this.bid = "0";
        this.askChangeFlag = 0d;
        this.bidChangeFlag = 0d;
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

    public Double getAskChangeFlag() {
        return askChangeFlag;
    }

    public void setAskChangeFlag(Double askChangeFlag) {
        this.askChangeFlag = askChangeFlag;
    }

    public Double getBidChangeFlag() {
        return bidChangeFlag;
    }

    public void setBidChangeFlag(Double bidChangeFlag) {
        this.bidChangeFlag = bidChangeFlag;
    }
}
