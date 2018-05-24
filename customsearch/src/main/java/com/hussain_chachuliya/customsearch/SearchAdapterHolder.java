package com.hussain_chachuliya.customsearch;


import java.util.HashMap;
import java.util.List;

public class SearchAdapterHolder {

    private static SearchAdapterHolder holder;
    private HashMap<Integer, SearchAdapter> adapters;
    
    private SearchAdapterHolder() {
        this.adapters = new HashMap<>();
    }

    public static SearchAdapterHolder getInstance() {
        if (holder == null) {
            holder = new SearchAdapterHolder();
        }
        return holder;
    }

    public void addAdapter(List<String> data, int requestCode) {
        this.adapters.put(requestCode, new SearchAdapter(data, requestCode));
    }

    public SearchAdapter getAdapter(int requestCode){
        return adapters.get(requestCode);
    }

    public void removeAdapter(int requestCode) {
        this.adapters.remove(requestCode);
    }

    public HashMap<Integer, SearchAdapter> getAdapters() {
        return adapters;
    }
}