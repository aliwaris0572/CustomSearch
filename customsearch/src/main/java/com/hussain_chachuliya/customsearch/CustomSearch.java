package com.hussain_chachuliya.customsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

public class CustomSearch extends AppCompatActivity implements SearchAdapter.ITextChangedListener {
    private SearchAdapter searchAdapter;
    private static List<String> allData;
    private RecyclerView rv;

    public static final String CUSTOM_SEARCH_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);

        searchAdapter = new SearchAdapter(this, allData);
        rv = findViewById(R.id.rvSearch);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(searchAdapter);


        final EditText txtSearch = findViewById(R.id.txtSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchAdapter.getFilter().filter(txtSearch.getText());
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    public static void start(Activity activity, List<String> data, int requestCode) {
        activity.startActivityForResult(new Intent(activity, CustomSearch.class), requestCode);
        allData = data;
    }

    @Override
    public void getSelectedText(String content) {
        Intent intent = new Intent();
        intent.putExtra(CustomSearch.CUSTOM_SEARCH_TEXT, content);
        setResult(RESULT_OK, intent);
        this.finish();
    }

    @Override
    public void searchItemsCount(int count) {
        if (count == 0) {
            rv.setVisibility(View.GONE);
            findViewById(R.id.imgNoRecord).setVisibility(View.VISIBLE);
            hideKeyboard(this);
        } else {
            rv.setVisibility(View.VISIBLE);
            findViewById(R.id.imgNoRecord).setVisibility(View.GONE);
        }
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
