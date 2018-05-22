package com.hussain_chachuliya.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hussain_chachuliya.customsearch.CustomSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int REQ_CODE = 1;
    private final int REQ_CODE_2 = 2;
    EditText etDemo, etDemo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDemo = findViewById(R.id.etDemo);
        etDemo2 = findViewById(R.id.etDemo2);
        etDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSearch.start(MainActivity.this, getListOfStrings(), REQ_CODE);
            }
        });

        etDemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSearch.start(MainActivity.this, getListOfStrings(), REQ_CODE_2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            etDemo.setText(data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));
        } else if(requestCode == REQ_CODE_2 && resultCode == RESULT_OK){
            etDemo2.setText(data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));
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
}
