package lessons.vs.petersonapps.converterlab.view;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.model.DataModel;
import lessons.vs.petersonapps.converterlab.model.Organizations_;

public class MainActivity extends AppCompatActivity {

    RecyclerView bankListRV;
    BankListAdapter bankListAdapter;
    List<Organizations_> bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) savedInstanceState.getBoolean("h");
        setContentView(R.layout.activity_main);

        bankListRV = bind(R.id.bank_list);

        // TODO:  initialize bankList

        bankListAdapter = new BankListAdapter(bankList);

        bankListRV.setAdapter(bankListAdapter);
        bankListRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search_menu_button);

        SearchView searchView = (SearchView) item.getActionView();

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
}
