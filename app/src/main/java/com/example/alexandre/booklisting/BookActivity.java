package com.example.alexandre.booklisting;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends Activity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private String mTitulo;
    private String GOOGLE_URL =
            "https://www.googleapis.com/books/v1/volumes?q=intitle:";
    private BookAdapter mAdapter;
    private ProgressBar mPbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        mTitulo = getIntent().getExtras().getString(getString(R.string.parm_titulo));

        mPbar = findViewById(R.id.indeterminateBar);
        mPbar.setVisibility(View.VISIBLE);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        ListView bookView = findViewById(R.id.list);
        mAdapter = new BookAdapter(BookActivity.this, 0, new ArrayList<Book>());
        bookView.setAdapter(mAdapter);

        if (isConnected) {
            getLoaderManager().initLoader(1, null, BookActivity.this);
        } else {
            mPbar.setVisibility(View.GONE);
            ListView view = findViewById(R.id.list);
            TextView tView = findViewById(R.id.empty_list_item);
            tView.setText(R.string.noConnection);
            view.setEmptyView(findViewById(R.id.empty_list_item));
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        mTitulo = mTitulo.replace(" ", "+");
        String url = GOOGLE_URL + mTitulo;
        return new BookLoader(BookActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            ListView view = findViewById(R.id.list);
            view.setEmptyView(findViewById(R.id.empty_list_item));
        }
        mPbar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
