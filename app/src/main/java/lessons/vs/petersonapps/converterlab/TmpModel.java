package lessons.vs.petersonapps.converterlab;

/**
 * Created by vs on 11.05.2017.
 */

public class TmpModel {

    private String bankName;
    private String bankRegion;
    private String bankCity;
    private String bankPhone;
    private String bankAdress;

    public TmpModel(String bankName, String bankRegion, String bankCity, String bankPhone, String bankAdress) {
        this.bankName = bankName;
        this.bankRegion = bankRegion;
        this.bankCity = bankCity;
        this.bankPhone = bankPhone;
        this.bankAdress = bankAdress;
    }

    public TmpModel() {
    }

    @Override
    public String toString() {
        return bankName + bankRegion + bankCity;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankRegion() {
        return bankRegion;
    }

    public void setBankRegion(String bankRegion) {
        this.bankRegion = bankRegion;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public String getBankAdress() {
        return bankAdress;
    }

    public void setBankAdress(String bankAdress) {
        this.bankAdress = bankAdress;
    }
}
