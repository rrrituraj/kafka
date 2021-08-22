package com.rituraj.kafka.producer;

import javax.validation.constraints.NotNull;

public class Book {
    @NotNull
    private String bookName;
    @NotNull
    private String authorName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
