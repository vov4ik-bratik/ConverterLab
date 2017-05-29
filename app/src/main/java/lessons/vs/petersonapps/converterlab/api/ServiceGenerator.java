package lessons.vs.petersonapps.converterlab.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vs on 25.05.2017.
 */

class ServiceGenerator implements RestClient {

    private static final String BASE_URL = "http://resources.finance.ua/";

    private Retrofit retrofit;

    public ServiceGenerator() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //if (BuildConfig.LOG_MODE)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new MyIntceptor())
                .build();

        this.retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    class MyIntceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Log.d("myTag", "intercept: ");
            Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
            return chain.proceed(request);
        }
    }
}
