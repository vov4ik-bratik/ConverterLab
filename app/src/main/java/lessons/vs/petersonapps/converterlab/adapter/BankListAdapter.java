package lessons.vs.petersonapps.converterlab.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.model.Organizations_;
import lessons.vs.petersonapps.converterlab.view.DetailedActivity;
import lessons.vs.petersonapps.converterlab.view.MainActivity;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.BanksViewHolder> implements Filterable {

    private final List<Organizations_> bankList;

    private CustomFilter customFilter;

    public BankListAdapter(List<Organizations_> bankList) {
        this.bankList = bankList;
    }

    @Override
    public BanksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_card_view, parent, false);

        return new BanksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BanksViewHolder holder, int position) {
        Organizations_ bankItem = bankList.get(position);
        holder.bankNameTV.setText(bankItem.getTytle());
        holder.bankRegionTV.setText(bankItem.getRegionId());
        holder.bankCityTV.setText(bankItem.getCityId());
        holder.bankPhoneTV.setText(bankItem.getPhone());
        holder.bankAddressTV.setText(bankItem.getAddress());
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    @Override
    public Filter getFilter() {
        if(customFilter == null)
            customFilter = new CustomFilter(this, bankList);
        return customFilter;
    }

    private static class CustomFilter extends Filter{

        private final BankListAdapter adapter;

        private final List<Organizations_> originalList;

        private final List<Organizations_> filteredList;

        private CustomFilter(BankListAdapter adapter, List<Organizations_> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new ArrayList<>(originalList);
            this.filteredList = new ArrayList<>();
        }


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final Organizations_ item : originalList) {
                    if (item.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.bankList.clear();
            adapter.bankList.addAll((ArrayList<Organizations_>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

    public class BanksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView bankCV;
        TextView bankNameTV;
        TextView bankRegionTV;
        TextView bankCityTV;
        TextView bankPhoneTV;
        TextView bankAddressTV;

        ImageButton linkIB;
        ImageButton locationIB;
        ImageButton phoneIB;
        ImageButton moreIB;

        boolean[] buttonFlagsArr = new boolean[4];


        public BanksViewHolder(View itemView) {

            super(itemView);

            bankCV = bind(R.id.bank_cv);

            bankNameTV = bind(R.id.bank_name_tv);
            bankRegionTV = bind(R.id.bank_region);
            bankCityTV = bind(R.id.bank_city);
            bankPhoneTV = bind(R.id.bank_phone);
            bankAddressTV = bind(R.id.bank_adress);

            linkIB = bind(R.id.link_button);
            locationIB = bind(R.id.location_button);
            phoneIB = bind(R.id.phone_button);
            moreIB = bind(R.id.more_button);

            linkIB.setOnClickListener(this);
            locationIB.setOnClickListener(this);
            phoneIB.setOnClickListener(this);
            moreIB.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.link_button:
                    onLInkButtonPressed(v);
                    break;
                case R.id.location_button:
                    onLocationButtonPressed(v);
                    break;
                case R.id.phone_button:
                    onPhoneButtonPressed(v);
                    break;
                case R.id.more_button:
                    onMoreButtonPressed(v);
                    break;
            }

            setButtonImageOnClick();

        }

        private void onLInkButtonPressed(View view) {

            setImageButtonPressedFlag(0);

            Toast.makeText(view.getContext(),
                    "link button on pressed",
                    Toast.LENGTH_SHORT).show();
        }

        private void onLocationButtonPressed(View view) {

            setImageButtonPressedFlag(1);

            Toast.makeText(view.getContext(),
                    "location button pressed",
                    Toast.LENGTH_SHORT).show();
        }

        private void onPhoneButtonPressed(View view) {

            setImageButtonPressedFlag(2);

            Toast.makeText(view.getContext(),
                    "phone button pressed",
                    Toast.LENGTH_SHORT).show();
        }

        private void onMoreButtonPressed(View view) {
            setImageButtonPressedFlag(3);
            openDetailedActivity(view, getAdapterPosition());
        }

        private void setImageButtonPressedFlag(int position) {

            for (int i = 0; i < buttonFlagsArr.length; i++) {
                buttonFlagsArr[i] = false;

            }
            buttonFlagsArr[position] = true;
        }

        private void setButtonImageOnClick() {
            for (int i = 0; i < buttonFlagsArr.length; i++) {

                if (buttonFlagsArr[i]) {

                    switch (i) {
                        case 0:
                            linkIB.setImageResource(R.drawable.ic_link_accent);
                            locationIB.setImageResource(R.drawable.ic_location);
                            phoneIB.setImageResource(R.drawable.ic_phone);
                            moreIB.setImageResource(R.drawable.ic_more_hori);
                            break;
                        case 1:
                            linkIB.setImageResource(R.drawable.ic_link);
                            locationIB.setImageResource(R.drawable.ic_location_accent);
                            phoneIB.setImageResource(R.drawable.ic_phone);
                            moreIB.setImageResource(R.drawable.ic_more_hori);
                            break;
                        case 2:
                            linkIB.setImageResource(R.drawable.ic_link);
                            locationIB.setImageResource(R.drawable.ic_location);
                            phoneIB.setImageResource(R.drawable.ic_phone_accent);
                            moreIB.setImageResource(R.drawable.ic_more_hori);
                            break;
                        case 3:
                            linkIB.setImageResource(R.drawable.ic_link);
                            locationIB.setImageResource(R.drawable.ic_location);
                            phoneIB.setImageResource(R.drawable.ic_phone);
                            moreIB.setImageResource(R.drawable.ic_more_hori_accent);
                            break;
                    }
                    break;
                }
            }
        }

        @SuppressWarnings("unchecked")
        private <T extends View> T bind(@IdRes int resId) {
            return ((T) itemView.findViewById(resId));
        }
    }

    private void openDetailedActivity(View view, int pos) {
        Intent intent = new Intent(view.getContext(), DetailedActivity.class);
        intent.putExtra("bankId", bankList.get(pos).getId());
        intent.putExtra("bankDescr", bankList.get(pos).getTytle());
        view.getContext().startActivity(intent);

    }















}