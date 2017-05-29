package lessons.vs.petersonapps.converterlab.presenter;

import lessons.vs.petersonapps.converterlab.model.DataModel;
import lessons.vs.petersonapps.converterlab.api.ConverterClient;
import lessons.vs.petersonapps.converterlab.api.CurrencyApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vs on 22.05.2017.
 */

public class Presenter implements ConverterContract.Presenter {

    private final ConverterContract.View view;
    private ConverterClient client;

    public Presenter(ConverterContract.View view) {
        this.view = view;
        this.client = CurrencyApp.getRestClient().createService(ConverterClient.class);
    }


    @Override
    public void getData() {
        Call<DataModel> call = client.currencyData();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()){
                    response.body().convertBankData();
                    view.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }
}
