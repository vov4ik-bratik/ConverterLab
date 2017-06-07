package lessons.vs.petersonapps.converterlab.view;

import android.support.annotation.IdRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.adapter.BankListAdapter;
import lessons.vs.petersonapps.converterlab.database.DataBaseManager;
import lessons.vs.petersonapps.converterlab.model.DataModel;
import lessons.vs.petersonapps.converterlab.model.Organizations_;
import lessons.vs.petersonapps.converterlab.presenter.ConverterContract;
import lessons.vs.petersonapps.converterlab.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements ConverterContract.View, SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView bankListRV;
    private BankListAdapter bankListAdapter;
    private List<Organizations_> bankList = new ArrayList<>();
    private ConverterContract.Presenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) savedInstanceState.getBoolean("h");
        setContentView(R.layout.activity_main);

        bankListRV = bind(R.id.bank_list);

        swipeRefreshInit();

        presenter = new Presenter(this);
        presenter.getData();

        //// TODO: 07.06.2017 add asynktask and service
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bankListAdapter = new BankListAdapter(bankList);

        bankListRV.setAdapter(bankListAdapter);
        bankListRV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void swipeRefreshInit() {
        swipeRefreshLayout = bind(R.id.srMain);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_menu_button));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bankListAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bankListAdapter.getFilter().filter(newText);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("h",false);
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T bind(@IdRes int resId, View view) {
        return ((T) view.findViewById(resId));
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T bind(@IdRes int resId) {
        return ((T) findViewById(resId));
    }

    @Override
    public void setData(DataModel body) {
        DataBaseManager dbManager = DataBaseManager.getInstance(this);
        dbManager.open();
        dbManager.addAllDataToDB(body);
        bankList.addAll(dbManager.getAllOrganizations());
        dbManager.close();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //// TODO: 07.06.2017 add update/get from DB data logic with service
                presenter.getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

















}
