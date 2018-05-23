package com.hussain_chachuliya.customsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CustomSearch extends AppCompatActivity implements SearchAdapter.ICustomSearch {
    private SearchAdapter searchAdapter;
    private RecyclerView rv;
    private static int REQUEST_CODE;
    public static final String CUSTOM_SEARCH_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (SearchAdapter adapter : SearchAdapterHolder.getInstance().getAdapters().values()) {
            if (adapter.getRequestCode() == REQUEST_CODE) {
                searchAdapter = adapter;
                searchAdapter.setCustomSearchListener(this);
            }
        }

        rv = findViewById(R.id.rvSearch);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(searchAdapter);

        EditText txtSearch = findViewById(R.id.txtSearch);
        if (searchAdapter == null) return;
        searchAdapter.getFilter().filter(txtSearch.getText());
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchAdapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static void start(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, CustomSearch.class), requestCode);
        REQUEST_CODE = requestCode;
    }

    public static void start(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(new Intent(fragment.getActivity(), CustomSearch.class), requestCode);
        REQUEST_CODE = requestCode;
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
}
