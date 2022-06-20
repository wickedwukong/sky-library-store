package com.sky.library;

/*
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 */

import java.util.Objects;

public class Book {
    private String reference;
    private String title;
    private String review;


    public Book(String reference, String title, String description) {
        this.reference = reference;
        this.title = title;
        this.review = description;
    }

    public String getReview() {
        return review;
    }

    public String getReference() {
        return reference;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return reference.equals(book.reference) && title.equals(book.title) && review.equals(book.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, title, review);
    }

}
