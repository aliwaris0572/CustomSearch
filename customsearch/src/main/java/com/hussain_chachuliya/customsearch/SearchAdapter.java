package com.hussain_chachuliya.customsearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder> implements Filterable {
    private List<String> data;
    private List<String> allData;
    private int requestCode;

    private ICustomSearch customSearchListener;

    public SearchAdapter(List<String> data, int requestCode) {
        this.data = data;
        // Used for restoring all items.
        this.allData = data;
        this.requestCode = requestCode;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_row, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtRow.setText(data.get(position));
    }

    private Filter fRecords;

    //return the filter class object
    @Override
    public Filter getFilter() {
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }

    //filter class
    private class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            //Implement filter logic
            // if editText is null return the actual list
            if (constraint == null || constraint.length() == 0) {
                //No need for filter
                results.values = allData;
                results.count = allData.size();

            } else {
                //Need Filter
                // it matches the text  entered in the editText and set the data in adapter list
                ArrayList<String> fRecords = new ArrayList<>();

                for (String s : allData) {
                    if (s.toUpperCase().trim().contains(constraint.toString().toUpperCase().trim())) {
                        fRecords.add(s);
                    }
                }
                results.values = fRecords;
                results.count = fRecords.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //it set the data from filter to adapter list and refresh the recyclerView adapter
            data = (List<String>) results.values;
            notifyDataSetChanged();
            customSearchListener.searchItemsCount(data.size());
        }
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtRow;

        viewHolder(final View itemView) {
            super(itemView);
            txtRow = itemView.findViewById(R.id.list_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customSearchListener.getSelectedText(txtRow.getText().toString());
                }
            });
        }
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setCustomSearchListener(ICustomSearch iCustomSearch) {
        this.customSearchListener = iCustomSearch;
    }

    interface ICustomSearch {
        void getSelectedText(String content);

        void searchItemsCount(int count);
    }

}

