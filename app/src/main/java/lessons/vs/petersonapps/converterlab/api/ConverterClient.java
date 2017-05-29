package lessons.vs.petersonapps.converterlab.api;

import lessons.vs.petersonapps.converterlab.model.DataModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vs on 22.05.2017.
 */

public interface ConverterClient {

    @GET("ua/public/currency-cash.json")
    Call<DataModel> currencyData();
}
