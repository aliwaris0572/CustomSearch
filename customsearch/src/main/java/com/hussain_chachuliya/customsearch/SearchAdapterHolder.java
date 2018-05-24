package com.hussain_chachuliya.customsearch;


import java.util.HashMap;
import java.util.List;

public class SearchAdapterHolder {

    private static SearchAdapterHolder holder;
    private HashMap<Integer, SearchAdapter> searchAdapters = null;

    private SearchAdapterHolder() {
        searchAdapters = new HashMap<>();
    }

    public static SearchAdapterHolder getInstance() {
        if (holder == null) {
            holder = new SearchAdapterHolder();
        }
        return holder;
    }

    public void addAdapter(List<String> data, int requestCode) {
        this.searchAdapters.put(requestCode, new SearchAdapter(data, requestCode));
    }

    public SearchAdapter getAdapter(int requestCode){
        return searchAdapters.get(requestCode);
    }

    public void removeAdapter(int requestCode) {
        this.searchAdapters.remove(requestCode);
    }

    public HashMap<Integer, SearchAdapter> getAdapters() {
        return searchAdapters;
    }
}