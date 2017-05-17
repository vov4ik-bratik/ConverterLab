package lessons.vs.petersonapps.converterlab.model;

/**
 * Created by vs on 17.05.2017.
 */

class Currency_ {
    String ask;
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
