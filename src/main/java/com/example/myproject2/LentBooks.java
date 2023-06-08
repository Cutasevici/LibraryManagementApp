package com.example.myproject2;

public class LentBooks {
    int nr, subscription_id;
    String first_name, last_name, book_title;

    public LentBooks(int nr, int subscription_id, String first_name, String last_name, String book_title) {
        this.nr = nr;
        this.subscription_id = subscription_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.book_title = book_title;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(int subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
}
