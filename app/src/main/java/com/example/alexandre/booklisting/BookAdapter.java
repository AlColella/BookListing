package com.example.alexandre.booklisting;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, int resource, List<Book> books) {
        super(context, resource, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView tituloView = listItemView.findViewById(R.id.titulo_view);
        tituloView.setText(currentBook.getTitulo());

        TextView autorView = listItemView.findViewById(R.id.autor_view);
        autorView.setText(currentBook.getAutor());

        return listItemView;
    }
}
