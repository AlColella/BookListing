package com.example.alexandre.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class BookLoader extends AsyncTaskLoader {

    private String mUrl;
    private Context mCtx;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
        mCtx=context;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        List<Book> books = QueryBook.extractBooks(mUrl, mCtx);
        return books;
    }
}
