package lessons.vs.petersonapps.converterlab.presenter;


import java.util.List;

import lessons.vs.petersonapps.converterlab.model.DataModel;
import lessons.vs.petersonapps.converterlab.model.Organizations_;

public class ConverterContract {

    public interface View {

        void setData(DataModel body);
    }

    public interface Presenter{

        void getData();
    }

}
