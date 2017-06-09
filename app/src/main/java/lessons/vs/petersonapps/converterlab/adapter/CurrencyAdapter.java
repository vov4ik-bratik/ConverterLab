package lessons.vs.petersonapps.converterlab.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.model.Currency_;

/**
 * Created by vs on 07.06.2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private final HashMap<String, Currency_> currencies;
    private ArrayList<String>keyArrayList = new ArrayList<>();
    private ArrayList<Currency_>valueArrayList = new ArrayList<>();

    public CurrencyAdapter(HashMap<String, Currency_> incomeCurrencies) {
        this.currencies = incomeCurrencies;

        Iterator<Map.Entry<String, Currency_>> itr = currencies.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String, Currency_> entry = itr.next();
            keyArrayList.add(entry.getKey());
            valueArrayList.add(entry.getValue());
        }
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_list_item, parent, false);
        return new CurrencyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.currencyTitle.setText(keyArrayList.get(position));
        holder.ask.setText(valueArrayList.get(position).getAsk());
        holder.bid.setText(valueArrayList.get(position).getBid());

        if(valueArrayList.get(position).getAskChangeFlag() < 0)
            holder.askChange.setImageResource(R.drawable.ic_arrow_drop_down_accent);
        else
            holder.askChange.setImageResource(R.drawable.ic_arrow_drop_up);

        if(valueArrayList.get(position).getBidChangeFlag() < 0)
            holder.bidChange.setImageResource(R.drawable.ic_arrow_drop_down_accent);
        else
            holder.bidChange.setImageResource(R.drawable.ic_arrow_drop_up);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder{

        TextView currencyTitle;
        ImageView bidChange;
        ImageView askChange;
        TextView ask;
        TextView bid;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            currencyTitle = bind(R.id.currency_description);
            bidChange = bind(R.id.bid_changes);
            askChange = bind(R.id.ask_changes);
            ask = bind(R.id.ask_value);
            bid = bind(R.id.bid_value);
        }


        @SuppressWarnings("unchecked")
        private <T extends View> T bind(@IdRes int resId) {
            return ((T) itemView.findViewById(resId));
        }
    }
}
