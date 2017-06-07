package lessons.vs.petersonapps.converterlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.database.DataBaseManager;
import lessons.vs.petersonapps.converterlab.model.Organizations_;

public class DetailedActivity extends AppCompatActivity {

    TextView bankNameTV;
    TextView bankRegionTV;
    TextView bankCityTV;
    TextView bankPhoneTV;
    TextView bankAddressTV;

    Organizations_ organization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        findtViews();
        setTVContent();


    }

    private void setTVContent() {
        Intent intent = getIntent();
        getOrganization(intent.getStringExtra("bankId"));

        bankNameTV.setText(organization.getTytle());
        bankRegionTV.setText("Регіон: " + organization.getRegionId());
        bankCityTV.setText("Місто: " + organization.getCityId());
        bankPhoneTV.setText("Тел. " + organization.getPhone());
        bankAddressTV.setText("Адреса: " + organization.getAddress());

    }

    private void getOrganization(String bankId) {
        DataBaseManager dbManager = DataBaseManager.getInstance(this);
        dbManager.open();
        organization = dbManager.getOrganization(bankId);
        dbManager.close();
    }

    private void findtViews() {
        bankNameTV = bind(R.id.detailed_bank_name_tv);
        bankRegionTV = bind(R.id.detailed_bank_region);
        bankCityTV = bind(R.id.detailed_bank_city);
        bankPhoneTV = bind(R.id.detailed_bank_phone);
        bankAddressTV = bind(R.id.detailed_bank_adress);
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T bind(@IdRes int resId) {
        return ((T) findViewById(resId));
    }

}
