package com.hussain_chachuliya.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hussain_chachuliya.customsearch.CustomSearch;
import com.hussain_chachuliya.customsearch.SearchAdapterHolder;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class DemoFragment extends Fragment {
    EditText etFrag;
    private final int REQ_CODE = 55;
    SearchAdapterHolder holder;

    public DemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        holder = new SearchAdapterHolder();
        holder.addAdapter(getListOfStrings(), REQ_CODE);

        etFrag = v.findViewById(R.id.etFrag);
        etFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSearch.start(DemoFragment.this, REQ_CODE, holder);
            }
        });

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            etFrag.setText(data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));
        }
    }

    @NonNull
    private List<String> getListOfStrings() {
        List<String> data = new ArrayList<>();
        data.add("aaa");
        data.add("baa");
        data.add("cbb");
        data.add("dbb");
        data.add("ecc");
        data.add("fcc");
        data.add("gdd");
        data.add("hdd");
        data.add("idd");
        data.add("jdd");
        data.add("kee");
        data.add("lee");
        data.add("mee");
        data.add("nee");
        data.add("oee");
        data.add("pff");
        data.add("qff");
        data.add("rfff");
        data.add("sfff");
        data.add("trr");
        data.add("urr");
        data.add("vrr");
        data.add("wrr");
        data.add("xrr");
        data.add("yjj");
        data.add("zjj");
        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        holder.removeAdapter(REQ_CODE);
    }
}
