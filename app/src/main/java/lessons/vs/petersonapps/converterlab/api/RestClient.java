package lessons.vs.petersonapps.converterlab.api;

/**
 * Created by vs on 25.05.2017.
 */

public interface RestClient {

    <S> S createService(Class<S> serviceClass);
}
