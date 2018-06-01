package com.example.alexandre.booklisting;

public class Book {

    private String mTitulo;
    private String mAutor;

    public Book(String titulo, String autor) {
        mTitulo = titulo;
        mAutor = autor;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public String getAutor() {
        return mAutor;
    }
}
