package com.last.booking.ui.bookDetail;

import android.support.annotation.Nullable;

import com.last.booking.data.model.MissionDetail;

public class BookDetailResult {

    @Nullable
    private BookDetailView bookDetailView;

    @Nullable
    private String error;

    @Nullable
    private BookDetailSuccessView bookSucessView;

    public BookDetailResult(@Nullable BookDetailView bookDetailView) {
        this.bookDetailView = bookDetailView;
    }

    public BookDetailResult(@Nullable BookDetailSuccessView bookSucessView) {
        this.bookSucessView = bookSucessView;
    }

    public BookDetailResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public BookDetailView getBookDetailView() {
        return bookDetailView;
    }

    @Nullable
    public String getError() {
        return error;
    }

    @Nullable
    public BookDetailSuccessView getBookSucessView() {
        return bookSucessView;
    }
}
