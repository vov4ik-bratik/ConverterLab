package lessons.vs.petersonapps.converterlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.adapter.CurrencyAdapter;
import lessons.vs.petersonapps.converterlab.database.DataBaseManager;
import lessons.vs.petersonapps.converterlab.model.Organizations_;

public class DetailedActivity extends AppCompatActivity {

    TextView bankNameTV;
    TextView bankRegionTV;
    TextView bankCityTV;
    TextView bankPhoneTV;
    TextView bankAddressTV;

    FloatingActionMenu menuFAM;
    FloatingActionButton buttonCallFAB;
    FloatingActionButton buttonLinkFAB;
    FloatingActionButton buttonMapFAB;


    RecyclerView currencyRV;

    Organizations_ organization;
    CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        findViews();
        setTVContent();

        //TODO implement swipeRefresh
        currencyAdapter = new CurrencyAdapter(organization.getCurrencies());

        currencyRV.setAdapter(currencyAdapter);
        currencyRV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setTVContent() {
        Intent intent = getIntent();
        getOrganization(intent.getStringExtra("bankId"));

        bankNameTV.setText(organization.getTytle());
        bankRegionTV.setText(String.format("%s%s", getString(R.string.region), organization.getRegionId()));
        bankCityTV.setText(String.format("%s%s", getString(R.string.city), organization.getCityId()));
        bankPhoneTV.setText(String.format("%s%s", getString(R.string.Phone), organization.getPhone()));
        bankAddressTV.setText(String.format("%s%s", getString(R.string.Address), organization.getAddress()));
    }

    private void getOrganization(String bankId) {
        DataBaseManager dbManager = DataBaseManager.getInstance(this);
        dbManager.open();
        organization = dbManager.getOrganization(bankId);
        dbManager.close();
    }

    private void findViews() {
        bankNameTV = bind(R.id.detailed_bank_name_tv);
        bankRegionTV = bind(R.id.detailed_bank_region);
        bankCityTV = bind(R.id.detailed_bank_city);
        bankPhoneTV = bind(R.id.detailed_bank_phone);
        bankAddressTV = bind(R.id.detailed_bank_adress);
        currencyRV = bind(R.id.currency_list);

        menuFAM = bind(R.id.FAM);
        buttonCallFAB = bind(R.id.callFAB);
        buttonLinkFAB = bind(R.id.linkFAB);
        buttonMapFAB = bind(R.id.mapFAB);
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T bind(@IdRes int resId) {
        return ((T) findViewById(resId));
    }

}
